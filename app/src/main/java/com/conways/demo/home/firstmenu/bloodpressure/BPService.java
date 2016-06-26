package com.conways.demo.home.firstmenu.bloodpressure;

import android.annotation.TargetApi;
import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.conways.demo.utils.LogUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by user on 2016/5/16.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class BPService extends Service {


    private BPServiceBinder binder = new BPServiceBinder();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0x00:

                    if (!connect) {
                        connectDevice(device);
                    }
                    break;
                case 0x01:
                    startCheck();

                default:
                    break;


            }
        }
    };

    private boolean connect = false;
    private BluetoothDevice device = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disConnectDevice();
        close();
    }

    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic readCharacteristic = null;
    private BluetoothGattCharacteristic writeCharacteristic = null;

    private BluetoothGattCallback gattCallback = new BluetoothGattCallback() {

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            LogUtils.d("BPService", "连接状态发生变化");
            switch (newState) {
                case BluetoothProfile.STATE_CONNECTED:
                    LogUtils.d("BPService", "连接建立");
                    connect = true;
                    bluetoothGatt.discoverServices();
                    broadcast(BPContants.ACTION_STATE_CONNECT);
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    LogUtils.d("BPService", "连接断开");
                    broadcast(BPContants.ACTION_STATE_DISONNECT);
                    break;

                default:
                    break;


            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            switch (status) {
                case BluetoothGatt.GATT_SUCCESS:
                    BluetoothGattService gattService = null;
                    LogUtils.d("BPService", (gatt.getServices().size() + "个服务"));
                    for (BluetoothGattService gattService1 : gatt.getServices()) {
                        if (gattService1.getUuid().toString().equals(BPContants
                                .BP_SERVICE_UUID)) {
                            gattService = gattService1;
                            break;

                        }
                    }

                    if (gattService == null) {
                        LogUtils.e("BPService", "未找到目标服务");
                        return;
                    }
                    LogUtils.d("BPService", "找到目标服务,开始打通通信通道");
                    for (BluetoothGattCharacteristic gattCharacteristic : gattService.getCharacteristics()) {

                        LogUtils.d("BPService", gattCharacteristic.getUuid().toString());
                        if (gattCharacteristic.getUuid().toString().equals(BPContants
                                .BP_SERVICE_CHARACTERISTIC_WRITE)) {
                            writeCharacteristic = gattCharacteristic;
                            continue;
                        } else if (gattCharacteristic.getUuid().toString().equals(BPContants
                                .BP_SERVICE_CHARACTERISTIC_READ)) {
                            readCharacteristic = gattCharacteristic;
                            continue;
                        }
                    }

                    if (writeCharacteristic == null || readCharacteristic == null) {
                        LogUtils.e("BPService", "读写通道开启失败！");
                        return;
                    }

                    LogUtils.d("BPService", "通道开启成功！");
                    bluetoothGatt.readCharacteristic(writeCharacteristic);
                    bluetoothGatt.readCharacteristic(readCharacteristic);
                    bluetoothGatt.setCharacteristicNotification(readCharacteristic, true);
                    broadcast(BPContants.ACTION_STATE_INIT_CHARACTERISTIC_SECCUSS);
                    break;

                default:
                    break;


            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            LogUtils.d("BPService  onCharacteristicRead", formatData(characteristic.getValue()));

        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

            super.onCharacteristicWrite(gatt, characteristic, status);
            LogUtils.d("BPService  onCharacteristicWrite", formatData(characteristic.getValue())
                    +"zzzz"+status);
            if (!Arrays.equals(characteristic.getValue(),BPContants.START_MEASURE())){

                startMeasrue();
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            LogUtils.d("BPService  onCharacteristicChanged", formatData(characteristic.getValue()));
        }


    };


    private void broadcast(String action) {
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }


    private void broadcast(String action, BluetoothGattCharacteristic characteristic) {
        Intent intent = new Intent(action);
        Bundle bundle = new Bundle();
        bundle.putByteArray(BPContants.ACTION_EXTRA_DATA, characteristic.getValue());
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    int time = 0;

    public void connectDevice(BluetoothDevice bluetoothDevice) {

        if (bluetoothDevice == null) {
            return;
        }
        if (bluetoothDevice.equals(device)) {
            LogUtils.d("BPService", "超时，使用已有的gatt再次进行连接，已经消费" + (time += 2) + "秒");
            bluetoothGatt.connect();
            handler.sendEmptyMessageDelayed(0x00, 2000l);
            return;
        }
        LogUtils.d("BPService", "构建一个新的gatt进行连接");
        bluetoothGatt = bluetoothDevice.connectGatt(this, false, gattCallback);
        device = bluetoothDevice;
        handler.sendEmptyMessageDelayed(0x00, 2000l);

    }

    public void findServices() {
        bluetoothGatt.discoverServices();
    }


    public void startCheck() {
        LogUtils.d("BPService", "开始连接校验");
        writeCharacteristic.setValue(BPContants.CONTECT());
        bluetoothGatt.writeCharacteristic(writeCharacteristic);
    }

    public void startMeasrue() {
        LogUtils.d("BPService", "开始测量");
        writeCharacteristic.setValue(BPContants.START_MEASURE());
        bluetoothGatt.writeCharacteristic(writeCharacteristic);
    }


    public void disConnectDevice() {
        if (bluetoothGatt == null) {
            return;
        }
        bluetoothGatt.disconnect();
    }


    public void close() {
        if (bluetoothGatt == null) {
            return;
        }
        bluetoothGatt.close();
        bluetoothGatt = null;
    }

    private String formatData(byte[] data) {
        if (data == null || data.length <= 0) {
            return "data is null!";
        }
        StringBuilder stringBuilder = new StringBuilder(data.length);
        for (byte byteChar : data) {

            stringBuilder.append(String.format("%02X ", byteChar));
        }

        return stringBuilder.toString();
    }


    public class BPServiceBinder extends Binder

    {
        public BPService getBpService() {
            return BPService.this;
        }
    }


}
