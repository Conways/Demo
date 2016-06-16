package com.conways.demo.home.firstmenu.bloodpressure;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseActivity;
import com.conways.demo.base.BaseTitleActivity;
import com.conways.demo.utils.LogUtils;

import java.util.Iterator;

/**
 * Created by user on 2016/5/16.
 */

public class BloodPressureActivity extends BaseTitleActivity implements View.OnClickListener {


    private ProgressBar pgHigh;
    private ProgressBar pgLow;
    private ProgressBar pgRate;

    private Button btMeasure;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BPControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bloodpressure);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }
    //初始化标题
    private void initTitle() {
        ivTitleBack.setOnClickListener(this);
        tvTitleTitle.setText(getText(R.string.blood_pressure_title));
    }
    //初始化
    private void init() {
        pgHigh = (ProgressBar) findViewById(R.id.activity_bloodpressure_high_progress);
        pgLow = (ProgressBar) findViewById(R.id.activity_bloodpressure_low_progress);
        pgRate = (ProgressBar) findViewById(R.id.activity_bloodpressure_rate_progress);
        pgHigh.setProgress(0);
        pgLow.setProgress(0);
        pgRate.setProgress(0);
        btMeasure = (Button) findViewById(R.id.activity_bloodpressure_start_measure);
        btMeasure.setOnClickListener(this);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        registerReceiver(broadcastReceiver,getIntentFilter());
    }
    //初始化广播过滤器
    private IntentFilter getIntentFilter(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BPContants.ACTION_STATE_CONNECT);
        intentFilter.addAction(BPContants.ACTION_STATE_DISONNECT);
        intentFilter.addAction(BPContants.ACTION_STATE_INIT_CHARACTERISTIC_SECCUSS);
        intentFilter.addAction(BPContants.ACTION_CONNECT_SECCUSS);
        intentFilter.addAction(BPContants.ACTION_START_MEASURE_SECCUSS);
        intentFilter.addAction(BPContants.ACTION_EXTRA_DATA);
        return intentFilter;
    }
    //初始化广播接收者
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if (action.equals(BPContants.ACTION_STATE_CONNECT)){
                logD(BPContants.ACTION_STATE_CONNECT);
                control.findServices();
            }else if(action.equals(BPContants.ACTION_STATE_INIT_CHARACTERISTIC_SECCUSS)){
                logD(BPContants.ACTION_STATE_INIT_CHARACTERISTIC_SECCUSS);
                control.startCheck();
            }else if(action.equals(BPContants.ACTION_CONNECT_SECCUSS)){
                logD(BPContants.ACTION_CONNECT_SECCUSS);
                control.startMeasure();
            }else if(action.equals(BPContants.ACTION_EXTRA_DATA)){

            }

        }
    };
    //绑定服务
    private void bindBloodPressureService() {
        Intent intent = new Intent(this, BPService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    //绑定服务回调接口
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BPService.BPServiceBinder binder = (BPService.BPServiceBinder) service;
            BPService bpService = binder.getBpService();
            control = new BPControl(BloodPressureActivity.this, bpService);
            control.connectDevice(bluetoothDevice);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @SuppressLint("NewApi")
    private void startScan() {


        if(bluetoothDevice!=null){
            control.connectDevice(bluetoothDevice);
            return;
        }

        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            showToast("蓝牙不可用");
            return;
        }

        bluetoothAdapter.startLeScan(leScanCallback);
    }


    @SuppressLint("NewApi")
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            logD("发现设备" + device.getName());
            if (device.getName().contains(BPContants.DEVICE_NAME)) {
                bluetoothDevice = device;
                bindBloodPressureService();
                bluetoothAdapter.stopLeScan(leScanCallback);
            }
        }
    };


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.activity_bloodpressure_start_measure:
                startScan();
                break;
            default:
                break;


        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (control != null) {
            control.DisConnectDevice();
            unbindService(serviceConnection);
        }
        unregisterReceiver(broadcastReceiver);
    }
}
