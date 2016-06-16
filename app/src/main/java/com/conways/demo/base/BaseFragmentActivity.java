package com.conways.demo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.conways.demo.DemoApplication;
import com.conways.demo.R;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by user on 2016/5/6.
 */
public class BaseFragmentActivity extends FragmentActivity {
    protected DemoApplication AppContext;
    protected Context context;

    private Toast toast;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    private void init() {
        AppContext = (DemoApplication) getApplication();
        context = this;
    }


    protected void showToast(String msg) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }

    protected void showToast(int strId) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(strId);
        toast.show();
    }

    protected void toTargetActivity(Class<?> target) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        startActivity(intent);
    }

    protected void end() {
        this.finish();

    }

    protected int getColors(int colorId) {
        return getResources().getColor(colorId);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
