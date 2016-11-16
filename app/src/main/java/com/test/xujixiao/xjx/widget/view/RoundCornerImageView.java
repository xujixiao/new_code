package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.test.xujixiao.xjx.R;


/**
 * 证件上传空间自定义
 */
public class RoundCornerImageView extends ImageView {

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Path clipPath = new Path();
        int w = getWidth();
        int h = getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), 15.0f, 15.0f, Path.Direction.CW);
        canvas.drawColor(getResources().getColor(R.color.white));
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
