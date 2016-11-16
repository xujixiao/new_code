package com.test.xujixiao.xjx.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import java.util.List;

public class CommonUtils {

	public static final int NETWORN_NONE = 0;
	public static final int NETWORN_WIFI = 1;
	public static final int NETWORN_MOBILE = 2;

	public static int getNetworkState(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// Wifi
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return NETWORN_WIFI;
		}

		// 3G
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if (state == State.CONNECTED || state == State.CONNECTING) {
			return NETWORN_MOBILE;
		}
		return NETWORN_NONE;
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}

		return false;
	}

	/**
	 * 检测Sdcard是否存在
	 * 
	 * @return
	 */
	public static boolean isExitsSdcard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			return true;
		else return false;
	}

	/**
	 * 得到版本号
	 */
	public static int getVersion(Context context) {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi;
		int version = 0;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} finally {
			pi = null;
			pm = null;
		}
		return version;
	}

	/**
	 * 得到版本号的名字
	 */
	public static String getVersionName(Context context) {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi;
		String version = "";
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionName;
		} catch (NameNotFoundException e) {
			version = "1.0.0.0";
		}
		return version;
	}

	public static String getTopActivity(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

		if (runningTaskInfos != null)
			return runningTaskInfos.get(0).topActivity.getClassName();
		else return "";
	}
}
