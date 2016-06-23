package com.test.xujixiao.xjx.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;


/**
 * 最高级的继承activity类，含有多种功能操作
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    /*顶部的控件*/
    protected TextView topLeftText, topRightText, topCenterText, topLeftHint;
    protected LinearLayout topLayoutBack;


    private boolean destroyed = false;
    private static Handler handler;

    private boolean isVisible;
    private AlertDialog loginInvalidDialog;
    private DialogInterface.OnDismissListener listener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                progressDismissListener();
            }
        };
        isVisible = true;
    }

    protected void progressDismissListener() {

    }

    protected String removeString(String value) {
        return value.replace("\r\n", "");
    }

    protected void init() {
        parseIntent();
        findView();
        initData();
    }

    protected abstract void findView();

    protected abstract void parseIntent();

    protected abstract void initData();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        destroyed = true;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void getTopLayoutViewId() {
        topCenterText = findView(R.id.top_title_textview);
        topLeftText = findView(R.id.top_left_textivew);
        topLayoutBack = findView(R.id.top_layout_back);
        if (topLayoutBack != null) {
            topLayoutBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showKeyboard(false);
                    finish();
                }
            });
        }
    }

    public void setTitle(CharSequence title) {
        getTopLayoutViewId();
        if (topCenterText != null) {
            topCenterText.setText(title);
        }
    }

    public void setTitle(int titleResId) {
        getTopLayoutViewId();
        if (topCenterText != null) {
            topCenterText.setText(titleResId);
        }
    }

    protected final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }


    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                hideInputMethod(getWindow().getDecorView());
            }
        }
    }

    protected void hideInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 延时弹出键盘
     *
     * @param focus 键盘的焦点项
     */
    protected void showKeyboardDelayed(View focus) {
        final View viewToFocus = focus;
        if (focus != null) {
            focus.requestFocus();
        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewToFocus == null || viewToFocus.isFocused()) {
                    showKeyboard(true);
                }
            }
        }, 200);
    }


    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                return onMenuKeyDown();

            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    protected boolean onMenuKeyDown() {
        return false;
    }


    /*如果调用了settitle不用再调用此方法*/
    protected void findViewId() {
        topCenterText = findView(R.id.top_title_textview);
        topLeftText = findView(R.id.top_left_textivew);
        topLayoutBack = findView(R.id.top_layout_back);
        if (topLayoutBack != null) {
            topLayoutBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }


    protected void setHintText(String text) {
        if (topLeftHint != null) {
            topLeftHint.setText(text);
        }
    }


    /**
     * 设置右侧功能
     *
     * @param text
     * @param listener
     */
    protected void setRightText(String text, View.OnClickListener listener) {
        topRightText.setText(text);
        topRightText.setVisibility(View.VISIBLE);
        topRightText.setOnClickListener(listener);
    }


}
