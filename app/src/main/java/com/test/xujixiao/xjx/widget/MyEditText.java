package com.test.xujixiao.xjx.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xujixiao on 2016/10/17.14:29
 * 邮箱：1107313740@qq.com
 * 过滤emjoi表情的数据
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

    public InputFilter[] mInputFilter = new InputFilter[]{this.emojiFilter};

    /**
     * 过滤emoji表情内容
     */
    public InputFilter emojiFilter = new InputFilter() {

        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                ToastUtils.showLongToast(getContext(), "不支持表情输入");
                return "";
            }
            return null;
        }
    };

    private void init() {
        setFilters(mInputFilter);
    }
}
