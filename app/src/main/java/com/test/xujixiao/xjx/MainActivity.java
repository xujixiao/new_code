package com.test.xujixiao.xjx;

import android.os.Bundle;
import android.view.View;

import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.common.adapter.Test.SingleAdapterTestActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
