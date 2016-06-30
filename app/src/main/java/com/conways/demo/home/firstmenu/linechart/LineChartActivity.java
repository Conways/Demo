package com.conways.demo.home.firstmenu.linechart;

import android.os.Bundle;
import android.view.View;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/6/30.
 */
public class LineChartActivity extends BaseTitleActivity implements View.OnClickListener{

    private List<List<Data>> datas;
    private ChartLine chartLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_linechart);
        super.onCreate(savedInstanceState);
        initTitle();
        initData();
        init();
    }


    private void initTitle(){
        tvTitleTitle.setText(getText(R.string.linechart_title));
    }

    private void initData(){
        datas=new ArrayList<>();
        for (int i = 0; i <3; i++) {
            List<Data> list=new ArrayList<>();
            for (int j = 0; j <10 ; j++) {
                Data data =new Data();
                int value=(int)(Math.random()*20)+i*40;
                data.setValue(value);
                list.add(data);
            }
            datas.add(list);
        }
    }

    private void init() {
        chartLine=(ChartLine)findViewById(R.id.activity_linechart_linechart);
        chartLine.setDatas(datas);
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
}
