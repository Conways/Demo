package com.conways.demo.home.firstmenu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conways.demo.R;
import com.conways.demo.base.BaseFragment;
import com.conways.demo.home.firstmenu.bloodpressure.BloodPressureActivity;
import com.conways.demo.home.firstmenu.cardview.CardViewActivity;
import com.conways.demo.home.firstmenu.mbbbloodpressure.MBBBoodPressureActivity;
import com.conways.demo.home.firstmenu.notify.NotifyActivity;
import com.conways.demo.home.firstmenu.sideslip.SideslipActivity;
import com.conways.demo.home.firstmenu.timewheel.TimeWheelActivity;
import com.conways.demo.utils.LogUtils;

/**
 * Created by user on 2016/5/6.
 */
public class MenuFirstFragment extends BaseFragment implements View.OnClickListener, OnItemClickLisener {

    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private MyAdapter myAdapter;

    private String[] list = {"康宝贝血压计", "时间选择器", "脉搏波血压计", "卡片控件", "侧滑导航","通知栏"};
    private Class<?>[] listTarget = {BloodPressureActivity.class, TimeWheelActivity.class,
            MBBBoodPressureActivity.class, CardViewActivity.class, SideslipActivity.class,
            NotifyActivity.class};

    private int[] imageIds = {R.drawable.pager_1, R.drawable.pager_2, R.drawable.pager_3, R.drawable.pager_4,};
    private String urls[] = {
            "http://pic.58pic.com/58pic/15/13/97/52i58PICnaB_1024.jpg" ,
            "http://www.leewiart.com/userfiles/18573/1756b215a1834ac2a831020e54884853.jpg?635529900812812500",
            "http://imgsrc.baidu.com/forum/pic/item/f697f2d3572c11df73873098622762d0f603c282.jpg",
            "http://pic41.nipic.com/20140515/18758466_100750604144_2.jpg"};

    private View vFirstNode;
    private View vSecondNode;
    private View vThirdNode;
    private View vFourthNode;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_first, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.menu_first_viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ViewPagerAdapter(context, imageIds,urls) {
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectNode(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.menu_first_recyclerView);
        myAdapter = new MyAdapter(context, list);
        myAdapter.setClickLisener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context));

        vFirstNode = view.findViewById(R.id.menu_first_node_1);
        vSecondNode = view.findViewById(R.id.menu_first_node_2);
        vThirdNode = view.findViewById(R.id.menu_first_node_3);
        vFourthNode = view.findViewById(R.id.menu_first_node_4);
        setSelectNode(viewPager.getCurrentItem());
    }


    private void setSelectNode(int position) {
        vFirstNode.setBackgroundResource(R.drawable.circle_stroke);
        vSecondNode.setBackgroundResource(R.drawable.circle_stroke);
        vThirdNode.setBackgroundResource(R.drawable.circle_stroke);
        vFourthNode.setBackgroundResource(R.drawable.circle_stroke);
        switch (position) {
            case 0:
                vFirstNode.setBackgroundResource(R.drawable.circle_layout_list);
                break;
            case 1:
                vSecondNode.setBackgroundResource(R.drawable.circle_layout_list);
                break;
            case 2:
                vThirdNode.setBackgroundResource(R.drawable.circle_layout_list);
                break;
            case 3:
                vFourthNode.setBackgroundResource(R.drawable.circle_layout_list);
                break;

            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void itemClickLiserner(View parent, int position) {
        toTargetActivity(listTarget[position]);
    }

    @Override
    public void itemLongClickLiserner(View parent, int position) {

    }


}
