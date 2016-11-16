package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.tailorx.R;
import cn.tailorx.appoint.StoreDetailActivity;
import cn.tailorx.protocol.StoreListProtocol;
import cn.tailorx.utils.GlideUtils;
import cn.tailorx.utils.Tools;

/**
 * Created by xujixiao on 2016/7/27.13:48
 * 邮箱：1107313740@qq.com
 */
public class MapPopWindow extends PopupWindow {

    private Context mContext;
    private Unbinder mUnbinder;
    private Holder mHolder;
    private StoreListProtocol.DataBean mDataBean;

    public MapPopWindow(Context context) {
        super(context);
        this.mContext = context;
        init();
    }


    public void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_marker, null);
        setContentView(view);
        this.mHolder = new Holder(view);
        this.mHolder.mRbStarGrade = (RatingBar) view.findViewById(R.id.in_rating_bar);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(null);
        setTouchable(true);
        initListener();
    }

    private void initListener() {
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mUnbinder != null) {
                    mUnbinder.unbind();
                }
            }
        });
        this.mHolder.mIvDismissWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mHolder.windowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDataBean != null) {
                    dismiss();
                    StoreDetailActivity.start(mContext, String.valueOf(mDataBean.getId()));
                }
            }
        });
    }

    /**
     * 更新从底部弹出的视图view门店信息
     *
     * @param dataBean
     */
    public void updateView(StoreListProtocol.DataBean dataBean) {
        mDataBean = dataBean;
        GlideUtils.displayDefault(mContext, dataBean.getCoverImage(), mHolder.mIvStoreImg, R.mipmap.ic_store_details_default);
        mHolder.mTvStoreName.setText(dataBean.getName());
        mHolder.mTvDistance.setText(Tools.getDistanceStr(String.valueOf(dataBean.getDistance())));
        mHolder.mTvTradeNumber.setText(String.format("成交量：%s", dataBean.getTotalOrderCount()));
        mHolder.mRbStarGrade.setRating(dataBean.getScore());
        ImageView[] imageViews = new ImageView[]{mHolder.mHead1, mHolder.mHead2, mHolder.mHead3, mHolder.mHead4};
        int size = 0;
        if (dataBean.getNewDesignerPhotoList() != null && !dataBean.getNewDesignerPhotoList().isEmpty()) {
            size = dataBean.getNewDesignerPhotoList().size();
            mHolder.mLlStationedDesigner.setVisibility(View.VISIBLE);
            mHolder.mTvNotDesigner.setVisibility(View.GONE);
            for (int i = 0; i < size; i++) {
                String url = dataBean.getNewDesignerPhotoList().get(i);
                switch (i) {
                    case 0:
                        GlideUtils.displayCircle(mContext, url, mHolder.mHead1, R.mipmap.ic_default_head);
                        break;
                    case 1:
                        GlideUtils.displayCircle(mContext, url, mHolder.mHead2, R.mipmap.ic_default_head);
                        break;
                    case 2:
                        GlideUtils.displayCircle(mContext, url, mHolder.mHead3, R.mipmap.ic_default_head);
                        break;
                    case 3:
                        GlideUtils.displayCircle(mContext, url, mHolder.mHead4, R.mipmap.ic_default_head);
                        break;
                }
            }
            for (int i = 3; i >= size; i--) {
                imageViews[i].setVisibility(View.GONE);
            }
        } else {
            mHolder.mLlStationedDesigner.setVisibility(View.GONE);
            mHolder.mTvNotDesigner.setVisibility(View.VISIBLE);
        }
    }

    public class Holder {
        @BindView(R.id.iv_dismiss_window)
        public ImageView mIvDismissWindow;
        @BindView(R.id.iv_store_img)
        public ImageView mIvStoreImg;
        @BindView(R.id.tv_store_name)
        public TextView mTvStoreName;
        //        @BindView(R.id.rb_star_grade)
        public RatingBar mRbStarGrade;
        @BindView(R.id.tv_trade_number)
        public TextView mTvTradeNumber;
        @BindView(R.id.tv_distance)
        public TextView mTvDistance;
        @BindView(R.id.iv_shop_designer_head1)
        public ImageView mHead1;
        @BindView(R.id.iv_shop_designer_head2)
        public ImageView mHead2;
        @BindView(R.id.iv_shop_designer_head3)
        public ImageView mHead3;
        @BindView(R.id.iv_shop_designer_head4)
        public ImageView mHead4;
        @BindView(R.id.ll_stationed_designer)
        public LinearLayout mLlStationedDesigner;
        @BindView(R.id.tv_not_designer)
        public TextView mTvNotDesigner;

        @BindView(R.id.window_layout)
        public View windowLayout;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
