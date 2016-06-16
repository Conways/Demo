package com.conways.demo.home.firstmenu.timewheel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.home.firstmenu.timewheel.widget.BaseWheelAdapter;

/**
 * Created by user on 2016/5/27.
 */
public class WheelAdapter extends BaseWheelAdapter<Integer> {

    private Context context;


    public WheelAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected View bindView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_wheel_time, parent,
                    false);
            holder.item=(TextView) convertView.findViewById(R.id.item_wheel_time_text);
            convertView.setTag(holder);
        }else {
            holder=(Holder)convertView.getTag();
        }

        holder.item.setText(mList.get(position).toString());

        return convertView;
    }


    class Holder {
        TextView item;
    }
}
