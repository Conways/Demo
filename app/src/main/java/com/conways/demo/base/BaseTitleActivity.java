package com.conways.demo.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.demo.R;

/**
 * Created by user on 2016/6/2.
 *
 * 有统一title风格的activity的父类，
 * 继承该类时，在onCreate里一定要先setContentView然后super.onCreat(),
 * 不然会冒出空指针
 */
public class BaseTitleActivity extends BaseActivity{


    protected ImageView ivTitleBack;
    protected TextView tvTitleTitle;
    protected TextView tvTitleAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();
    }


    private void initTitle() {
        ivTitleBack = (ImageView) findViewById(R.id.title_style_child_right_text_back);
        tvTitleTitle = (TextView) findViewById(R.id.title_style_child_right_text_title);
        tvTitleAction = (TextView) findViewById(R.id.title_style_child_right_text_action);
        tvTitleAction.setVisibility(View.GONE);
    }


}
