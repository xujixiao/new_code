package com.test.xujixiao.xjx.widget.view;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.PopupWindow;

import cn.tailorx.R;

/**
 * 选择图片
 */
public class SelectPictureWindow extends PopupWindow {

	private Button mBtnTakePhoto, mBtnPickDelete, mBtnCancel;
	private View mMenuView;
    private Context mContext;
    private OnClickListener mOnClickListener;
    private String mCamera;
    private String mPhoto;

    public SelectPictureWindow(Activity context, OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.base_dialog_layout, null);

        this.mContext = context;
        this.mOnClickListener = itemsOnClick;

        mBtnTakePhoto = (Button) mMenuView.findViewById(R.id.btn_base1);
        mBtnPickDelete = (Button) mMenuView.findViewById(R.id.btn_base2);
        mBtnCancel = (Button) mMenuView.findViewById(R.id.btn_base3);

        initView();
    }

    public SelectPictureWindow(Activity context, OnClickListener itemsOnClick, String camera, String photo) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.base_dialog_layout, null);

        this.mContext = context;
        this.mOnClickListener = itemsOnClick;
//        this.mCamera = confirm;
//        this.mPhoto = photo;

        mBtnTakePhoto = (Button) mMenuView.findViewById(R.id.btn_base1);
        mBtnPickDelete = (Button) mMenuView.findViewById(R.id.btn_base2);
        mBtnCancel = (Button) mMenuView.findViewById(R.id.btn_base3);
        // 设置为相机
        mBtnTakePhoto.setTextColor(Color.parseColor("#4c9bff"));
        mBtnTakePhoto.setTextSize(18);
        mBtnTakePhoto.setText(camera);
        // 设置为相册
        mBtnPickDelete.setTextColor((Color.parseColor("#4c9bff")));
        mBtnPickDelete.setTextSize(18);
        mBtnPickDelete.setText(photo);
        // 设置取消
        mBtnCancel.setTextColor((Color.parseColor("#f22613")));
        mBtnCancel.setTextSize(18);
        mBtnCancel.setText("取消");

        initView();
    }


    private void initView() {

        //第一个按钮监听
        mBtnTakePhoto.setOnClickListener(mOnClickListener);
        // 第二个按钮监听
        mBtnPickDelete.setOnClickListener(mOnClickListener);
        // 设置取消按钮监听
        mBtnCancel.setOnClickListener(mOnClickListener);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AppTheme1);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.ll_base_dialog).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }


}
