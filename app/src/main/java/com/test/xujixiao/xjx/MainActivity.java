package com.test.xujixiao.xjx;

import android.view.View;

import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.common.adapter.Test.SingleAdapterTestActivity;
import com.test.xujixiao.xjx.fragment.TestFragment;


public class MainActivity extends BaseActivity {


    @Override
    public int getViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        findViewById(R.id.single_adapter_test).setOnClickListener(this);
    }

    @Override
    protected void parseIntent() {

    }

    @Override
    protected void initData() {
        addFragment(TestFragment.newInstance());
    }

    @Override
    public int getContentId() {
        return R.id.content_frame_layout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.single_adapter_test:
                SingleAdapterTestActivity.start(MainActivity.this);
                break;
        }
    }
}
