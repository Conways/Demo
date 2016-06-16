package com.conways.demo.home.firstmenu.cardview;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.conways.demo.R;
import com.conways.demo.base.BaseActivity;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by user on 2016/6/2.
 */
public class CardViewActivity extends BaseTitleActivity implements View.OnClickListener {

    private CardView cvFirst;
    private CardView cvSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cardview);
        super.onCreate(savedInstanceState);
        initTitle();
    }


    //初始化标题
    private void initTitle() {
        ivTitleBack.setOnClickListener(this);
        tvTitleTitle.setText(getText(R.string.card_view_title));
        cvFirst = (CardView) findViewById(R.id.activity_cardview_first);
        cvSecond = (CardView) findViewById(R.id.activity_cardview_second);
        cvFirst.setOnClickListener(this);
        cvSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;

            case R.id.activity_cardview_first:
                showToast(getText(R.string.card_view_first).toString());
                break;
            case R.id.activity_cardview_second:
                showToast(getText(R.string.card_view_second).toString());
                break;

            default:
                break;


        }

    }
}
