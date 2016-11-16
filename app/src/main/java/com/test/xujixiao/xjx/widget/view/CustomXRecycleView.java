package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 */
public class CustomXRecycleView extends XRecyclerView {
    public CustomXRecycleView(Context context) {
        super(context);
    }

    public CustomXRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
