package com.test.xujixiao.xjx.common.adapter.Test;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.common.adapter.base.TAdapterDelegate;
import com.test.xujixiao.xjx.common.adapter.base.TViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xujixiao on 2016/6/20.16:48
 * 邮箱：1107313740@qq.com
 * activity表现了单个viewholder类型的使用方法
 */
public class SingleAdapterTestActivity extends BaseActivity implements TAdapterDelegate, View.OnClickListener {
    @BindView(R.id.my_listview)
    ListView mListView;
    private SingleAdapterTest singleAdapterTest;
    private List<Entity> mEntities = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, SingleAdapterTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.layout_adapter_test_activity;
    }

    @Override
    public int getContentId() {
        return R.id.content_frame_layout;
    }

    @Override
    protected void parseIntent() {

    }

    @Override
    protected void initData() {
        singleAdapterTest = new SingleAdapterTest(this, mEntities, this, null);
        mListView.setAdapter(singleAdapterTest);

        mEntities.add(new Entity("测试一"));
        mEntities.add(new Entity("测试一"));
        mEntities.add(new Entity("测试一"));
        singleAdapterTest.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return SingleAdapterViewHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return false;
    }
}
