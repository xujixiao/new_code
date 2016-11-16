package com.test.xujixiao.xjx.widget.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import cn.tailorx.R;
import cn.tailorx.constants.TailorxConstants;
import cn.tailorx.listener.AnimationListener;

/**
 * 通用PopupWindow
 */

public class BaseTailorXWindow extends PopupWindow {

    private Button mBtnTakePhoto, mBtnPickDelete, mBtnCancel;
    private View mView;
    private Context context;
    public ItemClick mItemClick;
    private View mLine;

    public BaseTailorXWindow(Activity context, OnClickListener itemsOnClick) {
        super(context);
        init(context, itemsOnClick);
        initView();
    }

    public interface ItemClick {
        void confirm();

        void rest();
    }

    public BaseTailorXWindow(final Activity context, OnClickListener itemsOnClick, String camera, String photo) {
        super(context);
        init(context, itemsOnClick);
        //设置为相机
        mBtnTakePhoto.setTextColor(Color.parseColor("#4c9bff"));
        mBtnTakePhoto.setTextSize(18);
        mBtnTakePhoto.setText(camera);
        //设置为相册
        mBtnPickDelete.setTextColor((Color.parseColor("#4c9bff")));
        mBtnPickDelete.setTextSize(18);
        mBtnPickDelete.setText(photo);
        if (TextUtils.isEmpty(photo)) {
            mBtnPickDelete.setVisibility(View.GONE);
            mLine.setVisibility(View.GONE);
        }

        //设置取消
        mBtnCancel.setTextColor((Color.parseColor("#f22613")));
        mBtnCancel.setTextSize(18);
        mBtnCancel.setText("取消");
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                runDismissAnimation();
            }
        });

        initView();
    }

    private void init(Activity context, OnClickListener itemsOnClick) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.base_dialog_layout, null);
        this.context = context;
        mBtnTakePhoto = (Button) mView.findViewById(R.id.btn_base1);
        mLine = mView.findViewById(R.id.view_line);
        mBtnPickDelete = (Button) mView.findViewById(R.id.btn_base2);
        mBtnCancel = (Button) mView.findViewById(R.id.btn_base3);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void initView() {

        //第一个按钮监听
        mBtnTakePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClick != null) {
                    mItemClick.confirm();
                }
                dismiss();
            }
        });
        // 第二个按钮监听
        mBtnPickDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClick != null) {
                    mItemClick.rest();
                }
                dismiss();
            }
        });
        this.setContentView(mView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        // mView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mView.findViewById(R.id.ll_base_dialog).getTop();
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

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        mView.clearAnimation();
        mView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_from_bottom));
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x66000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
        setAnimation(0, TailorxConstants.animation_back_alpha, null);
    }

    private void runDismissAnimation() {
        mView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_to_bottom);
        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                dismiss();
            }
        });
        mView.startAnimation(animation);
        setAnimation(TailorxConstants.animation_back_alpha, 0, null);
    }

    public interface AnimationEnd {
        void animationEnd();
    }

    private void setAnimation(int start, int end, final AnimationEnd animationEnd) {
        mBtnCancel.clearAnimation();
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(500).start();
        animator.setTarget(mBtnCancel);
//		animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                LogUtils.d("======" + animation.getAnimatedValue());
                getBackground().setAlpha((Integer) animation.getAnimatedValue());
            }
        });
    }
}
