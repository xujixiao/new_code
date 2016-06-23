package com.test.xujixiao.xjx.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.test.xujixiao.xjx.base.activity.NewBaseActivity;


/**
 * Created by xujixiao on 2016/5/19.14:10
 * 邮箱：1107313740@qq.com
 */
public abstract class AppFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {
    protected NewBaseActivity mActivity;

    protected abstract void initView(View view, Bundle saveInstanceState);

    protected abstract int getLayoutId();

    protected NewBaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (NewBaseActivity) activity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }
}
