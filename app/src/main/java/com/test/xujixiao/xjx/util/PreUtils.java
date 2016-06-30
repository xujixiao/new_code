package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.xujixiao.xjx.util.prefUtils.SharedPreferencesCompat;

import java.util.Map;

/**
 * 旧版的文件操作类
 */
public class PreUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "utouu_im_data";

    public static String getString(String key, final String defaultValue) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            return preferences.getString(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static void setString(final String key, final String value) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    public static boolean getBoolean(final String key,
                                     final boolean defaultValue) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            return preferences.getBoolean(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static void setBoolean(final String key, final boolean value) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    public static int getInt(final String key, final int defaultValue) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            return preferences.getInt(key, defaultValue);
        } else {
            return defaultValue;
        }
    }


    public static void setInt(final String key, final int value) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    public static float getFloat(final String key, final float defaultValue) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            return preferences.getFloat(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static void setFloat(final String key, final float value) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    public static long getLong(final String key, final long defaultValue) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            return preferences.getLong(key, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static void setLong(final String key, final long value) {
        if (null != NimUIKit.getContext()) {
            final SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(
                    FILE_NAME, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        if (null != NimUIKit.getContext()) {
            SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        if (null != NimUIKit.getContext()) {
            SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        if (null != NimUIKit.getContext()) {
            SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
            return preferences.contains(key);
        } else {
            return false;
        }
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        if (null != NimUIKit.getContext()) {
            SharedPreferences preferences = NimUIKit.getContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
            return preferences.getAll();
        } else {
            return null;
        }
    }
}