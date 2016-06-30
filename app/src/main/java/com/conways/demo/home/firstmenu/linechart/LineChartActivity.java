package com.conways.demo.home.firstmenu.linechart;

import android.os.Bundle;
import android.view.View;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by user on 2016/6/30.
 */
public class LineChartActivity extends BaseTitleActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_linechart);
        super.onCreate(savedInstanceState);
        initTitle();
    }


    private void initTitle(){
        tvTitleTitle.setText(getText(R.string.linechart_title));
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
