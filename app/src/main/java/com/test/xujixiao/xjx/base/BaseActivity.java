package com.test.xujixiao.xjx.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.constants.ChangeAnimType;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 最高级的继承activity类，含有多种功能操作
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    /*顶部的控件*/
    private boolean destroyed = false;
    private static Handler handler;
    private boolean isVisible;
    private Unbinder mUnbinder;
    private AlertDialog loginInvalidDialog;
    private DialogInterface.OnDismissListener listener;
    private FragmentManager mFragmentManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayoutId());
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        listener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                progressDismissListener();
            }
        };
        isVisible = true;
    }

    public abstract int getViewLayoutId();

    protected void progressDismissListener() {

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
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
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

    protected boolean isCompatible(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }


    public abstract int getContentId();

    /**
     * 添加fragment不执行动画
     *
     * @param baseFragment
     */
    public void addFragment(BaseFragment baseFragment) {
        addFragment(baseFragment, -100);
    }

    /**
     * 添加fragment并指定的动画类型
     *
     * @param baseFragment
     * @param animType
     */
    public void addFragment(BaseFragment baseFragment, int animType) {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        setAnimation(animType, transaction);
        transaction.add(getContentId(), baseFragment)
                .addToBackStack(baseFragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 执行fragment加载的动画
     *
     * @param animType
     * @param transaction
     */
    private void setAnimation(int animType, FragmentTransaction transaction) {
        if (animType == ChangeAnimType.LEFT_RIGHT) {
            /*左右方向*/
            transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_from_left, R.anim.in_from_left,
                    R.anim.out_from_right);
        } else if (animType == ChangeAnimType.SCALE) {
            /*缩放动画*/
            transaction.setCustomAnimations(R.anim.scale_to_one, R.anim.scale_to_zero);
        } else if (animType == ChangeAnimType.TOP_TO_LOW) {
            /*从顶部向下*/
            transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_top, R.anim.slide_in, R.anim.slide_out_to_top);
        } else if (animType == ChangeAnimType.LOW_TO_TOP_) {
            /*从底部向上*/
            transaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom, R.anim.slide_in, R.anim.slide_out_to_bottom);
        }
    }
}
