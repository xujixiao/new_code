package com.test.xujixiao.xjx.fragment;

import android.os.Bundle;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.custom_view.SelectAmountView;

/**
 * Created by xujixiao on 2016/12/20.11:26
 * 邮箱：1107313740@qq.com
 */

public class SignViewFragment extends BaseFragment {
    private SelectAmountView mSignDaysView;

    public static SignViewFragment newInstance() {
        Bundle args = new Bundle();
        SignViewFragment fragment = new SignViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.sign_day_layout;
    }

    @Override
    public void initData() {
        mView.setOnTouchListener(this);
        mSignDaysView = (SelectAmountView) mView.findViewById(R.id.sign_view);
    }
}
