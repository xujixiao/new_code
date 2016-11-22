package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.DisplayUtil;


/**
 * Created by xujixiao on 2016/11/18.14:50
 * 邮箱：1107313740@qq.com
 */

public class MainTabLayout extends LinearLayout {
    private Context mContext;
    private AttributeSet mAttributeSet;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4;
    private CheckInterface mCheckInterface;

    public void setCheckInterface(CheckInterface checkInterface) {
        mCheckInterface = checkInterface;
    }

    interface CheckInterface {
        void checkPosition(int position);
    }

    public MainTabLayout(Context context) {
        super(context);
        mContext = context;
    }

    public MainTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAttributeSet = attrs;
        initView();
        initListen();
    }

    public MainTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttributeSet = attrs;
        initView();
        initListen();
    }

    public void initView() {
        if (mAttributeSet == null)
            return;
        TypedArray typedArray = mContext.obtainStyledAttributes(mAttributeSet, R.styleable.main_tab_view);
        mTextView1 = new TextView(mContext);
        mTextView1.setText(typedArray.getString(R.styleable.main_tab_view_name1));
        mTextView2 = new TextView(mContext);
        mTextView2.setText(typedArray.getString(R.styleable.main_tab_view_name2));
        mTextView3 = new TextView(mContext);
        mTextView3.setText(typedArray.getString(R.styleable.main_tab_view_name4));
        mTextView4 = new TextView(mContext);
        mTextView4.setText(typedArray.getString(R.styleable.main_tab_view_name4));

        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        mTextView1.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView2.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView3.setGravity(Gravity.CENTER_HORIZONTAL);
        mTextView4.setGravity(Gravity.CENTER_HORIZONTAL);
        Drawable drawable1 = typedArray.getDrawable(R.styleable.main_tab_view_img1);
        Drawable drawable2 = typedArray.getDrawable(R.styleable.main_tab_view_img2);
        Drawable drawable3 = typedArray.getDrawable(R.styleable.main_tab_view_img3);
        Drawable drawable4 = typedArray.getDrawable(R.styleable.main_tab_view_img4);
        float dimension = typedArray.getDimension(R.styleable.main_tab_view_drawable_padding, DisplayUtil.dp2px(mContext, 5));
        if (drawable1 != null) {
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        }
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        }
        mTextView1.setCompoundDrawables(null, drawable1, null, null);
        mTextView2.setCompoundDrawables(null, drawable2, null, null);
        mTextView3.setCompoundDrawables(null, drawable3, null, null);
        mTextView4.setCompoundDrawables(null, drawable4, null, null);

        mTextView1.setCompoundDrawablePadding((int) dimension);
        mTextView2.setCompoundDrawablePadding((int) dimension);
        mTextView3.setCompoundDrawablePadding((int) dimension);
        mTextView4.setCompoundDrawablePadding((int) dimension);
        mTextView1.setLayoutParams(layoutParams);
        mTextView2.setLayoutParams(layoutParams);
        mTextView3.setLayoutParams(layoutParams);
        mTextView4.setLayoutParams(layoutParams);
        setVerticalGravity(VERTICAL);
        addView(mTextView1);
        addView(mTextView2);
        addView(mTextView3);
        addView(mTextView4);
    }

    private void initListen() {
        mTextView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckInterface != null) {
                    mCheckInterface.checkPosition(1);
                }
            }
        });
        mTextView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckInterface != null) {
                    mCheckInterface.checkPosition(2);
                }
            }
        });
        mTextView3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckInterface != null) {
                    mCheckInterface.checkPosition(3);
                }
            }
        });
        mTextView4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckInterface != null) {
                    mCheckInterface.checkPosition(4);
                }
            }
        });
    }

    private void setDrawable(TextView textView, Drawable drawable, int padding) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            if (textView != null) {
                textView.setCompoundDrawables(null, drawable, null, null);
                textView.setCompoundDrawablePadding(padding);
            }
        }
    }
}
