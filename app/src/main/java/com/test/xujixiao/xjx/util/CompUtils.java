package com.test.xujixiao.xjx.util;

import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.Comparator;

/**
 * Created by xujixiao on 2016/6/1.17:28
 * 邮箱：1107313740@qq.com
 */
public class CompUtils {
    public static Comparator<RecentContact> comp = new Comparator<RecentContact>() {

        @Override
        public int compare(RecentContact o1, RecentContact o2) {
            long time = o1.getTime() - o2.getTime();
            return time == 0 ? 0 : (time > 0 ? -1 : 1);
        }
    };
}
