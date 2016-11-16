package com.test.xujixiao.xjx.util;

import android.view.View;

/**
 * Created by xujixiao on 2016/8/4.13:42
 * 邮箱：1107313740@qq.com
 */
public class ViewWrapper {
    private View tagetView;

    public ViewWrapper(View tagetView) {
        this.tagetView = tagetView;
    }

    public int getWidth() {
        return this.tagetView.getLayoutParams().width;
    }

    public void setWidth(int width) {
        if (tagetView != null) {
            tagetView.getLayoutParams().width = width;
            tagetView.requestLayout();
        }
    }
}
