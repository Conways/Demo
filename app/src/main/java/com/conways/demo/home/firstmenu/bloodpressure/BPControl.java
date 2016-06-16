package com.conways.demo.home.firstmenu.bloodpressure;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

/**
 * Created by user on 2016/5/17.
 */
public class BPControl {

    private Context context;
    private BPService bpService;

    public BPControl(Context context, BPService bpService) {
        this.context = context;
        this.bpService = bpService;
    }

    public void findServices(){
        bpService.findServices();
    }

    public void connectDevice(BluetoothDevice bluetoothDevice){
        bpService.connectDevice(bluetoothDevice);
    }

    public void startCheck(){
        bpService.startCheck();
    }

    public void startMeasure(){
        bpService.startMeasrue();
    }

    public void DisConnectDevice(){
        bpService.disConnectDevice();
        bpService.close();
    }
}
