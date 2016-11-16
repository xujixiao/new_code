package com.test.xujixiao.xjx.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/**
 * Created by xujixiao on 2016/8/2.17:44
 * 邮箱：1107313740@qq.com
 */
public class AnimUtils {

    /**
     * 对某一个view执行动画通过加载一个动画资源id
     */
    public static void runAnim(Context context, final View view, int animId, final MyViewAnimListener animationListener) {
        if (context == null)
            return;
        Animation animation = AnimationUtils.loadAnimation(context, animId);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                if (animationListener != null) {
                    animationListener.onAnimationEnd(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationRepeat(animation);
                }
            }

            @Override
            public void onAnimationStart(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
            }
        });
        view.startAnimation(animation);
    }


    /**
     * 对一个view执行属性动画缩放变小，并透明
     *
     * @param mainView
     * @param myAnimListener
     */
    public static void runAnimView(final View mainView, final MyAnimListener myAnimListener) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(mainView, "scale_type", 1.0F, 0.0F)//
                .setDuration(300);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                mainView.setAlpha(cVal);
                mainView.setScaleX(cVal);
                mainView.setScaleY(cVal);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (myAnimListener != null) {
                    myAnimListener.onAnimationStart(animator);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (myAnimListener != null) {
                    myAnimListener.onAnimationEnd(animator);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                if (myAnimListener != null) {
                    myAnimListener.onAnimationCancel(animator);
                }

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                if (myAnimListener != null) {
                    myAnimListener.onAnimationRepeat(animator);
                }

            }
        });

    }


}
