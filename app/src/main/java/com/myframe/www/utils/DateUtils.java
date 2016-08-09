package com.myframe.www.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期utils
 */
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(strDate, new ParsePosition(0));
    }


    public static String getDateString(Date date, String separate) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR) + separate + (c.get(Calendar.MONTH) + 1) +
                separate + c.get(Calendar.DAY_OF_MONTH);
    }


    public static Long getDateString() {

        return System.currentTimeMillis();
    }


    public static int getWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return i;
    }


    public static String getCookieDate() {
        Date date = new Date();
        date.setTime(date.getTime() + 57600 * 1000);
        String s = date.toString();
        return s;
    }

    /**
     * 根据format，把毫秒值转换成想要的日期形式
     * @param format
     * @param longTime
     * @return
     */
    public static String getDateString(String format, long longTime) {
        SimpleDateFormat sformat = null;
        if (format == null || "".equals(format)) {
            sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        } else {
            sformat = new SimpleDateFormat(format, Locale.getDefault());
        }
        if (longTime == 0) {
            return sformat.format(new java.sql.Date(System.currentTimeMillis()));
        }
        return sformat.format(new java.sql.Date(longTime));
    }
}
