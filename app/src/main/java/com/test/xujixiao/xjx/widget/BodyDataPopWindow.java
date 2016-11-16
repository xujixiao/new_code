package com.test.xujixiao.xjx.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;

import cn.tailorx.R;
import cn.tailorx.constants.TailorxConstants;
import cn.tailorx.listener.AnimationListener;
import cn.tailorx.widget.wheel_adapters.AbstractWheelTextAdapter;
import cn.tailorx.widget.wheel_widget.WheelView;

/**
 * 身体数据选择
 */
public class BodyDataPopWindow extends PopupWindow {

    private TextView mTvCancel, mTvConfirm;
    public WheelView mWlBodyData;
    private View mView;
    private Context mContext;
    public View.OnClickListener mItemsOnClick;
    protected ArrayList<String> mList;

    public BodyDataPopWindow(Context context, View.OnClickListener itemsOnClick, ArrayList<String> list) {
        this.mContext = context;
        this.mItemsOnClick = itemsOnClick;
        this.mList = list;
        initView();
    }


    public void setData(ArrayList<String> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.body_data_wheel, null);
        mTvCancel = (TextView) mView.findViewById(R.id.tv_cancel);
        mTvConfirm = (TextView) mView.findViewById(R.id.tv_confirm);
        mWlBodyData = (WheelView) mView.findViewById(R.id.wl_body_data);
        // 设置取消按钮监听
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runDismissAnimation();
            }
        });
        // 第二个确认按钮监听
        mTvConfirm.setOnClickListener(mItemsOnClick);
        mWlBodyData.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mList));
        // 设置可见条目数量
        mWlBodyData.setVisibleItems(7);
        mWlBodyData.setVisibleItems(7);
        mWlBodyData.setVisibleItems(7);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
// 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x66000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mView.findViewById(R.id.ll_wheel_pop).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        runDismissAnimation();

                    }
                }
                return true;
            }
        });

    }

    private void runDismissAnimation() {
        mView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_to_bottom);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                dismiss();
            }
        });
        mView.startAnimation(animation);
        setAnimation(TailorxConstants.animation_back_alpha, 0);
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        mView.clearAnimation();
        mView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_from_bottom));
        setAnimation(0, TailorxConstants.animation_back_alpha);
        super.showAtLocation(parent, gravity, x, y);
    }

    private void setAnimation(int start, int end) {
        mTvCancel.clearAnimation();
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(500).start();
        animator.setTarget(mTvCancel);
//		animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LogUtils.d("======" + animation.getAnimatedValue());
                getBackground().setAlpha((Integer) animation.getAnimatedValue());
            }
        });
    }

    public WheelView getWheelView() {
        return mWlBodyData;
    }

    /**
     * 身体数据适配器
     *
     * @param <T>
     */
    public static class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {

        private ArrayList<String> mList;

        /**
         * Constructor
         *
         * @param context the current mContext
         * @param list    the items
         */
        public ArrayWheelAdapter(Context context, ArrayList list) {
            super(context);

            //setEmptyItemResource(TEXT_VIEW_ITEM_RESOURCE);
            this.mList = list;
        }

        @Override
        public CharSequence getItemText(int index) {
            if (index >= 0 && index < mList.size()) {
                String item = mList.get(index);

                return item;
            }
            return "";
        }

        @Override
        public int getItemsCount() {
            return mList.size();
        }
    }

}
