package com.test.xujixiao.xjx.util;

import android.support.v4.widget.DrawerLayout;
import android.view.View;


/**
 * Created by Administrator on 2016/7/27.
 */
public class DrawerListener implements DrawerLayout.DrawerListener {

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        drawerView.setClickable(true);
    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
