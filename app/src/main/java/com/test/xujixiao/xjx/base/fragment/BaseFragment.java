package com.test.xujixiao.xjx.base.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.custom_view.LoadingProgress;
import com.test.xujixiao.xjx.util.TLog;


/**
 * Created by xujixiao on 2015/8/20.
 */
public class BaseFragment extends TFragment {

    protected TextView topLeftText, topRightText, topCenterText;
    protected TextView topHintText;
    protected LinearLayout topLayoutBack;
    protected LoadingProgress loadingProgress;
    private AlertDialog loginInvalidDialog;

    protected void getTopLayoutViewId(View mainView) {
        topCenterText = (TextView) mainView.findViewById(R.id.top_title_textview);
        topLeftText = (TextView) mainView.findViewById(R.id.top_left_textivew);
        topLayoutBack = (LinearLayout) mainView.findViewById(R.id.top_layout_back);
        if (topLayoutBack != null) {
            topLayoutBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TLog.log("layoutback_____点击");
                    showKeyboard(false);
                    topLeftBackLayoutClick();
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        }
        topRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topRightOnClick();
            }
        });
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
//        EventBus.getDefault().register(this);
        if (getActivity() != null) {
            loadingProgress = new LoadingProgress(getActivity());
            loadingProgress.setCancelable(true);
        }
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
