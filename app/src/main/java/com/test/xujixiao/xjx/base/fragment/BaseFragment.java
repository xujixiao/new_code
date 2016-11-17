package com.test.xujixiao.xjx.base.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.base.BaseActivity;
import com.test.xujixiao.xjx.custom_view.LoadingProgress;
import com.test.xujixiao.xjx.util.TLog;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;


/**
 * Created by xujixiao on 2015/8/20.
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {

    protected TextView topLeftText, topRightText, topCenterText;
    protected TextView topHintText;
    protected LinearLayout topLayoutBack;
    protected LoadingProgress loadingProgress;
    private AlertDialog loginInvalidDialog;
    protected BaseActivity mBaseActivity;
    private View mView;
    private Unbinder mUnbinder;
    protected Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        getTopLayoutViewId();
        return mView;
    }

    public abstract int getLayoutId();

    protected void getTopLayoutViewId() {
        topCenterText = (TextView) mView.findViewById(R.id.top_title_textview);
        topLeftText = (TextView) mView.findViewById(R.id.top_left_textivew);
        topLayoutBack = (LinearLayout) mView.findViewById(R.id.top_layout_back);
        if (topLayoutBack != null) {
            topLayoutBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TLog.log("layoutback_____点击");
                    topLeftBackLayoutClick();
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        }
        if (topRightText != null) {
            topRightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topRightOnClick();
                }
            });
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    protected void topRightOnClick() {
    }


    protected void topLeftBackLayoutClick() {

    }

    /**
     * 切换fragment
     *
     * @param fragment
     * @param args
     */
    protected void changeFragment(Fragment fragment, Bundle args, int id) {
        // 判断传递参数是否为空,如果不为空,设置到fragment上
        if (args != null) {
            fragment.setArguments(args);
        }
        getFragmentManager().beginTransaction().addToBackStack(null).replace(id, fragment).commit();
    }

    /**
     * 切换fragment
     * 关闭后返回结果
     *
     * @param fragment
     * @param args     需要传递的参数
     */
    protected void changeFragmentReturn(Fragment fragment, Bundle args, int id) {
        // 判断传递参数是否为空,如果不为空,设置到fragment上
        if (args != null) {
            fragment.setArguments(args);
        }
        getFragmentManager().beginTransaction().addToBackStack(null).hide(BaseFragment.this).add(id, fragment).commit();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            EventBus.getDefault().register(this);
            mRealm = Realm.getDefaultInstance();
            mBaseActivity = (BaseActivity) getActivity();
            loadingProgress = new LoadingProgress(getActivity());
            loadingProgress.setCancelable(true);
            initData();
        }
    }


    public abstract void initData();


    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void progressShow() {
        if (getActivity() != null) {
            try {
                if (loadingProgress != null) {
                    if (!loadingProgress.isShowing()) {
                        loadingProgress.show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void progressDismiss() {
        if (getActivity() != null) {
            try {
                if (loadingProgress != null) {
                    if (loadingProgress.isShowing()) {
                        loadingProgress.dismiss();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置返回的文字显示
     *
     * @param text
     */
    protected void setHintText(String text) {
        if (topHintText != null) {
            topHintText.setText(text);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        progressDismiss();
    }

    @Override
    public void onDestroy() {
        progressDismiss();
//        EventBus.getDefault().unregister(this);
        super.onDestroy();

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
