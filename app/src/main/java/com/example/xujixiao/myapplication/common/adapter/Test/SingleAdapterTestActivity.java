package com.example.xujixiao.myapplication.common.adapter.Test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xujixiao.myapplication.R;
import com.example.xujixiao.myapplication.base.BaseActivity;
import com.example.xujixiao.myapplication.common.adapter.base.TAdapterDelegate;
import com.example.xujixiao.myapplication.common.adapter.base.TViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujixiao on 2016/6/20.16:48
 * 邮箱：1107313740@qq.com
 * activity表现了单个viewholder类型的使用方法
 */
public class SingleAdapterTestActivity extends BaseActivity implements TAdapterDelegate, View.OnClickListener {
    private ListView mListView;
    private SingleAdapterTest singleAdapterTest;
    private List<Entity> mEntities = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, SingleAdapterTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adapter_test_activity);
        init();
    }

    @Override
    protected void findView() {
        mListView = findView(R.id.my_listview);
    }

    @Override
    protected void parseIntent() {

    }

    @Override
    protected void initData() {
        singleAdapterTest = new SingleAdapterTest(this, mEntities, this, null);
        mListView.setAdapter(singleAdapterTest);

        mEntities.add(new Entity("xujixiao"));
        mEntities.add(new Entity("xujixiao"));
        mEntities.add(new Entity("xujixiao"));
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
