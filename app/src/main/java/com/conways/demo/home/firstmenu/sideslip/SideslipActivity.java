package com.conways.demo.home.firstmenu.sideslip;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by user on 2016/6/6.
 */
public class SideslipActivity extends BaseTitleActivity implements View.OnClickListener {

    private DrawerLayout dlMain;
    private LinearLayout llLeft;
    private CircleImageView cvHead;

    private FrameLayout flItem1;
    private FrameLayout flItem2;
    private FrameLayout flItem3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_side_slip);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.side_slip_title));
        ivTitleBack.setImageResource(R.drawable.menu_action);
        ivTitleBack.setOnClickListener(this);
    }

    private void init() {
        dlMain = (DrawerLayout) findViewById(R.id.activity_side_slip_drawer);
        llLeft = (LinearLayout) findViewById(R.id.activity_side_slip_left);
        cvHead = (CircleImageView) findViewById(R.id.activity_side_slip_left_head);
        cvHead.setBorderColor(0xffed6d35);
        cvHead.setImageResource(R.drawable.pager_1);
        cvHead.setBorderWidth(3);

        flItem1 = (FrameLayout) findViewById(R.id.activity_side_slip_item_1);
        flItem2 = (FrameLayout) findViewById(R.id.activity_side_slip_item_2);
        flItem3 = (FrameLayout) findViewById(R.id.activity_side_slip_item_3);

        flItem1.setOnClickListener(this);
        flItem2.setOnClickListener(this);
        flItem3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                dlMain.openDrawer(llLeft);
                break;

            case R.id.activity_side_slip_item_1:
                showToast("item 1");
                break;
            case R.id.activity_side_slip_item_2:
                showToast("item 2");
                break;
            case R.id.activity_side_slip_item_3:
                showToast("item 3");
                break;

            default:
                break;


        }
    }
}
