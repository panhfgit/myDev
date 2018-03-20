/**
 * Copyright &copy; 2012-2014 <a href="https://github.com//jeesite">JeeSite</a> All rights reserved.
 */
package com.test.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyyMMdd"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }


    /**
     * 获取月份
     * @return
     */
    public static String getMonth(int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, amount);
        return DateFormatUtils.format(calendar.getTime(), "yyyyMM");
    }


    /**
     * 获取上个月
     * @return
     */
    public static String getLastMonth(){
        return getMonth(-1);
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取一段时间后的日期
     *
     * @param dt
     * @param str
     * @return
     */
    public static Date getDateAfterTime(Date dt, String str) throws Exception {
        long times = dt.getTime();
        Date date = new Date();
        if (!"".equals(str)) {
            String[] timesAry = str.split(",|，");
            for (String timeStr : timesAry) {
                String valueStr = "";
                String timeIUpperCase = timeStr.trim().toUpperCase();
                if (timeIUpperCase.endsWith("Y") && timeIUpperCase.length() >= 2) {//年
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addYear(times, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("M") && timeIUpperCase.length() >= 2) {//月
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addOrMinusMonth(times, Integer.parseInt(valueStr));
                } else if(timeIUpperCase.endsWith("T") && timeIUpperCase.length() >= 2){//天
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addOrMinusDays(times, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("D") && timeIUpperCase.length() >= 2) {//排除周六、周日和节假日的天数
//                    valueStr = timeStr.substring(0, timeStr.length() - 1);
//                    date = TimeUtil.addDateByWorkDay(dt, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("H") && timeIUpperCase.length() >= 2) {//小时
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addOrMinusHours(times, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("MIN") && timeIUpperCase.length() >= 4) {//分钟
                    valueStr = timeStr.substring(0, timeStr.length() - 3);
                    date = TimeUtil.addOrMinusMinutes(times, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("S") && timeIUpperCase.length() >= 2) {//秒
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addOrMinusSecond(times, Integer.parseInt(valueStr));
                } else if (timeIUpperCase.endsWith("W") && timeIUpperCase.length() >= 2) {//周
                    valueStr = timeStr.substring(0, timeStr.length() - 1);
                    date = TimeUtil.addOrMinusWeek(times, Integer.parseInt(valueStr));
                }
                times = date.getTime();
            }
        }
        return date;
    }

    //获取当月一号
    public static Date getFirstDay() {
        return parseDate(getYear() + "-" + getMonth() + "-01");
    }

    //获取下月一号
    public static Date getNextFirstDay() throws Exception {
        return getDateAfterTime(getFirstDay(), "1M");
    }

    /**
     * 获得系统当前时间
     */
    public static Date getCurrentDate() throws Exception {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public static int getCurrentQuarter() throws Exception{
        int month = Integer.parseInt(getMonth());
        if(month>=1 && month<=3){
            return 1;
        }else if(month >= 4 && month <= 6){
            return 2;
        }else if(month >= 7 && month <= 9){
            return 3;
        }else if(month >= 10 && month <= 12){
            return 4;
        }
       return 0;
    };
}
