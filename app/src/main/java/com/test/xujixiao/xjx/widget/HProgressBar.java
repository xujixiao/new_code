package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.DisplayUtil;

import java.util.ArrayList;

/**
 */
public class HProgressBar extends View {

    private float paddingLeft, paddingRight;
    private float viewWidth, viewHeight;
    private float widthInterval, lineHeight;
    private int color;

    private float dipToPx1;
    private Paint textPaint;
    private Paint backgroundPaint;
    private Paint statePaint;
    private Paint intervalPaint;

    private ArrayList<String> dates;
    private int workState = -1;
    private float workStateRatio;

    public HProgressBar(Context context) {
        super(context);
        init();
    }

    public HProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {

        // 画圆角矩形
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);// 充满
        backgroundPaint.setColor(getResources().getColor(R.color.gray));
        backgroundPaint.setAntiAlias(true);// 设置画笔的锯齿效果

        // 画圆角矩形
        statePaint = new Paint();
        statePaint.setStyle(Paint.Style.FILL);// 充满
        statePaint.setAntiAlias(true);// 设置画笔的锯齿效果

        intervalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        intervalPaint.setColor(getResources().getColor(R.color.white));
        intervalPaint.setStrokeWidth(DisplayUtil.dip2px(getContext(), 1));
        intervalPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.black_333333));
        textPaint.setTextSize(DisplayUtil.sp2px(getContext(), 9));
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);// 数字字体的样式 （粗细/实体或空心什么的...）
    }

    private void changeMeasure() {
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        paddingLeft = paddingRight = DisplayUtil.dip2px(getContext(), 15F);
        dipToPx1 = DisplayUtil.dip2px(getContext(), 4F);
        lineHeight = dipToPx1;

        widthInterval = (viewWidth - paddingLeft - paddingRight) / 4;
    }

    public void setData(ArrayList<String> dates, int workState, float workStateRatio) {
        this.dates = dates;
        this.workState = workState;
        this.workStateRatio = workStateRatio;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        changeMeasure();
        drawBackground(canvas);
        drawLine(canvas);
        drawInterval(canvas);
        drawDate(canvas);
    }

    private void drawBackground(Canvas canvas) {
        RectF oval3 = new RectF(paddingLeft, lineHeight, viewWidth - paddingRight, lineHeight + dipToPx1);// 设置个新的长方形
        canvas.drawRoundRect(oval3, dipToPx1, dipToPx1, backgroundPaint);// 第二个参数是x半径，第三个参数是y半径
    }

    private void drawLine(Canvas canvas) {
        int tempColor;
        switch (workState) {
            case 1:
                tempColor = getResources().getColor(R.color.red);
                break;
            case 2:
                tempColor = getResources().getColor(R.color.black);
                break;
            case 3:
                tempColor = getResources().getColor(R.color.blue_4C9BFF);
                break;
            case 4:
                tempColor = getResources().getColor(R.color.green_4DC0A4);
                break;
            case 5:
                tempColor = Color.BLACK;
                break;
            default:
                tempColor = Color.BLACK;
                break;
        }
        statePaint.setColor(tempColor);

        RectF oval3 = new RectF(paddingLeft, lineHeight, paddingLeft + 4 * widthInterval * workStateRatio, lineHeight + dipToPx1);// 设置个新的长方形
        canvas.drawRoundRect(oval3, dipToPx1, dipToPx1, statePaint);// 第二个参数是x半径，第三个参数是y半径
    }

    private void drawInterval(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 3; i++) {
            float tempFloatX = paddingLeft + (i + 1) * widthInterval;
            path.moveTo(tempFloatX, 0);
            path.lineTo(tempFloatX, lineHeight + dipToPx1);
            canvas.drawPath(path, intervalPaint);
        }
    }

    private void drawDate(Canvas canvas) {
        if (null == dates) {
            return;
        }
        float textHeight = getTextHeight(9, dates.get(0));
        float height = lineHeight + DisplayUtil.dip2px(getContext(), 10) + textHeight;
        int tempPx3 = DisplayUtil.sp2px(getContext(), 10);
        float tempFloatY = height + textHeight * 3 / 2;

        for (int i = 0; i < dates.size(); i++) {
            Path path = new Path();
            float tempTextWidth = getTextWidth(tempPx3, dates.get(i)) / 2;
            path.moveTo(paddingLeft + (i + 1) * widthInterval - tempTextWidth, tempFloatY);// 只用于移动移动画笔。
            path.lineTo(paddingLeft + (i + 1) * widthInterval + tempTextWidth, tempFloatY);// 用于进行直线绘制。
            canvas.drawTextOnPath(dates.get(i), path, 0, 0, textPaint);
        }

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
