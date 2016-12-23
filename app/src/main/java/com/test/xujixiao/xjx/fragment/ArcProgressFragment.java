package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.custom_view.ArcProgressBarView;

/**
 * Created by xujixiao on 2016/12/15.10:50
 * 邮箱：1107313740@qq.com
 */

public class ArcProgressFragment extends BaseFragment {
    ArcProgressBarView mArcProgressBarView1;
    ArcProgressBarView mArcProgressBarView2;
    ArcProgressBarView mArcProgressBarView3;

    public static ArcProgressFragment newInstance() {

        Bundle args = new Bundle();

        ArcProgressFragment fragment = new ArcProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.arc_progress_layout;
    }

    @Override
    public void initData() {
        mArcProgressBarView1 = (ArcProgressBarView) mView.findViewById(R.id.arc_progress1);
        mArcProgressBarView2 = (ArcProgressBarView) mView.findViewById(R.id.arc_progress2);
        mArcProgressBarView3 = (ArcProgressBarView) mView.findViewById(R.id.arc_progress3);
        mArcProgressBarView1.setBackgroundColor(getResources().getColor(R.color.color_f2f2f2));
    }
}
