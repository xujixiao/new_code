package com.test.xujixiao.xjx.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by xujixiao on 2016/8/11.09:53
 * 邮箱：1107313740@qq.com
 */
public class IntentUtils {
    public static void startBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static void startCallPhone(Context context, String telephone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restartApplication(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("cn.tailorx");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        LogUtils.d("重新启动应用----");
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Activity context, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, 3);
    }
}
