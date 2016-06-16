package com.conways.demo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.conways.demo.DemoApplication;

/**
 * Created by user on 2016/5/6.
 * 所有fragment的基类
 */
public class BaseFragment extends Fragment {

    protected DemoApplication AppContext;
    protected Context context;

    private Toast toast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        AppContext = (DemoApplication) getActivity().getApplication();
        context = getActivity();
    }


    protected void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
        }
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

}
