package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.test.xujixiao.xjx.util.DisplayUtil;

/**
 * Created by Administrator on 2015/9/21.
 */
public class CustomLayout extends RadioGroup {

    private static int PADDING_HOR = 20;//水平方向padding
    private static int PADDING_VERTICAL = 10;//垂直方向padding
    private static int SIDE_MARGIN = 20;//左右间距
    private static int TEXT_MARGIN = 20;
    private RadioButton tempRadioButton;

    /**
     * @param context
     */
    public CustomLayout(Context context) {
        super(context);
        initData(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int autualWidth = r - l;
        int x = 0;// 横坐标开始
        int y = 0;//纵坐标开始
        int rows = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            x += width + TEXT_MARGIN;
            if (x > autualWidth) {
                x = width + SIDE_MARGIN;
                if (i != 0) {
                    rows++;
                }
            }
            y = rows * (height + TEXT_MARGIN);
            if (i == 0) {
                view.layout(SIDE_MARGIN, y - height, x, y);
            } else {
                view.layout(x - width, y - height, x, y);
            }
        }
    }

    private void initData(Context context) {
        PADDING_HOR = DisplayUtil.dp2px(context, 5);//水平方向padding
        PADDING_VERTICAL = DisplayUtil.dip2px(context, 2.5f);//垂直方向padding
        SIDE_MARGIN = DisplayUtil.dip2px(context, 5);//左右间距
        TEXT_MARGIN = DisplayUtil.dip2px(context, 5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = SIDE_MARGIN;//横坐标,左边距的大小作为x的起始位置
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - SIDE_MARGIN * 2;//实际宽度，可以用来放置子view的宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR, PADDING_VERTICAL);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + TEXT_MARGIN;
            if (x > actualWidth) {//换 行
                x = width;
                if (index != 0) {
                    rows++;
                }
            }
            y = rows * (height + TEXT_MARGIN);
        }
        setMeasuredDimension(actualWidth, y + TEXT_MARGIN);
    }

    public RadioButton addRadioButton(Context context, String text) {
        tempRadioButton = new RadioButton(context);
        tempRadioButton.setText(text);
        tempRadioButton.setTextSize(12);
        tempRadioButton.setButtonDrawable(null);
        this.addView(tempRadioButton);
        return tempRadioButton;
    }

    public RadioButton addRadioButtonTrans(Context context, String text) {
        tempRadioButton = new RadioButton(context);
        tempRadioButton.setText(text);
        tempRadioButton.setTextSize(12);
        tempRadioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
        this.addView(tempRadioButton);
        return tempRadioButton;
    }

    public void addRadioButtonTrans(String key, Object value) {
        RadioButton uRadioButton = new RadioButton(getContext());
        uRadioButton.setTag(value);
        uRadioButton.setText(key);
        uRadioButton.setTextSize(12);
        uRadioButton.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));
        this.addView(uRadioButton);
    }
}
