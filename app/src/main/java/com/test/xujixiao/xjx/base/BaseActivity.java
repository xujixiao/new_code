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
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.apkfuns.logutils.LogUtils;
import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.fragment.BaseFragment;
import com.test.xujixiao.xjx.constants.ChangeAnimType;
import com.test.xujixiao.xjx.widget.LoadingProgress;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;


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
    private LoadingProgress mLoadingProgress;
    private DialogInterface.OnDismissListener listener;
    private FragmentManager mFragmentManager;
    private Realm mRealm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayoutId());
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mRealm = Realm.getDefaultInstance();
        mFragmentManager = getSupportFragmentManager();
        listener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                progressDismissListener();
            }
        };
        isVisible = true;
        init();
    }

    public abstract int getViewLayoutId();

    protected void progressDismissListener() {

    }

    private void init() {
        parseIntent();
        initData();
    }

    protected abstract void parseIntent();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        destroyed = true;
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
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

    /**
     * 切换布局 默认加入回退
     *
     * @param fragment
     * @param animType
     * @return
     */
    public BaseFragment replaceFragment(BaseFragment fragment, int animType) {
        return replaceFragment(fragment, animType, true);
    }

    /**
     * 切换布局
     *
     * @param fragment
     * @param animType
     * @param isInPop  是否加入回退堆栈
     * @return
     */
    public BaseFragment replaceFragment(BaseFragment fragment, int animType, boolean isInPop) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        setAnimation(animType, transaction);
        transaction.replace(getContentId(), fragment);
        if (isInPop) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            LogUtils.d(e.toString());
        }
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        removeFragment();
    }

    public void removeFragment() {
        if (mLoadingProgress != null && mLoadingProgress.isShowing()) {
            mLoadingProgress.dismiss();
        }
        if (mFragmentManager == null) {
            return;
        }
        LogUtils.d("回退fragment count " + mFragmentManager.getBackStackEntryCount());
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
