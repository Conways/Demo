package com.conways.demo.home.firstmenu.timewheel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseActivity;
import com.conways.demo.base.BaseTitleActivity;
import com.conways.demo.home.firstmenu.timewheel.widget.ArrayWheelAdapter;
import com.conways.demo.home.firstmenu.timewheel.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 2016/5/27.
 */
public class TimeWheelActivity extends BaseTitleActivity implements View.OnClickListener, WheelView
        .OnWheelItemSelectedListener {


    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_timewheel);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        ivTitleBack.setOnClickListener(this);
        tvTitleTitle.setText(getText(R.string.time_wheel_title));
    }

    private void init() {

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextColor = 0xffed6d35;
        style.textColor = 0xff999999;
        style.textSize = getResources().getDimension(R.dimen.weel_time_text_size);
        style.selectedTextSize = getResources().getDimension(R.dimen.weel_time_selected_text_size);
        style.backgroundColor = 0x00ffffff;


        wvYear = (WheelView) this.findViewById(R.id.activity_timewheel_year);
        wvMonth = (WheelView) this.findViewById(R.id.activity_timewheel_month);
        wvDay = (WheelView) this.findViewById(R.id.activity_timewheel_day);

        wvYear.setWheelAdapter(new ArrayWheelAdapter(this));
        wvYear.setSkin(WheelView.Skin.Holo);
        wvYear.setWheelData(getDataOfYear());
        wvYear.setWheelSize(3);
        wvYear.setStyle(style);
        wvYear.setExtraText(getText(R.string.time_wheel_year).toString(), 0xffed6d35, getResources().getDimension(R.dimen
                        .weel_time_selected_text_size),
                getResources().getDimension(R.dimen.w90));

        wvMonth.setWheelAdapter(new ArrayWheelAdapter(this));
        wvMonth.setSkin(WheelView.Skin.Holo);
        wvMonth.setWheelData(getDataOfMonth());
        wvMonth.setWheelSize(3);
        wvMonth.setStyle(style);
        wvMonth.setExtraText(getText(R.string.time_wheel_month).toString(), 0xffed6d35, getResources().getDimension(R.dimen
                        .weel_time_selected_text_size),
                getResources().getDimension(R.dimen.w60));

        wvDay.setWheelAdapter(new ArrayWheelAdapter(this));
        wvDay.setSkin(WheelView.Skin.Holo);
        wvDay.setWheelData(getDataOfDay(31));
        wvDay.setWheelSize(3);
        wvDay.setStyle(style);
        wvDay.setExtraText(getText(R.string.time_wheel_day).toString(), 0xffed6d35, getResources().getDimension(R.dimen
                        .weel_time_selected_text_size),
                getResources().getDimension(R.dimen.w60));


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
    public void onItemSelected(int position, Object o) {

        logD(o.getClass().getName());

    }

    private List<Integer> getDataOfYear() {
        List<Integer> list = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        for (int i = 0; i < 100; i++) {
            list.add(calendar.get(Calendar.YEAR) - i);
        }


        return list;
    }

    private List<Integer> getDataOfMonth() {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 13; i++) {
            list.add(i);
        }


        return list;
    }

    private List<Integer> getDataOfDay(int day) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < day; i++) {
            list.add(i);
        }


        return list;
    }
}
