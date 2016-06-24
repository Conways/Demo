package com.conways.demo.home.firstmenu.sqlite;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Conways on 2016/6/19.
 */
public class SqliteActivity extends BaseTitleActivity implements View.OnClickListener, OnItemClickLisener {

    private RecyclerView rvUserList;
    private UserListAdapter adapter;
    private List<User> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sqlite);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.sqlite_title));
        ivTitleBack.setOnClickListener(this);
        tvTitleAction.setVisibility(View.VISIBLE);
        tvTitleAction.setText(getText(R.string.sqlite_aciton));
        tvTitleAction.setOnClickListener(this);
    }

    private void init() {
        rvUserList = (RecyclerView) findViewById(R.id.activity_sqlite_list);
        rvUserList.setLayoutManager(new LinearLayoutManager(context));
        rvUserList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void onResume() {
        update();
        super.onResume();
    }

    private void update() {
        if (list == null) {
            list = new ArrayList<User>();
        }
        list.clear();
        list.addAll(DbDao.getInstance(AppContext).getUsers());
        if (adapter == null) {
            adapter = new UserListAdapter(context, list);
            adapter.setOnItemClickLisener(this);
            rvUserList.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemLongClickLiserner(View parent, int position) {
        User user = list.get(position);
        dialog(user.getId());
    }

    @Override
    public void itemClickLiserner(View parent, int position) {
        dialog(position);
    }


    private void dialog(final int id) {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_delete_item, null);
        TextView tvCancel = (TextView) view.findViewById(R.id.dialog_delete_item_cancel);
        TextView tvOk = (TextView) view.findViewById(R.id.dialog_delete_item_ok);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DbDao.getInstance(SqliteActivity.this).deleteUserById(id)) {
                    alertDialog.dismiss();
                    update();
                }
            }
        });
        alertDialog.getWindow().setContentView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.title_style_child_right_text_action:
                toTargetActivity(AddUserActivity.class);
                break;

            default:
                break;


        }


    }
}
