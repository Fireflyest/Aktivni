package com.fireflyest.aktivni.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Fireflyest
 */
public class TimeUtils {

    private TimeUtils(){

    }

    /**
     * 获取当前月份
     * @return int
     */
    public static int getYear(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return int
     */
    public static int getMonth(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH)+1;
    }

    /**
     * 获取当天是当月第几天
     * @return int
     */
    public static int getDay(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前小时
     * @return int
     */
    public static int getHour(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR);
    }

    /**
     * 获取当前分钟
     * @return int
     */
    public static int getMinute(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE);
    }

    /**
     * 获取当前秒钟
     * @return int
     */
    public static int getSecond(){
        return Calendar.getInstance(Locale.CHINA).get(Calendar.SECOND);
    }

    /**
     * 获取当前日期字符串
     * @return String
     */
    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(Calendar.getInstance(Locale.CHINA).getTime());
    }

    /**
     * 获取当前时间数据
     * @return long
     */
    public static long getDate(){
        return Calendar.getInstance(Locale.CHINA).getTime().getTime();
    }

    public static int getMaxDay(){
        return new GregorianCalendar().getActualMaximum(Calendar.DATE);
    }

    public static int getFirstDay(){
        Calendar first = new GregorianCalendar();
        first.set(Calendar.DAY_OF_MONTH, 1);
        return first.get(Calendar.DAY_OF_WEEK);
    }

}
