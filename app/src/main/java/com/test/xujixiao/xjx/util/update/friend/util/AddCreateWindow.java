//package com.test.xujixiao.xjx.util.update.friend.util;
//
//import android.animation.Animator;
//import android.animation.Animator.AnimatorListener;
//import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Handler;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//
//
///**
// * 新版本的功能界面浮层
// */
//public class AddCreateWindow extends PopupWindow implements View.OnClickListener {
//
//    private String TAG = AddCreateWindow.class.getSimpleName();
//    private Activity mContext;
//    private int statusBarHeight;
//    private Bitmap mBitmap = null;
//    private Bitmap overlay = null;
//    private int select = -1;
//
//    private Handler mHandler = new Handler();
//    private RelativeLayout layout;
//
//    public AddCreateWindow(Activity context) {
//        mContext = context;
//    }
//
//    private BlurListener blurListener;
//
//    public void setBlurListener(BlurListener tBlurListener) {
//        this.blurListener = tBlurListener;
//    }
//
//    public interface BlurListener {
//        void onItemClick(int item);
//    }
//
//
//    public void init() {
//        Rect frame = new Rect();
//        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        statusBarHeight = frame.top + 30;
//        DisplayMetrics metrics = new DisplayMetrics();
//        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int mWidth = metrics.widthPixels;
//        int mHeight = metrics.heightPixels;
//
//        setWidth(mWidth);
//        setHeight(mHeight);
//    }
//
//    private Bitmap blur() {
//        if (null != overlay) {
//            return overlay;
//        }
//        long startMs = System.currentTimeMillis();
//
//        View view = mContext.getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache(true);
//        mBitmap = view.getDrawingCache();
//
//        float scaleFactor = 8;
//        float radius = 10;
//        if (mBitmap != null) {
//            int width = mBitmap.getWidth();
//            int height = mBitmap.getHeight();
//
//            overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(overlay);
//            canvas.scale(1 / scaleFactor, 1 / scaleFactor);
//            Paint paint = new Paint();
//            paint.setFlags(Paint.FILTER_BITMAP_FLAG);
//            canvas.drawBitmap(mBitmap, 0, 0, paint);
//
//            overlay = FastBlur.doBlur(overlay, (int) radius, true);
//            Log.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
//        }
//        return overlay;
//    }
//
//
//    public void showMoreWindow(View anchor, int bottomMargin) {
//        select = -1;
//        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.add_create_popwindow, null);
//        setContentView(layout);
//        showAnimation(layout);
//        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
//        setOutsideTouchable(true);
//        setFocusable(true);
//        showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
//    }
//
//    private void showAnimation(ViewGroup tempLayout) {
//        ImageView imageView = (ImageView) tempLayout.findViewById(R.id.iv_add_create_function);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, -135);
//        objectAnimator.setDuration(300).start();
//        imageView.setOnClickListener(this);
//
//        ViewGroup layout = (ViewGroup) tempLayout.findViewById(R.id.ll_function_layout);
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            final View child = layout.getChildAt(i);
//            child.setOnClickListener(this);
//            child.setVisibility(View.INVISIBLE);
//            mHandler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    child.setVisibility(View.VISIBLE);
//                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", -400, 0);
//                    fadeAnim.setDuration(300);
//                    KickBackAnimator kickAnimator = new KickBackAnimator();
//                    kickAnimator.setDuration(150);
//                    fadeAnim.setEvaluator(kickAnimator);
//                    fadeAnim.start();
//                }
//            }, i * 50);
//        }
//
//    }
//
//    private void closeAnimation(ViewGroup tempLayout) {
//        ViewGroup layout = (ViewGroup) tempLayout.findViewById(R.id.ll_function_layout);
//        ImageView imageView = (ImageView) tempLayout.findViewById(R.id.iv_add_create_function);
//        ObjectAnimator.ofFloat(imageView, "rotation", -135, 0).setDuration(300).start();
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            final View child = layout.getChildAt(i);
//            child.setOnClickListener(this);
//            mHandler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    child.setVisibility(View.VISIBLE);
//                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, -400);
//                    fadeAnim.setDuration(300);
//                    KickBackAnimator kickAnimator = new KickBackAnimator();
//                    kickAnimator.setDuration(150);
//                    fadeAnim.setEvaluator(kickAnimator);
//                    fadeAnim.start();
//                    fadeAnim.addListener(new AnimatorListener() {
//
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            child.setVisibility(View.INVISIBLE);
//                        }
//
//                        @Override
//                        public void onAnimationCancel(Animator animation) {
//
//                        }
//                    });
//                }
//            }, (layout.getChildCount() - i - 1) * 30);
//
//            if (child.getId() == R.id.tv_add_friend) {
//                mHandler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        dismiss();
//                        if (select != -1) {
//                            clickItem(select);
//                        }
//                    }
//                }, (layout.getChildCount() - i) * 30 + 80);
//            }
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_add_create_function:
//                select = -1;
//                if (isShowing()) {
//                    closeAnimation(layout);
//                }
//                break;
//            case R.id.tv_add_friend:
//                select = 0;
//                if (isShowing()) {
//                    closeAnimation(layout);
//                }
//                break;
//            case R.id.tv_create_flock:
//                select = 1;
//                if (isShowing()) {
//                    closeAnimation(layout);
//                }
//                break;
//            case R.id.tv_create_discussion:
//                select = 2;
//                if (isShowing()) {
//                    closeAnimation(layout);
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void clickItem(int select) {
//        if (blurListener != null) {
//            blurListener.onItemClick(select);
//        }
//    }
//
//    public void destroy() {
//        if (null != overlay) {
//            overlay.recycle();
//            overlay = null;
////            System.gc();
//        }
//        if (null != mBitmap) {
//            mBitmap.recycle();
//            mBitmap = null;
////            System.gc();
//        }
//    }
//
//    public void closeWindow() {
//        if (isShowing()) {
//            closeAnimation(layout);
//        }
//    }
//}
