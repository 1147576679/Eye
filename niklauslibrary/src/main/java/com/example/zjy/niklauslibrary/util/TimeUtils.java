package com.example.zjy.niklauslibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zjy on 2017/1/6.
 */

public class TimeUtils {
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00\'00\'";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "\'" + unitFormat(second)+"\"";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 返回系统时间
     * @param
     * @return 系统时间 long
     *
     * */
    public static long CurrentTime() {

        return System.currentTimeMillis();

    }

    /**
     * 返回年-月-日 hh:ff:ss
     *
     * @param time
     * long系统时间的long类型
     * @return String yyyy-MM-dd HH:mm:ss类型的时间类型
     * */
    public static String getFormatTime(long time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(time);

        String formatTime = format.format(date);

        return formatTime;

    }

    /**
     * 星期几
     *
     * @param
     * @return 星期一到星期日
     *
     * */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 星期几
     *
     * @param time
     * long 系统时间的long类型
     * @return 星期一到星期日
     *
     * */
    public static String getWeekOfDate(long time) {

        Date date = new Date(time);
//        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 返回年-月-日 hh:ff:ss 星期几
     *
     * @param time
     * long系统时间
     * @return String 例如2013-05-26 19:39:26 星期日
     *
     * */

    public static String getDateAndWeek(long time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");

        Date date = new Date(time);

        String dateString = format.format(date);

        String dayString = getWeekOfDate(date);

        return dateString + " " + dayString;

    }

    /**
     * 将CST时间类型字符串进行格式化输出
     * @param
     * @return
     * @throws ParseException
     */
    public static String CSTFormat(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd", Locale.ENGLISH);
        Date date = new Date(time);
        return formatter.format(date);
    }
}
