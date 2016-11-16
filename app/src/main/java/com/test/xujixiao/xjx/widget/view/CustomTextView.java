package com.test.xujixiao.xjx.widget.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

//自定义控件字体
public class CustomTextView extends TextView {

	public CustomTextView(Context context) {
		super(context);
		init(context);
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomTextView(Context context, AttributeSet attrs, int defSyle) {
		super(context, attrs, defSyle);
		init(context);
	}

	/***
	 * 设置字体
	 * 
	 * @return
	 */
	public void init(Context context) {
		Typeface typeface = FontCustom.setFont(context);
		if (null != typeface)
			setTypeface(typeface);

	}
}
