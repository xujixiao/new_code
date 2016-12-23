package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.widget.ReboundScrollView;

import butterknife.BindView;

/**
 * Created by xujixiao on 2016/11/23.16:05
 * 邮箱：1107313740@qq.com
 */

public class ReboundScrollViewFragment extends BaseFragment {
    @BindView(R.id.test_match_layout)
    LinearLayout mTestMatchLayout;
    @BindView(R.id.rebound_view)
    ReboundScrollView mReboundScrollView;

    public static ReboundScrollViewFragment newInstance() {
        Bundle args = new Bundle();
        ReboundScrollViewFragment fragment = new ReboundScrollViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rebound_scrollview;
    }

    @Override
    public void initData() {
//        ViewGroup.LayoutParams layoutParams = mTestMatchLayout.getChildAt(0).getLayoutParams();
//        layoutParams.height = ScreenUtil.screenHeight;
//        mTestMatchLayout.getChildAt(0).setLayoutParams(layoutParams);
//        ViewGroup.LayoutParams layoutParams1 = mReboundScrollView.getLayoutParams();
//        layoutParams.height = ScreenUtil.screenHeight + mTestMatchLayout.getChildAt(1).getHeight();
//        mReboundScrollView.setLayoutParams(layoutParams1);
    }
}
