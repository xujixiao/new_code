package com.test.xujixiao.xjx.custom_view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.CrashUtils;


public class LoadingProgress extends ProgressDialog {

    private static final int TIME_OUT = 10000;

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
        setIndeterminate(true);
        setCancelable(false);
        setMessage("请稍候……");
    }

    public void show(boolean timeOut) {
        try {
            if (!isShowing()) {
                setCancelable(false);
                show();
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
                super.dismiss();
            }
        } catch (Exception e) {
            CrashUtils.crash(e, "LoadingProgress:关闭异常");
        }
    }
}
