package com.test.xujixiao.xjx.util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.test.xujixiao.xjx.widget.CommonDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by xujixiao on 2016/8/4.13:26
 * 邮箱：1107313740@qq.com
 */
public class Tools {
    public static InputFilter[] mInputFilter = new InputFilter[]{Tools.emojiFilter};

    /**
     * 过滤emoji表情内容
     */
    public static InputFilter emojiFilter = new InputFilter() {

        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };
    private static CommonDialog mDialog;


    /**
     * 设置文本字体指定颜色高亮
     *
     * @param mTvMessage
     * @param start
     * @param end
     * @param color
     */
    public static void setStringLight(TextView mTvMessage, int start, int end, int color) {
        CharSequence charSequence = mTvMessage.getText();
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvMessage.setText(spannableString);
    }

    /**
     * 科学计数法转为非科学计数法
     *
     * @param d
     * @return
     */
    public static String doubleToStr(double d) {
        DecimalFormat format = new DecimalFormat("#####0.00");
        String str = "";
        double db = new Double(String.valueOf(d));
        str = format.format(db);
        return str;
    }

    public static String doubleToStrMutil(double d) {
        DecimalFormat format = new DecimalFormat("#####0.0000000");
        String str = "";
        double db = new Double(String.valueOf(d));
        str = format.format(db);
        return str;
    }

    public static boolean isSuccess(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.has("success")) {
                return jsonObject.getBoolean("success");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getMsg(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.has("msg")) {
                return jsonObject.getString("msg");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    public static void setTextBold(TextView textBold, int start, int end) {
        if (textBold != null) {
            try {
                SpannableString spannableString = new SpannableString(textBold.getText());
                spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                textBold.setText(spannableString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取带星号的电话号码
     *
     * @param pNumber
     * @return
     */
    public static String getTelephoneStr(String pNumber) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return pNumber;
    }

    /**
     * 获取标准的距离标示
     *
     * @param distance
     * @return
     */
    public static String getDistanceStr(String distance) {
        if (TextUtils.isEmpty(distance) || distance.equals("0")) {
            return "未知距离";
        }
        int tempDis = Integer.parseInt(distance);
        if (tempDis > 1000) {
            return String.format(Locale.CHINA, "%.1f", tempDis / 1000.0f) + "km";
        } else if (tempDis < 1000 && tempDis > 100) {
            return tempDis + "m";
        } else {
            return "<100m";
        }
    }


    public interface IDialogConfirm {
        public void confirm();
    }

    /**
     * 构建一个常规的dialog
     *
     * @param context
     * @param message
     * @param iDialogConfirm
     */
    public static void buildDialog(Context context, String message, final IDialogConfirm iDialogConfirm) {
        mDialog = new CommonDialog.Builder(context).setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (iDialogConfirm != null) {
                    iDialogConfirm.confirm();
                }
                mDialog.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDialog.dismiss();
            }
        }).create();
    }

    public static void toast(String msg) {
        ToastUtils.showShortToast(NimUIKit.getContext(), msg);
    }
}
