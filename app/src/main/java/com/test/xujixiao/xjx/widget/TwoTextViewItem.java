package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.xujixiao.xjx.R;
import com.test.xujixiao.xjx.util.DisplayUtil;


/**
 * Created by xujixiao on 2016/11/18.09:23
 * 邮箱：1107313740@qq.com
 */

public class TwoTextViewItem extends RelativeLayout {
    private Context mContext;
    private AttributeSet mAttributeSet;
    private TextView tvName, tvValue;

    public TwoTextViewItem(Context context) {
        super(context);
        mContext = context;
    }

    public TwoTextViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mAttributeSet = attrs;
        initAttrs();
    }

    public TwoTextViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttributeSet = attrs;
        initAttrs();
    }

    private void initAttrs() {
        if (mAttributeSet == null) {
            return;
        }
        TypedArray typedArray = mContext.obtainStyledAttributes(mAttributeSet, R.styleable.two_textview_item);
        String name = (String) typedArray.getText(R.styleable.two_textview_item_left_name);
        if (TextUtils.isEmpty(name)) {
            name = "";
        }
        String value = (String) typedArray.getText(R.styleable.two_textview_item_right_value);
        if (TextUtils.isEmpty(value)) {
            value = "";
        }

        float nameSize = typedArray.getDimension(R.styleable.two_textview_item_name_size, DisplayUtil.dip2px(mContext, 16));
        float valueSize = typedArray.getDimension(R.styleable.two_textview_item_value_size, DisplayUtil.dip2px(mContext, 14));
        int nameColor = typedArray.getColor(R.styleable.two_textview_item_name_color, getResources().getColor(R.color.color_333333));
        int valueColor = typedArray.getColor(R.styleable.two_textview_item_value_color, getResources().getColor(R.color.color_999999));
        Drawable drawable = typedArray.getDrawable(R.styleable.two_textview_item_left_img);
        Drawable rightImg = typedArray.getDrawable(R.styleable.two_textview_item_right_img);
        initViews(name, value, nameSize, valueSize, nameColor, valueColor, drawable, rightImg);
    }

    private void initViews(String name, String value, float nameSize, float valueSize, int nameColor, int valueColor, Drawable drawable, Drawable arrow) {
        tvName = new TextView(mContext);
        tvName.setText(name);
        tvName.setTextColor(nameColor);
        tvName.setTextSize(DisplayUtil.px2dip(mContext, nameSize));
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.leftMargin = (int) DisplayUtil.dip2px(mContext, 12);
        this.addView(tvName, layoutParams);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tvName.setCompoundDrawables(drawable, null, null, null);
            tvName.setCompoundDrawablePadding((int) DisplayUtil.dip2px(mContext, 10));
        }

        tvValue = new TextView(mContext);
        tvValue.setText(value);
        tvValue.setTextColor(valueColor);
        tvValue.setTextSize(DisplayUtil.px2sp(mContext, valueSize));
        tvValue.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        LayoutParams valueParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        valueParams.addRule(RelativeLayout.CENTER_VERTICAL);
        valueParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        valueParams.rightMargin = (int) DisplayUtil.dip2px(mContext, 12);
        if (arrow != null) {
            arrow.setBounds(0, 0, arrow.getMinimumWidth(), arrow.getMinimumHeight());
            tvValue.setCompoundDrawables(null, null, arrow, null);
            tvValue.setCompoundDrawablePadding((int) DisplayUtil.dip2px(mContext, 10));
        }
        this.addView(tvValue, valueParams);
    }

    public void setValueText(CharSequence valueText) {
        tvValue.setText(valueText);
    }

    public String getValueText() {
        return tvValue.getText().toString();
    }

    public void setValueSize(int value) {
        tvValue.setTextSize(DisplayUtil.dip2px(mContext, value));
    }
}
