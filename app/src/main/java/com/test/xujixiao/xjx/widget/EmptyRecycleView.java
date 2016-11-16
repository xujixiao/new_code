package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xujixiao on 2016/9/8.09:19
 * 邮箱：1107313740@qq.com
 * 可以设置空布局的recycleview
 */
public class EmptyRecycleView extends RecyclerView {
    private View emptyView;

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {


        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    EmptyRecycleView.this.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    EmptyRecycleView.this.setVisibility(View.VISIBLE);
                }
            }

        }
    };

    public EmptyRecycleView(Context context) {
        super(context);
    }

    public EmptyRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }
}
