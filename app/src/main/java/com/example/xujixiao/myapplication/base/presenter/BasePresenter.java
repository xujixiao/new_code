package com.example.xujixiao.myapplication.base.presenter;

/**
 * Created by xujixiao on 2016/5/27.09:41
 * 邮箱：1107313740@qq.com
 */
public class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        this.mView = null;
    }
}
