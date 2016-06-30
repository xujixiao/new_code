package com.test.xujixiao.xjx.util;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 日期格式 yyyy-MM-dd
     */
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * 日期格式 hh:mm
     */
    public final static String FORMAT_TIME = "HH:mm";

    /**
     * 日期格式 hh:mm:ss
     */
    public static final String FORMAT_TIME_ALL = "HH:mm:ss";

    /**
     * 日期格式 yyyy-MM-dd hh:mm
     */
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式 MM月dd日 hh:mm
     */
    public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日 HH:mm";

    /**
     * 日期格式 yyyy-MM-dd hh:mm:ss
     */
    public final static String FORMAT_DATE_TIME_ALL = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 365 * 24 * 60 * 60;// 年
    private static final int MONTH = 30 * 24 * 60 * 60;// 月
    private static final int DAY = 24 * 60 * 60;// 天
    private static final int HOUR = 60 * 60;// 小时
    private static final int MINUTE = 60;// 分钟

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 根据时间戳获取指定格式的时间，如2011-11-30 08:40
     *
     * @param timestamp 时间戳 单位为毫秒
     * @param format    指定格式 如果为null或空串则使用默认格式"yyyy-MM-dd HH:MM"
     * @return String
     */
    public static String getFormatTimeFromTimestamp(long timestamp, String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.valueOf(sdf.format(new Date(timestamp)).substring(0, 4));
            if (currentYear == year) {// 如果为今年则不显示年份
                sdf.applyPattern(FORMAT_MONTH_DAY_TIME);
            } else {
                sdf.applyPattern(FORMAT_DATE_TIME);
            }
        } else {
            sdf.applyPattern(format);
        }
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    /**
     * 根据时间戳获取时间字符串，并根据指定的时间分割数partionSeconds来自动判断返回描述性时间还是指定格式的时间
     *
     * @param timestamp      时间戳 单位是毫秒
     * @param partionSeconds 时间分割线，当现在时间与指定的时间戳的秒数差大于这个分割线时则返回指定格式时间，否则返回描述性时间
     * @param format
     * @return
     */
    public static String getMixTimeFromTimestamp(long timestamp, long partionSeconds, String format) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        if (timeGap <= partionSeconds) {
            return getDescriptionTimeFromTimestamp(timestamp);
        } else {
            return getFormatTimeFromTimestamp(timestamp, format);
        }
    }

    /**
     * 时间格式化 yyyy-MM-dd
     *
     * @param str 时间戳
     * @return 格式化后时间
     */
    public static String format(String str) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(str));
            return (String) DateFormat.format("yyyy-MM-dd", calendar);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 时间格式化 yyyy-MM-dd
     *
     * @param time 时间戳
     * @return 格式化后时间
     */
    public static String format(long time) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            return (String) DateFormat.format("yyyy-MM-dd", calendar);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getCurrentTime(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * 将日期字符串以指定格式转换为Date
     *
     * @param timeStr 日期字符串
     * @param format  指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static Date getDateFromString(String timeStr, String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        try {
            return sdf.parse(timeStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 将Date以指定格式转换为日期时间字符串
     *
     * @param date   日期
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getStringFromDate(Date date, String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(date);
    }

    public static long getLongFormString(String timeStr, String format) {
        return getDateFromString(timeStr, format).getTime();
    }

    /**
     * 获取日期
     *
     * @return 年月日时分秒
     */
    public static String getTime() {
        sdf.applyPattern("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * 秒转化为时分秒
     *
     * @param time
     * @return
     */
    public static String getTime(long time) {
        long tempTime = time;
        StringBuffer buffer = new StringBuffer();

        long mins = tempTime / 60;
        long house = mins / 60;
        long min = mins % 60;
        long ss = tempTime % 60;

        if (house > 0) {
            buffer.append(house + "小时");
        }

        if (house > 0 || min > 0) {
            buffer.append(((min < 10 && house > 0) ? "0" + min : min) + "分");
        }

        buffer.append((ss < 10 ? "0" + ss : ss) + "秒");

        return buffer.toString();
    }

    /**
     * 秒转化为 -天-小时-分
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        long tempTime = time;
        StringBuffer stringBuffer = new StringBuffer();

        long tempMins = tempTime / 60;// 总分钟

        long tempHouse = tempMins / 60;

        long day = tempHouse / 24;

        long house = tempHouse % 24;

        long min = tempMins % 60;

        long ss = tempTime % 60;

        stringBuffer.append(day + "天" + (house < 10 ? "0" + house : house) + "小时" + (min < 10 ? "0" + min : min) + "分" + (ss < 10 ? "0" + ss : ss) + "秒");
        return stringBuffer.toString();
    }

    /**
     * 得到几天/周/月/年后的日期，整数往后推,负数往前移动
     *
     * @param calendar
     * @param calendarType Calendar.DATE,Calendar.WEEK_OF_YEAR,Calendar.MONTH,Calendar.
     *                     YEAR
     * @param next
     * @return
     */
    public String getDayByDate(Calendar calendar, int calendarType, int next) {

        calendar.add(calendarType, next);
        Date date = calendar.getTime();
        return date_Formater_1.format(date);

    }

    public SimpleDateFormat date_Formater_1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");


    public SimpleDateFormat date_Formater_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static int comparaDate(String DATE1, String DATE2) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
