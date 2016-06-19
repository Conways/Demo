package com.conways.demo.home.firstmenu.sidebar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;
import com.conways.demo.home.firstmenu.sidebar.sidebarwiget.OnSideBarTouchListener;
import com.conways.demo.home.firstmenu.sidebar.sidebarwiget.SideBar;

/**
 * Created by Conways on 2016/6/18.
 */
public class SideBarActivity extends BaseTitleActivity implements View.OnClickListener, OnSideBarTouchListener {

    private SideBar sideBar;
    private TextView tvValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sidebar);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.sidebar_title));
        ivTitleBack.setOnClickListener(this);
        tvValue = (TextView) findViewById(R.id.activity_sidebar_value);
    }


    private void init() {
        sideBar = (SideBar) findViewById(R.id.activity_sidebar_sidebar);
        sideBar.setOnSideBarTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;

            default:
                break;


        }

    }

    @Override
    public void onLetterChanged(String letter, int position, int itemHeight) {
        logD("value" + letter + "position==" + position);
        tvValue.setText(letter);

    }

    @Override
    public void onLetterTouching(boolean touching) {
        tvValue.setVisibility(touching ? View.VISIBLE : View.GONE);

    }
}
