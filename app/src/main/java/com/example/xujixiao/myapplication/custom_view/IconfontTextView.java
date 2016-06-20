package com.example.xujixiao.myapplication.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/20.
 */
public class IconfontTextView extends TextView {

	public IconfontTextView(Context context) {
		super(context);
		init();
	}

	public IconfontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public IconfontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		try {
			Typeface typeface = Typeface.createFromAsset(this.getResources().getAssets(), "iconfont/iconfont.ttf");
			setTypeface(typeface);
		} catch (RuntimeException exception) {}
	}
}
