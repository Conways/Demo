package com.conways.demo.home.firstmenu.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by user on 2016/6/20.
 */
public class AddUserActivity extends BaseTitleActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etIntroduce;
    private Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_adduser);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.adduser_title));
        ivTitleBack.setOnClickListener(this);
    }

    private void init() {
        etName = (EditText) findViewById(R.id.activity_adduser_name);
        etIntroduce = (EditText) findViewById(R.id.activity_adduser_introduce);
        btSave = (Button) findViewById(R.id.activity_adduser_save);
        btSave.setOnClickListener(this);
    }


    private void save() {
        if (isEmpty(etName.getText().toString()) || isEmpty(etIntroduce.getText().toString())) {
            showToast(getText(R.string.adduser_keep_msg_perfect).toString());
            return;
        }

        User user = new User();
        user.setName(etName.getText().toString().trim());
        user.setIntroduce(etIntroduce.getText().toString().trim());
        if (DbDao.getInstance(AppContext).addUser(user)) {
            showToast(getText(R.string.adduser_save_seccuss).toString());
            end();
        } else {
            showToast(getText(R.string.adduser_save_failed).toString());
        }


    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.activity_adduser_save:
                save();
                break;

            default:
                break;
        }

    }


}
