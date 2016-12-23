package com.test.xujixiao.xjx.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai-feng.yue on 2016/4/20 15:50
 */

public class SelectAmountView extends View {
    private Context context;
    private ArrayList<String> dates = new ArrayList<>();


    private float paddingLeft, paddingRight;
    private float viewWidth, viewHeight;
    private float widthInterval, lineHeight;

    private int selectIndex = 0;

    public SelectAmountView(Context context) {
        super(context);
        this.context = context;
    }

    public SelectAmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SelectAmountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    public void setDate(List<String> dates, String date, String giftDate) {
        this.dates = (ArrayList<String>) dates;
        this.invalidate();
    }

    private void initPaint() {
        dates.clear();
        dates.add("5元");
        dates.add("50元");
        dates.add("100元");
        dates.add("150元");
        dates.add("200元");
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        paddingLeft = DisplayUtil.dp2px(context, 40);
        paddingRight = DisplayUtil.dip2px(context, 40);
        lineHeight = DisplayUtil.dip2px(context, 8);
        widthInterval = (viewWidth - paddingLeft - paddingRight) / 4;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(DisplayUtil.dip2px(context, 35), MeasureSpec.AT_MOST));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(0, 0, 0, 0);// 清空画布
        initPaint();
        drawLine(canvas);
        drawCircle(canvas);
        drawDate(canvas);
    }

    private void drawLine(Canvas canvas) {
        // 横线
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(R.color.red));
        paint.setStrokeWidth(DisplayUtil.dip2px(context, 5));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);


        Path path = new Path();
        path.moveTo(paddingLeft, lineHeight);
        path.lineTo(paddingLeft + selectIndex * widthInterval, lineHeight);
        canvas.drawPath(path, paint);

        paint.setColor(getResources().getColor(R.color.gray));
        path = new Path();
        path.moveTo(paddingLeft + selectIndex * widthInterval, lineHeight);
        path.lineTo(paddingLeft + 4 * widthInterval, lineHeight);
        canvas.drawPath(path, paint);
    }

    /**
     * 绘制金额
     *
     * @param canvas
     */
    private void drawDate(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.gray_666666));
        paint.setTextSize(DisplayUtil.sp2px(context, 12));
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);// 数字字体的样式 （粗细/实体或空心什么的...）

        float textHeight = getTextHeight(10, dates.get(0));
        float height = lineHeight + DisplayUtil.dip2px(context, 5) + textHeight;
        for (int i = 0; i < 5; i++) {
            Path path = new Path();
            path.moveTo(paddingLeft + i * widthInterval - getTextWidth(DisplayUtil.sp2px(context, 10), dates.get(i)) / 2, height + textHeight * 3 / 2);// 只用于移动移动画笔。
            path.lineTo(paddingLeft + i * widthInterval + getTextWidth(DisplayUtil.sp2px(context, 10), dates.get(i)) / 2, height + textHeight * 3 / 2);// 用于进行直线绘制。
            canvas.drawTextOnPath(dates.get(i), path, 0, 0, paint);
        }
    }

    // 画圆圈
    private void drawCircle(Canvas canvas) {
        Paint fillPaintWhite = new Paint();
        fillPaintWhite.setColor(getResources().getColor(R.color.color_f64747));
        fillPaintWhite.setStrokeWidth(DisplayUtil.dip2px(context, 3));
        fillPaintWhite.setAntiAlias(true);
        fillPaintWhite.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 5; i++) {
            float sPointWidth = paddingLeft + i * widthInterval;
            float sPointHeight = lineHeight;
            if (i == selectIndex) {
                fillPaintWhite.setColor(getResources().getColor(R.color.color_f64747));
                canvas.drawCircle(sPointWidth, sPointHeight, DisplayUtil.dip2px(context, 8), fillPaintWhite);
                fillPaintWhite.setColor(getResources().getColor(R.color.white));
                canvas.drawCircle(sPointWidth, sPointHeight, DisplayUtil.dip2px(context, 6), fillPaintWhite);
            } else if (i > selectIndex) {
                fillPaintWhite.setColor(getResources().getColor(R.color.gray));
                canvas.drawCircle(sPointWidth, sPointHeight, DisplayUtil.dip2px(context, 5), fillPaintWhite);
            } else {
                fillPaintWhite.setColor(getResources().getColor(R.color.color_f64747));
                canvas.drawCircle(sPointWidth, sPointHeight, DisplayUtil.dip2px(context, 5), fillPaintWhite);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dates == null || dates.size() == 0) {
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float fx = event.getX();
            for (int i = 0; i < dates.size(); i++) {
                float width = paddingLeft + i * widthInterval;
                if (fx > (width - widthInterval / 3) && fx < (width + widthInterval / 3)) {
                    selectIndex = i;
                    LogUtils.d(selectIndex + "-----------------------");
                    invalidate();
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public String getSelect() {
        if (dates == null || dates.size() == 0) {
            return "";
        }
        LogUtils.d("------------------------" + dates.get(selectIndex));
        return dates.get(selectIndex);
    }

    private TextPaint FontPaint;

    private float getTextWidth(float Size, String text) {
        if (FontPaint == null) {
            FontPaint = new TextPaint();
        }
        FontPaint.setTextSize(Size);
        return FontPaint.measureText(text);
    }

    private int getTextHeight(float Size, String text) {
        if (FontPaint == null) {
            FontPaint = new TextPaint();
        }
        FontPaint.setTextSize(Size);
        Rect bounds = new Rect();
        FontPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.bottom + bounds.height();
    }
}