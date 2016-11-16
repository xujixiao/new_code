package com.test.xujixiao.xjx.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapTools {
    public static String SDPATH = Environment.getExternalStorageDirectory()
            + "/ubeauty/cache/";

    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    public static String saveBitmap(Bitmap bm,String path) {
        Log.e("", "保存图片");
        try {
            File fileAbsolutePath = new File(SDPATH);
            if (!fileAbsolutePath.exists()) {
                fileAbsolutePath.mkdirs();
            }
            if (fileAbsolutePath != null) {
                File f = new File(SDPATH, path);
                FileOutputStream out = new FileOutputStream(f);
                bm.compress(Bitmap.CompressFormat.JPEG, 90, out);

                if(bm!=null&&bm.isRecycled())
                {
                    bm.recycle();
                    bm=null;
                    //System.gc();
                }
                out.flush();
                out.close();
                Log.e("", "已经保存");
                Log.i("info", f.getAbsolutePath());
                return f.getAbsolutePath();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
