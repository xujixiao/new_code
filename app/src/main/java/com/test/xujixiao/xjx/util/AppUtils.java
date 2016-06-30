package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AppUtils {

    /**
     * 获取设备IMEI号
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {

        String imei = "";
        try {
            // 获取设备信息
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (null != tm) {
                imei = tm.getDeviceId();
            }
            if (null == imei) {
                imei = "";
            }

            // getLine1Number 手机号
            // getSimSerialNumber SIM卡序号
            // getSubscriberId IMSI

        } catch (SecurityException e) {
            CrashUtils.crash(e, "AppUtils:第42行");
        } catch (Exception e) {
            CrashUtils.crash(e, "AppUtils:第44行");
        }
        return imei;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (null != packageManager) {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                if (null != packageInfo) {
                    int labelRes = packageInfo.applicationInfo.labelRes;
                    return context.getResources().getString(labelRes);
                }
            }
        } catch (NameNotFoundException e) {
            CrashUtils.crash(e, "AppUtils:第63行");
        }
        return "UTOUU";
    }


    /**
     * 模拟器判断
     *
     * @param context
     * @return <br>
     * 创建时间：2015-6-19 下午3:23:34
     */
    public static void isEmulator(Context context) {
        String result = "";
        try {
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            ProcessBuilder cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            StringBuffer sb = new StringBuffer();
            String readLine = "";
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine);
            }
            responseReader.close();
            result = sb.toString().toLowerCase();
        } catch (IOException ex) {
            CrashReport.postCatchedException(ex);
        }
        boolean flag = (!result.contains("arm")) || (result.contains("intel")) || (result.contains("amd"));
    }
}
