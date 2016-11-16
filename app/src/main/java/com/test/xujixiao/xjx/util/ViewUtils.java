package com.test.xujixiao.xjx.util;

import android.widget.ScrollView;


/**
 * Created by xujixiao on 2016/8/15.10:34
 * 邮箱：1107313740@qq.com
 * scrollview滑动到最上面
 */
public class ViewUtils {
    public static void scrollTop(ScrollView mScrollView) {
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);
        mScrollView.requestFocus();
        mScrollView.scrollTo(0, 0);
    }

}
