package com.test.xujixiao.xjx.base.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.base.fragment.AppFragment;

/**
 * Created by xujixiao on 2016/5/19.13:53
 * 邮箱：1107313740@qq.com
 * 流程式fragment封装baseactivity
 */
public abstract class NewBaseActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 获取顶级activity的布局layoud id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 获取置换fragment的viewgroup的id
     *
     * @return
     */
    protected abstract int getFragmentContentId();

    public void setFragmentNoAnim(AppFragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    public void addFragment(AppFragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            setChangeAnimation(fragmentTransaction);
            fragmentTransaction.add(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    protected void setChangeAnimation(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_from_left, R.anim.in_from_left,
                R.anim.out_from_right);
    }

    /**
     * 如果希望改变fragment的切换的动画样式可以
     */
//    protected void bottomToTopAnim(FragmentTransaction fragmentTransaction) {
//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top,
//
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
