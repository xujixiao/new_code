package com.test.xujixiao.xjx.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.CrashUtils;


public class LoadingProgress extends Dialog {

    private static final int TIME_OUT = 10000;
    private final ImageView imageView = null;

    private Handler mainHandler = new Handler();

    private Runnable timeRunnable = new Runnable() {

        @Override
        public void run() {
            /*10秒之后如果，还在显示设置可取消*/
            if (isShowing()) {
                setCancelable(true);
                dismiss();
            }

            mainHandler.removeCallbacks(timeRunnable);
        }
    };

    public LoadingProgress(Context context) {
        super(context, R.style.dialogTheme);
//        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
//        setContentView(view);
//        imageView = (ImageView) view.findViewById(R.id.img);
//        imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        show(true);
    }

    public void show(boolean timeOut) {
        try {
            if (!isShowing()) {
                setCancelable(false);
                show();
                imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rote));
                if (timeOut) {
                    mainHandler.postDelayed(timeRunnable, TIME_OUT);
                }
            }
        } catch (Exception e) {
            CrashUtils.crash(e, "LoadingProgress:show异常");
        }
    }

    @Override
    public void dismiss() {
        mainHandler.removeCallbacks(timeRunnable);
        try {
            if (isShowing()) {
                imageView.clearAnimation();
                super.dismiss();
            }
        } catch (Exception e) {
            CrashUtils.crash(e, "LoadingProgress:关闭异常");
        }
    }
}
