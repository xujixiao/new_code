package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.xujixiao.xjx.R;


/**
 * 滑动工具类
 */
public class GlideUtils {
    /**
     * 默认
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayDefault(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
//                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 显示gif图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayDefault(Context context, int url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asGif()
                .into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param Default
     */
    public static void displayDefault(Context context, String url, ImageView imageView, int Default) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(Default)
                .error(Default)
//                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param Default
     * @param error
     */
    public static void displayDefault(Context context, String url, ImageView imageView, int Default, int error) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .centerCrop()
                .placeholder(Default)
                .error(error)
                .crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void displayDefault(Context context, String url, ImageView imageView, int Default, int width, int height) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
//                .crossFade()

                .centerCrop()
                .placeholder(Default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .override(width, height)
                .into(imageView);
    }

    /**
     * 圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayCircle(Context context, String url, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param Default
     */
    public static void displayCircle(Context context, String url, ImageView imageView, int Default) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(Default)
                .error(Default)
                .transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param Default
     * @param error
     */
    public static void displayCircle(Context context, String url, ImageView imageView, int Default, int error) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(Default)
                .error(error)
                .transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


}
