package com.conways.demo.home.firstmenu.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.conways.demo.R;
import com.conways.demo.base.BaseTitleActivity;

/**
 * Created by user on 2016/6/16.
 */
public class NotifyActivity extends BaseTitleActivity implements View.OnClickListener {

    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Button btSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notify);
        super.onCreate(savedInstanceState);
        initTitle();
        init();
    }

    private void initTitle() {
        tvTitleTitle.setText(getText(R.string.notify_title));
        ivTitleBack.setOnClickListener(this);

    }

    private void init() {
        btSend = (Button) findViewById(R.id.activity_notify_show);
        btSend.setOnClickListener(this);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this);
        builder.setContentTitle(getText(R.string.notify_notify_title))
                .setContentText(getText(R.string.notify_notify_content))
                .setTicker(getText(R.string.notify_notify_ticker))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher);
    }


    private void showNotify() {
        notificationManager.notify(100, builder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_style_child_right_text_back:
                end();
                break;
            case R.id.activity_notify_show:
                showNotify();
                break;
            default:
                break;


        }

    }
}
