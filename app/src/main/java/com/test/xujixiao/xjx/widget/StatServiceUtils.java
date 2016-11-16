package com.test.xujixiao.xjx.widget;

import android.content.Context;

import com.tencent.stat.StatService;

import java.util.Properties;

/**
 * Created by Administrator on 2016/7/7.
 * 腾讯云分析的用户操作跟踪
 */
public class StatServiceUtils {

    /**
     * 事件埋点
     *
     * @param context
     * @param event_id
     * @param args
     */
    public static void trackCustomEvent(Context context, String event_id, String... args) {
        StatService.trackCustomEvent(context, event_id, args);
    }

    /**
     * @param context
     * @param event_id
     * @param params_id
     */
    public static void trackCustomKVEvent(Context context, String event_id, String params_id) {
        Properties properties = new Properties();
        properties.put(params_id, "");
        StatService.trackCustomKVEvent(context, event_id, properties);
    }
}
