package com.conways.demo.bdmap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.conways.demo.R;
import com.conways.demo.base.BaseActivity;

/**
 * Created by user on 2016/5/11.
 */
public class BdMapActivity extends BaseActivity implements View.OnClickListener {

    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdmap);
        bt = (Button) findViewById(R.id.button);
        bt.setText("开始连接");
        bt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;


        }
    }
}
