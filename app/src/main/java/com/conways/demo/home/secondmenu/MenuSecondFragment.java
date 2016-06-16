package com.conways.demo.home.secondmenu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseFragment;

/**
 * Created by user on 2016/5/6.
 */
public class MenuSecondFragment extends BaseFragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private String[] lists = {"a", "b", "c", "d", "e"};

    private TextView tvTitle;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x001){
                refreshList();
                myAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu_second,container,false);
        initTitle(view);
        init(view);
        return view;
    }

    private void initTitle(View view){
        tvTitle=(TextView) view.findViewById(R.id.title_style_main_title);
        tvTitle.setText(getText(R.string.tools_bar_menu_2));
    }

    private void init(View view){
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.menu_second_swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(new int[]{R.color.main_color});


        recyclerView=(RecyclerView)view.findViewById(R.id.menu_second_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        myAdapter=new MyAdapter(context, lists);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
    }

    private void refreshList(){
        for (int i = 0; i< lists.length; i++){
            if (i== lists.length/2){break;}
            String temp= lists[i];
            lists[i]= lists[lists.length-1-i];
            lists[lists.length-1-i]=temp;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

        handler.sendEmptyMessageDelayed(0x001,1000l);

    }
}
