package com.test.xujixiao.xjx.adapter;

import android.content.Context;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.BaseRecyclerAdapter;
import com.test.xujixiao.xjx.base.RecyclerViewHolder;

import java.util.List;

/**
 * Created by xujixiao on 2016/11/17.11:05
 * 邮箱：1107313740@qq.com
 * 演示如何使用BaseRecyclerAdapter
 */

public class MainAdapter extends BaseRecyclerAdapter<String> {

    public MainAdapter(Context ctx, List<String> list) {
        super(ctx, list);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.main_adapter_item;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, String item) {
        holder.getTextView(R.id.text).setText(item);
    }
}
