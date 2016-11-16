package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.tailorx.R;
import cn.tailorx.protocol.ViewStatusProtocol;


public class LoadDataView extends FrameLayout {

    /**
     * 数据加载异常
     */
    private final View errorView;
    /**
     * 没有数据
     */
    private final View noDataView;
    /**
     * 数据
     */
    private final View dataView;
    /**
     * 加载数据中
     */
    private final View loadingView;
    /**
     * 网络连接失败
     */
    private final View netErrorView;
    private final LayoutInflater inflater;
    private final ImageView loadingImg;
    //    private final Animation mImageViewAnimation;
    private volatile boolean isFirstLoad = true;
    private TextView noDataTextView;
    private ImageView likeImageView;
    private Context mContext;

    public LoadDataView(Context context, View view) {
        super(context);
        this.mContext = context;
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        dataView = view;
        inflater = LayoutInflater.from(context);
        netErrorView = inflater.inflate(R.layout.base_net_unusual_layout1, null);
        errorView = inflater.inflate(R.layout.base_error_unusual_layout, null);
        noDataView = inflater.inflate(R.layout.base_no_data_layout1, null);
        loadingView = inflater.inflate(R.layout.base_loading_data_layout, null);
        loadingImg = (ImageView) loadingView.findViewById(R.id.loading_img);
        loadingImg.setImageResource(R.drawable.frame);
        AnimationDrawable animationDrawable = (AnimationDrawable) loadingImg.getDrawable();
        animationDrawable.start();
//        mImageViewAnimation = AnimationUtils.loadAnimation(context, R.anim.rote);
//        mImageViewAnimation.setInterpolator(new LinearInterpolator());
//        mImageViewAnimation.setDuration(2000);
//        mImageViewAnimation.setRepeatCount(Animation.INFINITE);
//        mImageViewAnimation.setRepeatMode(Animation.RESTART);
//        GlideUtils.displayDefault(mContext, R.drawable.loading_img, loadingImg);
        initViews();
    }

    private void initViews() {
        if (null != dataView) {
            addView(dataView);
        }
        if (null != errorView) {
            addView(errorView);
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView) {
            addView(netErrorView);
            netErrorView.setVisibility(View.GONE);
        }
        if (null != loadingView) {
            addView(loadingView);
            loadingView.setVisibility(View.GONE);
        }
        if (null != noDataView) {
            noDataTextView = (TextView) noDataView.findViewById(R.id.data_loading_textview);
//			likeImageView = (ImageView) noDataView.findViewById(R.id.like);

            addView(noDataView);
            noDataView.setVisibility(View.GONE);
        }


    }

    public void stop() {
        if (null != loadingImg) {
//            loadingImg.clearAnimation();
        }
    }

    public void start() {
        stop();
        if (null != loadingImg) {
//            loadingImg.startAnimation(mImageViewAnimation);

        }
    }

    /**
     * 开始加载
     */
    public void loadStart() {
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.VISIBLE) {
            start();
            loadingView.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 加载成功
     */
    public void loadSuccess() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.VISIBLE) {
            dataView.setVisibility(View.VISIBLE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 加载失败
     */
    public void loadError() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.VISIBLE) {
            errorView.setVisibility(View.VISIBLE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 加载成功，但无数据
     */
    public void loadNoData() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.VISIBLE) {
            noDataView.setVisibility(View.VISIBLE);
        }
        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 网络连接问题，加载异常，检查网络，点击屏幕重新连接
     */
    public void loadNotNetwork() {
        stop();

        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.VISIBLE) {
            netErrorView.setVisibility(View.VISIBLE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void setErrorListner(View.OnClickListener listener) {
        if (null == listener) {
            return;
        }
        if (null != errorView) {

            errorView.setOnClickListener(listener);
        }
        if (null != netErrorView) {

            netErrorView.setOnClickListener(listener);
        }
        if (null != noDataView) {

            noDataView.setOnClickListener(listener);
        }

    }

    public void changeStatusView(ViewStatusProtocol status) {
        if (isFirstLoad) {
            switch (status) {
                case START:
                    loadStart();
                    break;
                case SUCCESS:
                    isFirstLoad = false;
                    loadSuccess();
                    break;
                case FAILURE:
                    loadError();
                    break;
                case EMPTY:
                    loadNoData();
                    break;
                case NOTNETWORK:
                    loadNotNetwork();
                    break;
            }
        }
    }

    public LoadDataView setNoDataText(String text) {
        if (null != noDataTextView) {
            noDataTextView.setText(text);
        }
        return this;
    }

    public void setFirstLoad() {
        isFirstLoad = true;
    }

    public LoadDataView setLikeImageResource(int resId) {
        if (null != likeImageView) {
            likeImageView.setImageResource(resId);
        }
        return this;
    }


}
