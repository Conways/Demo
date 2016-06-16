package com.conways.demo.home.fourthmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conways.demo.R;
import com.conways.demo.base.BaseFragment;

/**
 * Created by user on 2016/5/6.
 */
public class MenuFourthFragment extends BaseFragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu_fouth,container,false);
        init(view);
        return view;
    }

    private void init(View view){

    }

    @Override
    public void onClick(View v) {

    }
}
