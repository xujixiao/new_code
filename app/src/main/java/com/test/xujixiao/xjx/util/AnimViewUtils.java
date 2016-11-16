package com.test.xujixiao.xjx.util;

import android.animation.ObjectAnimator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;


/**
 * Created by xujixiao on 2016/8/2.17:52
 * 邮箱：1107313740@qq.com
 */
public class AnimViewUtils {


    //    public static void setProgressAnim(ProgressBar mProgressBar1, int progress, int width,) {
//        setProgressAnim(mProgressBar1, progress, 1500, width);
//    }
    public static void setProgressAnim(ProgressBar mProgressBar1, int progress, long duration, int width) {
        setProgressAnim(mProgressBar1, progress, duration, width, false);
    }

    public static void setProgressAnim(ProgressBar mProgressBar1, int progress, long duration, int width, boolean clear) {
        if (mProgressBar1.getAnimation() != null) {
            mProgressBar1.clearAnimation();
        }
        if (clear) {
            mProgressBar1.setProgress(0);
        }
        /*背景变化的动画*/
//        ViewWrapper viewWrapper1 = new ViewWrapper(mProgressBar1);
//        viewWrapper1.setWidth(0);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewWrapper1, "width", width);
//        objectAnimator.setInterpolator(new DecelerateInterpolator());
//        objectAnimator.setDuration(duration).start();
//        mProgressBar1.setProgress(0);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(mProgressBar1, "progress", progress);
        objectAnimator1.setDuration(duration);
        objectAnimator1.setInterpolator(new DecelerateInterpolator());
        objectAnimator1.start();
    }
}
