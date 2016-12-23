package com.test.xujixiao.xjx.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.DisplayUtil;

/**
 * Created by xujixiao on 2016/12/15.10:21
 * 邮箱：1107313740@qq.com
 */

/**
 * 弧形进度条
 *
 * @author Eric
 */
public class ArcProgressBarView extends View {
    private Context mContext;
    private int height;
    private int width;

    /**
     * 背景弧线的宽度
     */
    private int bgStrokeWidth = 2;
    /**
     * 前景弧线的宽度
     */
    private int barStrokeWidth = 2;
    /**
     * 背景弧线的颜色
     */
    private int bgColor = Color.GRAY;
    /**
     * 前景弧线的颜色
     */
    private int barColor = getResources().getColor(R.color.color_yellow_b39729);
    /**
     * 控件的间距
     */
    private int padding = 15;

    private int smallBgColor = Color.WHITE;
    private int progress = 40;
    private int angleOfMoveCircle = 140;// 移动小园的起始角度。
    private int startAngle = 140;
    private int endAngle = 260;
    private Paint mPaintBar = null;
    private Paint mPaintSmallBg = null;
    private Paint mPaintBg = null;
    /**
     * 背景弧线的画笔
     */
    private Paint mPaintCircle = null;
    /**
     * 背景弧线所处的矩形大小
     */
    private RectF rectBg = null;
    /**
     * 直徑。
     */
    private int diameter = 70;

    private boolean showSmallBg = true;// 是否显示小背景。
    private boolean showMoveCircle = false;// 是否显示移动的小园。

    public ArcProgressBarView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ArcProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    public ArcProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectBg, startAngle, endAngle, false, mPaintCircle);
        mPaintCircle.setColor(barColor);
        canvas.drawArc(rectBg, startAngle, startAngle + 40, false, mPaintCircle);
        invalidate();
    }


    private void init() {
        diameter = DisplayUtil.dip2px(mContext, diameter);
        // 画弧形的矩阵区域。
        rectBg = new RectF(padding, 15, diameter, diameter);
        // 计算弧形的圆心和半径。
        int cx1 = (diameter + 15) / 2;
        int cy1 = (diameter + 15) / 2;
        int arcRadius = (diameter - 15) / 2;

        // ProgressBar结尾和开始画2个圆，实现ProgressBar的圆角。
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(bgColor);
        mPaintCircle.setStrokeWidth(barStrokeWidth);
        if (progress == 0) {
            progress = 1;
        }
    }

    /**
     */
    public void addProgress(int _progress) {
        progress += _progress;
        angleOfMoveCircle += _progress;
        System.out.println(progress);
        if (progress > endAngle) {
            progress = 0;
            angleOfMoveCircle = startAngle;
        }
        invalidate();
    }

    /**
     * 设置弧形背景的画笔宽度。
     */
    public void setBgStrokeWidth(int bgStrokeWidth) {
        this.bgStrokeWidth = bgStrokeWidth;
    }

    /**
     * 设置弧形ProgressBar的画笔宽度。
     */
    public void setBarStrokeWidth(int barStrokeWidth) {
        this.barStrokeWidth = barStrokeWidth;
    }

    /**
     * 设置弧形背景的颜色。
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * 设置弧形ProgressBar的颜色。
     */
    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    /**
     * 设置弧形小背景的颜色。
     */
    public void setSmallBgColor(int smallBgColor) {
        this.smallBgColor = smallBgColor;
    }

    /**
     * 设置弧形的直径。
     */
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    /**
     * 是否显示小背景。
     */
    public void setShowSmallBg(boolean showSmallBg) {
        this.showSmallBg = showSmallBg;
    }

    /**
     * 是否显示移动的小圆。
     */
    public void setShowMoveCircle(boolean showMoveCircle) {
        this.showMoveCircle = showMoveCircle;
    }


}

