package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;

import cn.tailorx.R;

/**
 * Created by xujixiao on 2016/8/8.16:13
 * 邮箱：1107313740@qq.com
 */
public class CustomSexSelectView extends FrameLayout implements GestureDetector.OnGestureListener {
    private Context mContext;
    private ImageView ivFontWoman;
    private ImageView ivFontMan;
    private int current_x;
    private int current_y;
    private int start_x;
    private int start_y;
    private int width;
    private int height;


    public CustomSexSelectView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CustomSexSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.custom_sex_select_layout, this);
//        ivFontMan = (ImageView) findViewById(R.id.iv_man);
//        ivFontWoman = (ImageView) findViewById(R.id.iv_woman);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("actondown-------------");
                onTouchDown(motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("ACTION_MOVE-------------");
                onTouchMove(motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("ACTION_UP-------------");
                break;
        }
        return false;
    }

    void onTouchDown(MotionEvent event) {

        current_x = (int) event.getRawX();
        current_y = (int) event.getRawY();

        start_x = (int) event.getX();
        start_y = current_y - this.getTop();

    }

    void onTouchMove(MotionEvent event) {
        int left = 0, top = 0, right = 0, bottom = 0;
        /** 处理拖动 **/
        /** 在这里要进行判断处理，防止在drag时候越界 **/
        /** 获取相应的l，t,r ,b **/
        left = current_x - start_x;
        right = current_x + this.getWidth() - start_x;

        /** 水平进行判断 **/
        if (left >= 0) {
            left = 0;
            right = this.getWidth();
        }
//        if (right <= screen_W) {
//            left = screen_W - this.getWidth();
//            right = screen_W;
//        }
        ivFontWoman.layout(left, ivFontWoman.getTop(), right, ivFontWoman.getBottom());
        current_x = (int) event.getRawX();
        current_y = (int) event.getRawY();
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
