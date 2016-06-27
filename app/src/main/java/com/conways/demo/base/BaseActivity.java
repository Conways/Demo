package com.conways.demo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.conways.demo.home.firstmenu.timewheel.DemoApplication;
import com.conways.demo.utils.LogUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by user on 2016/5/6.
 * <p/>
 * 所有非fragmentActivity的基类
 */
public class BaseActivity extends Activity {
    protected DemoApplication AppContext;
    protected Context context;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        AppContext = (DemoApplication) getApplication();
        context = this;
    }


    protected void showToast(String msg) {

        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();

    }


    protected void logD(String debug) {

        LogUtils.d(this.getClass().getSimpleName(),debug);

    }

    protected void logE(String error) {
        LogUtils.e(this.getClass().getSimpleName(),error);

    }


    protected void toTargetActivity(Class<?> target) {
        Intent intent = new Intent();
        intent.setClass(context, target);
        startActivity(intent);
    }

    protected void end() {
        this.finish();
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
