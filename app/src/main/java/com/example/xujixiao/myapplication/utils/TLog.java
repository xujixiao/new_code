package com.example.xujixiao.myapplication.utils;

import android.util.Log;

import com.example.xujixiao.myapplication.BuildConfig;

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
}
