package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {

	public static String getSavePath(Context context) {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();// 获取跟目录
		} else {
			if (context != null) {
				return Environment.getDataDirectory().getAbsolutePath()
						+ File.separator + "data" + File.separator
						+ context.getPackageName();
			} else {
				return Environment.getDataDirectory().getAbsolutePath()
						+ File.separator + "data" + File.separator
						+ "com.utouu";
			}
		}
	}
}
