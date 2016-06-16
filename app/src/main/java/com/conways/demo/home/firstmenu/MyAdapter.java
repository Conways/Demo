package com.conways.demo.home.firstmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conways.demo.R;

/**
 * Created by user on 2016/4/29.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    private Context context;
    private String[] list;

    private OnItemClickLisener clickLisener;

    public MyAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyHolder viewHolder, final int i) {

        viewHolder.tv.setText(list[i]);
        if (clickLisener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickLisener.itemClickLiserner(v, i);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickLisener.itemLongClickLiserner(v,i);
                    return true;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_1_recycler_view, viewGroup, false);
        MyHolder mHolder = new MyHolder(view);
        return mHolder;
    }


    public void setClickLisener(OnItemClickLisener clickLisener) {
        this.clickLisener = clickLisener;
    }
}
