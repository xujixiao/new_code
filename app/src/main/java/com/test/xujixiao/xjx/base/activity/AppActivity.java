package com.test.xujixiao.xjx.base.activity;

import android.content.Intent;
import android.os.Bundle;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.AppFragment;
import com.test.xujixiao.xjx.utils.TLog;


/**
 * Created by xujixiao on 2016/5/19.13:59
 * 邮箱：1107313740@qq.com
 */
public abstract class AppActivity extends NewBaseActivity {
    protected abstract AppFragment getFirstFragment();

    protected void handleIntent(Intent intent) {
        TLog.log(intent.getExtras() != null ? intent.getExtras().toString() : "intent null");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        getTopLayoutViewId();
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        if (null == getSupportFragmentManager().getFragments()) {
            AppFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                setFragmentNoAnim(firstFragment);
            }
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }
}
