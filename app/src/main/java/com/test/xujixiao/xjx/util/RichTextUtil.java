package com.test.xujixiao.xjx.util;


import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xujixiao on 2016/11/18.14:06
 * 邮箱：1107313740@qq.com
 * android textview 富文本操作
 */
public class RichTextUtil {

    public static final String RICHTEXT_STRING = "string";
    public static final String RICHTEXT_COLOR = "color";
    public static final String RICHTEXT_SIZE = "size";
    public static final String RICHTEXT_RSIZE = "relativesize";
    public static final String RICHTEXT_DELETE = "delete";


    /**
     * 根据传入的hashmaplist组成富文本返回,key在RichTextUtil里
     *
     * @param list
     * @return
     */
    public static SpannableStringBuilder getSpannableStringFromList(List<HashMap<String, Object>> list) {

        SpannableStringBuilder ssb = new SpannableStringBuilder("");
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = list.get(i);
            try {
                String st = (String) map.get(RICHTEXT_STRING);
                ssb.append(st);
                int len = st.length();

                if (map.containsKey(RICHTEXT_COLOR)) {
                    int color = ((Integer) map.get(RICHTEXT_COLOR)).intValue();
                    ssb.setSpan(new ForegroundColorSpan(color), position, position + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                }

                if (map.containsKey(RICHTEXT_SIZE)) {
                    int size = ((Integer) map.get(RICHTEXT_SIZE)).intValue();
                    ssb.setSpan(new AbsoluteSizeSpan(size), position, position + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                if (map.containsKey(RICHTEXT_RSIZE)) {
                    float size = ((Float) map.get(RICHTEXT_RSIZE)).floatValue();
                    ssb.setSpan(new RelativeSizeSpan(size), position, position + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                if (map.containsKey(RICHTEXT_DELETE)) {
                    ssb.setSpan(new StrikethroughSpan(), position, position + len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

//              android.text.style.RelativeSizeSpan
                position = position + len;

            } catch (Exception e) {
                return null;
            }

        }

        return ssb;
    }
}


/*    使用方法
List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    HashMap<String, Object> map;
    map = new HashMap<String, Object>();
    map.put(RichTextUtil.RICHTEXT_STRING, "第1部分正常");
    list.add(map);

    map = new HashMap<String, Object>();
    map.put(RichTextUtil.RICHTEXT_STRING, "第2部分红色");
    map.put(RichTextUtil.RICHTEXT_COLOR, new Integer(Color.RED));
    list.add(map);

    map = new HashMap<String, Object>();
    map.put(RichTextUtil.RICHTEXT_STRING, "第3部分30号字");
    map.put(RichTextUtil.RICHTEXT_SIZE, new Integer(30));
    list.add(map);

    map = new HashMap<String, Object>();
    map.put(RichTextUtil.RICHTEXT_STRING, "第4部分蓝色加大35");
    map.put(RichTextUtil.RICHTEXT_COLOR, new Integer(Color.BLUE));
    map.put(RichTextUtil.RICHTEXT_SIZE, new Integer(35));
    list.add(map);

    tv.setText(RichTextUtil.getSpannableStringFromList(list));
*/