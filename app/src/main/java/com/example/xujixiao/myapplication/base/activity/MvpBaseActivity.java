package com.example.xujixiao.myapplication.base.activity;

import android.os.Bundle;

import com.example.xujixiao.myapplication.base.BaseActivity;
import com.example.xujixiao.myapplication.base.presenter.BasePresenter;

/**
 * Created by xujixiao on 2016/5/27.10:07
 * 邮箱：1107313740@qq.com
 * 一般的activity使用的mvp的基类
 */
public abstract class MvpBaseActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
        presenterOperate();
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    /**
     * 初始化presenter的对象
     *
     * @return
     */
    public abstract T initPresenter();

    /**
     * 执行presenter的操作
     */
    public abstract void presenterOperate();
}
