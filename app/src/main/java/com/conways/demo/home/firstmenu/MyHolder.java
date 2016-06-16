package com.conways.demo.home.firstmenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.conways.demo.R;

/**
 * Created by user on 2016/4/29.
 */
public class MyHolder extends RecyclerView.ViewHolder {

    public TextView tv;

    public MyHolder(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View itemView) {
        tv = (TextView) itemView.findViewById(R.id.item_recycler_view_text);

    }


}
