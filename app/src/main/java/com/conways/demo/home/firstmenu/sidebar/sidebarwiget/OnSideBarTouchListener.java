package com.conways.demo.home.firstmenu.sidebar.sidebarwiget;

/**
 * Created by Sai on 16/3/25.
 */
public interface OnSideBarTouchListener {
    void onLetterChanged(String letter, int position, int itemHeight);
    void onLetterTouching(boolean touching);
}
