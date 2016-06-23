package com.test.xujixiao.xjx.utils;

import android.util.Log;

import com.test.xujixiao.xjx.BuildConfig;

/**
 * 工具类实现输出日志信息等
 */
public class TLog {
    public static String LOG_TAG = "xujixiao";
    public static boolean DEBUG = BuildConfig.DEBUG;

    public TLog() {
    }

    public static void error(String log) {
        if (DEBUG && log != null)
            Log.e(LOG_TAG, "" + log);
    }

    public static void log(String log) {
        if (DEBUG && log != null) {
            Log.e(LOG_TAG, log);
        }
    }

    public static void log(String tag, String log) {
        if (DEBUG && log != null)
            Log.i(tag, log);
    }

    public static void logException(Exception e) {
        if (DEBUG && e != null) {
        }
    }

    protected static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }
}
