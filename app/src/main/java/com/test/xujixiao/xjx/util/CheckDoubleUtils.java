package com.test.xujixiao.xjx.util;

/**
 * Created by xujixiao on 2016/2/4.20:46
 * 邮箱：1107313740@qq.com
 */
public class CheckDoubleUtils {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 600) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isShortDoubleClick(int overTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < overTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


}
