package com.conways.demo.home.firstmenu.sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.conways.demo.R;

import java.util.List;

/**
 * Created by user on 2016/6/20.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.Holder> {


    private Context context;
    private List<User> list;
    private OnItemClickLisener onItemClickLisener;

    public UserListAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final UserListAdapter.Holder viewHolder, final int i) {
        User user = list.get(i);
        viewHolder.tvName.setText(user.getName());
        viewHolder.tvIntroduce.setText(user.getIntroduce());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickLisener != null) {
                    onItemClickLisener.itemLongClickLiserner(viewHolder.itemView, i);
                }
                return true;
            }
        });

    }

    @Override
    public UserListAdapter.Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_list, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        public ImageView ivHead;
        public TextView tvName;
        public TextView tvIntroduce;


        public Holder(View itemView) {
            super(itemView);
            ivHead = (ImageView) itemView.findViewById(R.id.item_user_list_head);
            tvName = (TextView) itemView.findViewById(R.id.item_user_list_name);
            tvIntroduce = (TextView) itemView.findViewById(R.id.item_user_list_introduce);
        }
    }

    public void setOnItemClickLisener(OnItemClickLisener onItemClickLisener) {
        this.onItemClickLisener = onItemClickLisener;
    }
}
