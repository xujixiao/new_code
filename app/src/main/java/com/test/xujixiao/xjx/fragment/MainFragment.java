package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.adapter.MainAdapter;
import com.test.xujixiao.xjx.base.BaseRecyclerAdapter;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.common.adapter.Test.SingleAdapterTestActivity;
import com.test.xujixiao.xjx.constants.ChangeAnimType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xujixiao on 2016/11/17.10:04
 * 邮箱：1107313740@qq.com
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.xrecyclerview)
    XRecyclerView mXrecyclerview;
    private List<String> mList = new ArrayList<>();
    private MainAdapter mMainAdapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        mList.add("网易云信封装adapter的应用");
        mList.add("Realm数据库用法示例");
        mList.add("xujixiao");
        mList.add("xujixiao");
        mList.add("xujixiao");
        mMainAdapter = new MainAdapter(mBaseActivity, mList);
        mXrecyclerview.setLayoutManager(new LinearLayoutManager(mBaseActivity));
        mXrecyclerview.setLoadingMoreEnabled(false);
        mXrecyclerview.setPullRefreshEnabled(false);
        mXrecyclerview.setAdapter(mMainAdapter);
        mMainAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                switch (pos) {
                    case 1:
                        SingleAdapterTestActivity.start(mBaseActivity);
                        break;
                    case 2:
                        mBaseActivity.addFragment(RealmFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base;
    }
}
