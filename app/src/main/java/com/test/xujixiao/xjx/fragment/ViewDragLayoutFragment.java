package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;

/**
 * Created by xujixiao on 2017/3/8.10:03
 * 邮箱：ji-xiao.xu@utsoft.cn
 */

public class ViewDragLayoutFragment extends BaseFragment {

    public static ViewDragLayoutFragment newInstance() {

        Bundle args = new Bundle();

        ViewDragLayoutFragment fragment = new ViewDragLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.view_drag_layout_fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
