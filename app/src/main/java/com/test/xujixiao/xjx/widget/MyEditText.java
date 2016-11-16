package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import cn.tailorx.utils.Tools;

/**
 * Created by xujixiao on 2016/10/17.14:29
 * 邮箱：1107313740@qq.com
 */

public class MyEditText extends EditText {
    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFilters(Tools.mInputFilter);
    }
}
