package com.example.xujixiao.myapplication.common.adapter.Test;

import android.widget.TextView;

import com.example.xujixiao.myapplication.R;
import com.example.xujixiao.myapplication.common.adapter.base.TViewHolder;

/**
 * Created by xujixiao on 2016/6/20.17:12
 * 邮箱：1107313740@qq.com
 */
public class SingleAdapterViewHolder extends TViewHolder {
    private TextView tvName;
    private Entity entity;

    @Override
    protected int getResId() {
        return R.layout.layout_item_listview;
    }

    @Override
    protected void inflate() {
        tvName = findView(R.id.textView);

    }

    @Override
    protected void refresh(Object item) {
        entity = (Entity) item;
        tvName.setText(entity.name);
    }
}
