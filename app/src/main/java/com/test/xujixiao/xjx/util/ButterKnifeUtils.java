package com.test.xujixiao.xjx.util;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by xujixiao on 2016/11/15.13:58
 * 邮箱：1107313740@qq.com
 */

public class ButterKnifeUtils {
    public static final ButterKnife.Action<View> disable = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled(false);
        }
    };
    public static final ButterKnife.Action<View> enable = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            view.setEnabled(true);
        }
    };
}
