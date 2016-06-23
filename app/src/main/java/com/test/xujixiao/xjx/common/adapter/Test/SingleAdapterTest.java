package com.test.xujixiao.xjx.common.adapter.Test;

import android.content.Context;

import com.test.xujixiao.xjx.common.adapter.base.TAdapter;
import com.test.xujixiao.xjx.common.adapter.base.TAdapterDelegate;

import java.util.List;

/**
 * Created by xujixiao on 2016/6/20.17:08
 * 邮箱：1107313740@qq.com
 */
public class SingleAdapterTest extends TAdapter<Entity> {

    public SingleAdapterTest(Context context, List<Entity> items, TAdapterDelegate delegate,
                             ViewHolderEventListener viewHolderEventListener) {
        super(context, items, delegate);
        this.viewHolderEventListener = viewHolderEventListener;
    }

    public interface ViewHolderEventListener {
        void onItemClick(Entity entity);

        void onRemove(Entity entity);
    }

    private ViewHolderEventListener viewHolderEventListener;

    public ViewHolderEventListener getEventListener() {
        return viewHolderEventListener;
    }
}
