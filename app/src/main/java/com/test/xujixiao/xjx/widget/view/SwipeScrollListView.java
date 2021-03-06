package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.test.xujixiao.xjx.widget.view.swipe_listview.SwipeListView;

/**
 */
public class SwipeScrollListView extends SwipeListView {
    public SwipeScrollListView(Context context, int swipeBackView, int swipeFrontView) {
        super(context, swipeBackView, swipeFrontView);
    }

    public SwipeScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
