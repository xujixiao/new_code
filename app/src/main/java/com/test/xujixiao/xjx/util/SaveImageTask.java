package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;

import com.test.xujixiao.xjx.widget.view.MultiTouchZoomableImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


/**
 * Created by xujixiao on 2016/9/27.09:56
 * 邮箱：1107313740@qq.com
 * 后台保存image
 */
public class SaveImageTask extends AsyncTask<Bitmap, Void, String> {
    private MultiTouchZoomableImageView mImageView;
    private Context mContext;

    public SaveImageTask(Context context, MultiTouchZoomableImageView imageView) {
        super();
        this.mImageView = imageView;
        this.mContext = context;
    }

    @Override
    protected String doInBackground(Bitmap... params) {
        String result = "图片保存失败";
        try {
            String sdcard = Environment.getExternalStorageDirectory().toString();
            String fileName = System.currentTimeMillis() + ".png";
            File file = new File(sdcard + "/Download", fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            File imageFile = new File(file.getAbsolutePath(), new Date().getTime() + ".jpg");
            FileOutputStream fileOutputStream = null;
            fileOutputStream = new FileOutputStream(imageFile);
            Bitmap image = params[0];
            image.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            result = String.format("图片保存到%s", file.getAbsolutePath());
            // 其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(), file.getAbsolutePath(), fileName, null);
            // 最后通知图库更新
            mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        mImageView.setDrawingCacheEnabled(false);
    }
}
