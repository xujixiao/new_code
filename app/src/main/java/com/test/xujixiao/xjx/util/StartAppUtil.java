package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;


/**
 * 启动App工具
 */
public class StartAppUtil {
    private static StartAppUtil mStartAppUtil = null;

    private StartAppUtil() {
    }

    public static StartAppUtil getInstance() {
        if (null == mStartAppUtil) {
            synchronized (StartAppUtil.class) {
                if (null == mStartAppUtil) {
                    mStartAppUtil = new StartAppUtil();
                }
            }
        }
        return mStartAppUtil;
    }


    public void startApp(final Context context, String appPackageName, final String appName, final String appURL, final int appIcon) {
        AlertDialog updateDialog = null;
        try {
            Intent intent = context.getPackageManager()
                    .getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        } catch (Exception e) {
            updateDialog = new AlertDialog.Builder(context).setMessage("当前" + appName + "未安装,是否下载" + appName + "?")
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    if (appURL.endsWith(".apk")) {
                                        // downApk(context, appName, appURL, appIcon);
                                    } else {
                                        // checkUpdates(context, appName, appURL, appIcon);
                                    }
                                    dialog.dismiss();
                                }
                            })
                    .setNegativeButton(android.R.string.cancel, null)
                    .create();
            if (null != updateDialog && !updateDialog.isShowing()) {
                updateDialog.show();
            }
        }
    }





}