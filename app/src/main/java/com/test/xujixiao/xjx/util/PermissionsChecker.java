package com.test.xujixiao.xjx.util;

/**
 * Created by xujixiao on 2016/10/11.09:24
 * 邮箱：1107313740@qq.com
 */

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;


/**
 * 检查权限的工具类
 */
public class PermissionsChecker {


    // 判断权限集合
    public static boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    public static boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(NimUIKit.sContext, permission) !=
                PackageManager.PERMISSION_GRANTED;
    }
}
