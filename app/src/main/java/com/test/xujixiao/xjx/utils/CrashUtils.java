package com.test.xujixiao.xjx.utils;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by xujixiao on 2016/4/21.17:11
 * 邮箱：1107313740@qq.com
 */
public class CrashUtils {
    public static void crash(Exception e) {
        TLog.log(e.toString());
        CrashReport.postCatchedException(e);
    }

    public static void crash(Exception e, String content) {
        CrashReport.postCatchedException(e);
    }
}
