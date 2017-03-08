package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.activity.ViewDragLayoutActivity;
import com.test.xujixiao.xjx.adapter.MainAdapter;
import com.test.xujixiao.xjx.base.BaseRecyclerAdapter;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.common.adapter.Test.SingleAdapterTestActivity;
import com.test.xujixiao.xjx.constants.ChangeAnimType;
import com.test.xujixiao.xjx.util.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import swipedemo.MyActivity;

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
        mList.add("StickyFragmen演示");
        mList.add("rebound-scrollview-Fragment演示");
        mList.add("swiplayout演示");
        mList.add("仿qq控件下拉放大演示");
        mList.add("弧形progress演示");
        mList.add("签到控件修改演示");
        mList.add("viewdrag的用法示例");
        mMainAdapter = new MainAdapter(mBaseActivity, mList);
        RecyclerUtils.linearDefault(mBaseActivity, mXrecyclerview);
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
                    case 3:
                        mBaseActivity.addFragment(StickyFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                    case 4:
                        mBaseActivity.addFragment(ReboundScrollViewFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                    case 5:
                        MyActivity.start(getActivity());
                        break;
                    case 6:
                        mBaseActivity.addFragment(QQZoneScrollViewScaleFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                    case 7:
                        mBaseActivity.addFragment(ArcProgressFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                    case 8:
                        mBaseActivity.addFragment(SignViewFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
                        break;
                    case 9:
                        mBaseActivity.addFragment(ViewDragLayoutFragment.newInstance(), ChangeAnimType.LEFT_RIGHT);
//                        ViewDragLayoutActivity.start(getActivity());
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
