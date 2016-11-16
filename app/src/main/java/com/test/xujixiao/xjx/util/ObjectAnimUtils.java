package com.test.xujixiao.xjx.util;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by xujixiao on 2016/8/4.14:35
 * 邮箱：1107313740@qq.com
 */
public class ObjectAnimUtils {
    public static void translateXView(View view, float from, float to) {
        ObjectAnimator.ofFloat(view, "translationX", from, to).setDuration(300).start();
    }

    public static void translateYView(View view, float from, float to) {
        ObjectAnimator.ofFloat(view, "translationY", from, to).setDuration(300).start();
    }

    public static void scaleXView(View view, float from, float to) {
        ObjectAnimator.ofFloat(view, "scaleX", from, to).setDuration(300).start();
    }

    public static void scaleYView(View view, float from, float to) {
        ObjectAnimator.ofFloat(view, "scaleY", from, to).setDuration(300).start();
    }

    public static void rorateXView(View view, float from, float to) {
        ObjectAnimator.ofFloat(view, "rotationX", from, to).setDuration(300).start();
    }
}
