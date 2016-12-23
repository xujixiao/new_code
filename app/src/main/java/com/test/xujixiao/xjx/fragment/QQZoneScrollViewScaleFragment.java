package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.custom_view.FlexibleScrollView;

/**
 * Created by xujixiao on 2016/12/15.09:54
 * 邮箱：1107313740@qq.com
 */

public class QQZoneScrollViewScaleFragment extends BaseFragment {
    private FlexibleScrollView mScrollView;

    public static QQZoneScrollViewScaleFragment newInstance() {

        Bundle args = new Bundle();

        QQZoneScrollViewScaleFragment fragment = new QQZoneScrollViewScaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.qq_scrollview_test_layout;
    }

    @Override
    public void initData() {
        mScrollView = (FlexibleScrollView) mView.findViewById(R.id.flexible_scroll_vew);
        mScrollView.bindActionBar(mView.findViewById(R.id.custom_action_bar));
        mScrollView.setHeaderView(mView.findViewById(R.id.flexible_header_view));
    }
}
