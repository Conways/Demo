package com.conways.demo.home.firstmenu.mbbbloodpressure;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseActivity;
import com.conways.demo.base.BaseTitleActivity;
import com.iknet.iknetbluetoothlibrary.BluetoothManager;
import com.iknet.iknetbluetoothlibrary.MeasurementResult;

import java.util.List;

/**
 * Created by user on 2016/6/2.
 */
public class MBBBoodPressureActivity extends BaseTitleActivity implements View.OnClickListener,
        BluetoothManager.OnBTMeasureListener {


    BluetoothManager bluetoothManager;
    private TextView tvMsg;
    private Button btStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mbbbloodpressure);
        super.onCreate(savedInstanceState);
        initTitle();
        init();

    }

    //初始化标题
    private void initTitle() {
        ivTitleBack.setOnClickListener(this);
        tvTitleTitle.setText(getText(R.string.mbb_blood_pressure_title));
    }

    private void init() {
        bluetoothManager = BluetoothManager.getInstance(this);
        bluetoothManager.initSDK();
        tvMsg = (TextView) findViewById(R.id.activity_mbbbloodpressure_data);
        btStart = (Button) findViewById(R.id.activity_mbbbloodpressure_start);
        btStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.activity_mbbbloodpressure_start:
                action();
                break;

            default:
                break;
        }
    }

    private void action() {
        if (btStart.getText().toString().equals(getResources().getString(R.string
                .mbb_blood_pressure_start))) {
            bluetoothManager.startBTAffair(this);

        } else if (btStart.getText().toString().equals(getResources().getString(R.string
                .mbb_blood_pressure_restart))) {
            if (bluetoothManager.isConnectBT()) {
                bluetoothManager.startMeasure();
            } else {
                bluetoothManager.startBTAffair(this);
            }

        }
    }

    @Override
    public void onFoundFinish(List<BluetoothDevice> list) {
        if (list.size() <= 0) {
            tvMsg.setText("未检索到设备");
            btStart.setText(getText(R.string.mbb_blood_pressure_restart));
        }

    }

    @Override
    public void onConnected(boolean b, BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void onPower(String s) {

    }

    @Override
    public void onRunning(String s) {
        tvMsg.setText(s);
    }

    @Override
    public void onMeasureError() {
        tvMsg.setText("测量错误");
        btStart.setText(getText(R.string.mbb_blood_pressure_restart));


    }

    @Override
    public void onMeasureResult(MeasurementResult measurementResult) {
        tvMsg.setText(
                "高压：" + measurementResult.getCheckShrink() + "\n" +
                        "低压：" + measurementResult.getCheckDiastole() + "\n" +
                        "心率：" + measurementResult.getCheckHeartRate());
        btStart.setText(getText(R.string.mbb_blood_pressure_restart));
    }

    @Override
    public void onDisconnected(BluetoothDevice bluetoothDevice) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothManager.stopBTAffair();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            bluetoothManager.stopMeasure();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }
}
