package com.test.xujixiao.xjx.util;


import android.app.Activity;

import com.test.xujixiao.xjx.custom_view.LoadingProgress;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.HashMap;

/**
 * 分享工具类
 * Created by xujixiao on 2016/1/19.
 */

public class ShareHelperUtil {

    //    //指定分享平台与顺序
    public static final SHARE_MEDIA[] SHARE_MEDIAS = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE};

    private ShareHelperUtil() {

    }


    private static HashMap<SHARE_MEDIA, String> map = new HashMap<>();

    static {
        if (map != null) {
            map.put(SHARE_MEDIA.WEIXIN, "微信");
            map.put(SHARE_MEDIA.QQ, "QQ");
            map.put(SHARE_MEDIA.QZONE, "QQ空间");
            map.put(SHARE_MEDIA.WEIXIN_CIRCLE, "微信朋友圈");
        }
    }

    public static String getShareName(SHARE_MEDIA dedia) {
        if (map != null) {
            return map.get(dedia);
        }
        return "";
    }

    /**
     * 使用友盟的默认分享面板
     *
     * @param activity
     * @param title            分享标题
     * @param content          分享内容
     * @param targetUrl        分享链接
     * @param umImage          分享缩略图
     * @param umShareListeners 分享事件监听
     */
    public static void defaultShare(Activity activity, String title, String content, String targetUrl, UMImage umImage, UMShareListener umShareListeners) {
        if (Config.dialog == null) {
            Config.dialog = new LoadingProgress(activity);
        }
        new ShareAction(activity).setDisplayList(SHARE_MEDIAS).withTitle(title).withText(content)
                .withTargetUrl(targetUrl).withMedia(umImage).setListenerList(umShareListeners).open();
    }

}

