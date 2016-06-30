package com.test.xujixiao.xjx.util;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.CustomNotification;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xujixiao on 2015/12/2.
 */
public class CustomNotificationUtils {
    public void buildNotification(String receiverId, SessionTypeEnum sessionType) {
        // 构造自定义通知，指定接收者
        CustomNotification notification = new CustomNotification();
        notification.setSessionId(receiverId);
        notification.setSessionType(sessionType);

        // 构建通知的具体内容。为了可扩展性，这里采用 json 格式，以 "id" 作为类型区分。
        // 这里以类型 “1” 作为“正在输入”的状态通知。
        try {
            JSONObject json = new JSONObject();
            json.put("id", "1");
            notification.setContent(json.toString());
            // 发送自定义通知
            NIMClient.getService(MsgService.class).sendCustomNotification(notification);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
