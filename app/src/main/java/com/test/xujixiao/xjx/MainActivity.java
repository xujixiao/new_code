package com.test.xujixiao.xjx;

import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.fragment.MainFragment;


public class MainActivity extends BaseActivity {


    @Override
    public int getViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        addFragment(MainFragment.newInstance());
    }

    @Override
    protected void parseIntent() {

    }

    @Override
    public int getContentId() {
        return R.id.content_frame_layout;
    }
}
