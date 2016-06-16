package com.conways.demo.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conways.demo.R;
import com.conways.demo.base.BaseFragmentActivity;
import com.conways.demo.home.firstmenu.MenuFirstFragment;
import com.conways.demo.home.fourthmenu.MenuFourthFragment;
import com.conways.demo.home.secondmenu.MenuSecondFragment;
import com.conways.demo.home.thirdmenu.MenuThirdFragment;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    private static final String[] TAGS = {"first", "second", "third", "fourth"};
    private static final int ID_MENU_1 = 0x001;
    private static final int ID_MENU_2 = 0x002;
    private static final int ID_MENU_3 = 0x003;
    private static final int ID_MENU_4 = 0x004;
    private static final String CURRENT_TAB = "current_tab";

    private int currentTab = ID_MENU_1;

    private LinearLayout menu_1;
    private LinearLayout menu_2;
    private LinearLayout menu_3;
    private LinearLayout menu_4;


    private ImageView ivMenu1Icon;
    private TextView tvMenu1;
    private ImageView ivMenu2Icon;
    private TextView tvMenu2;
    private ImageView ivMenu3Icon;
    private TextView tvMenu3;
    private ImageView ivMenu4Icon;
    private TextView tvMenu4;


    private MenuFirstFragment firstFragment;
    private MenuSecondFragment secondFragment;
    private MenuThirdFragment thirdFragment;
    private MenuFourthFragment fourthFragment;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        restoreFragment(savedInstanceState);
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();

        menu_1 = (LinearLayout) findViewById(R.id.menu_1);
        menu_2 = (LinearLayout) findViewById(R.id.menu_2);
        menu_3 = (LinearLayout) findViewById(R.id.menu_3);
        menu_4 = (LinearLayout) findViewById(R.id.menu_4);

        menu_1.setOnClickListener(this);
        menu_2.setOnClickListener(this);
        menu_3.setOnClickListener(this);
        menu_4.setOnClickListener(this);

        ivMenu1Icon = (ImageView) findViewById(R.id.menu_1_icon);
        ivMenu2Icon = (ImageView) findViewById(R.id.menu_2_icon);
        ivMenu3Icon = (ImageView) findViewById(R.id.menu_3_icon);
        ivMenu4Icon = (ImageView) findViewById(R.id.menu_4_icon);

        tvMenu1 = (TextView) findViewById(R.id.menu_1_text);
        tvMenu2 = (TextView) findViewById(R.id.menu_2_text);
        tvMenu3 = (TextView) findViewById(R.id.menu_3_text);
        tvMenu4 = (TextView) findViewById(R.id.menu_4_text);
    }

    private void restoreFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            firstFragment = (MenuFirstFragment) fragmentManager.findFragmentByTag(TAGS[0]);
            secondFragment = (MenuSecondFragment) fragmentManager.findFragmentByTag(TAGS[1]);
            thirdFragment = (MenuThirdFragment) fragmentManager.findFragmentByTag(TAGS[2]);
            fourthFragment = (MenuFourthFragment) fragmentManager.findFragmentByTag(TAGS[3]);
            int current = savedInstanceState.getInt(CURRENT_TAB);
            setSlectedTab(current);
        }else {
            setSlectedTab(ID_MENU_1);
        }
    }

    private void setSlectedTab(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        resetAll(fragmentTransaction);
        currentTab = id;

        switch (id) {
            case ID_MENU_1:
                tvMenu1.setTextColor(getColors(R.color.main_color));
                ivMenu1Icon.setImageResource(R.drawable.menu_slected);
                if (firstFragment == null) {
                    firstFragment = new MenuFirstFragment();
                    fragmentTransaction.add(R.id.main_fragment, firstFragment, TAGS[0]);
                } else {
                    fragmentTransaction.show(firstFragment);
                }
                break;
            case ID_MENU_2:

                tvMenu2.setTextColor(getColors(R.color.main_color));
                ivMenu2Icon.setImageResource(R.drawable.menu_slected);
                if (secondFragment == null) {
                    secondFragment = new MenuSecondFragment();
                    fragmentTransaction.add(R.id.main_fragment, secondFragment, TAGS[1]);
                } else {
                    fragmentTransaction.show(secondFragment);
                }
                break;
            case ID_MENU_3:

                tvMenu3.setTextColor(getColors(R.color.main_color));
                ivMenu3Icon.setImageResource(R.drawable.menu_slected);
                if (thirdFragment == null) {
                    thirdFragment = new MenuThirdFragment();
                    fragmentTransaction.add(R.id.main_fragment, thirdFragment, TAGS[2]);
                } else {
                    fragmentTransaction.show(thirdFragment);
                }
                break;
            case ID_MENU_4:
                tvMenu4.setTextColor(getColors(R.color.main_color));
                ivMenu4Icon.setImageResource(R.drawable.menu_slected);
                if (fourthFragment == null) {
                    fourthFragment = new MenuFourthFragment();
                    fragmentTransaction.add(R.id.main_fragment, fourthFragment, TAGS[3]);
                } else {
                    fragmentTransaction.show(fourthFragment);
                }
                break;
            default:
                break;
        }

        fragmentTransaction.commit();

    }


    private void resetAll(FragmentTransaction fragmentTransaction) {
        tvMenu1.setTextColor(getColors(R.color.gray_main));
        tvMenu2.setTextColor(getColors(R.color.gray_main));
        tvMenu3.setTextColor(getColors(R.color.gray_main));
        tvMenu4.setTextColor(getColors(R.color.gray_main));

        ivMenu1Icon.setImageResource(R.drawable.menu_normal);
        ivMenu2Icon.setImageResource(R.drawable.menu_normal);
        ivMenu3Icon.setImageResource(R.drawable.menu_normal);
        ivMenu4Icon.setImageResource(R.drawable.menu_normal);


        if (firstFragment != null) {
            fragmentTransaction.hide(firstFragment);
        }

        if (secondFragment != null) {
            fragmentTransaction.hide(secondFragment);
        }

        if (thirdFragment != null) {
            fragmentTransaction.hide(thirdFragment);
        }

        if (fourthFragment != null) {
            fragmentTransaction.hide(fourthFragment);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.menu_1:
                setSlectedTab(ID_MENU_1);
                break;
            case R.id.menu_2:
                setSlectedTab(ID_MENU_2);
                break;
            case R.id.menu_3:
                setSlectedTab(ID_MENU_3);
                break;
            case R.id.menu_4:
                setSlectedTab(ID_MENU_4);
                break;
            default:
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_TAB, currentTab);
        super.onSaveInstanceState(outState);
    }
}
