package com.conways.demo.home.firstmenu.sqlite;

import android.os.Bundle;
import android.view.View;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by Conways on 2016/6/19.
 */
public class SqliteActivity extends BaseTitleActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sqlite);
        super.onCreate(savedInstanceState);
        initTitle();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.sqlite_title));
        ivTitleBack.setOnClickListener(this);
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
