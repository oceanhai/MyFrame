package com.myframe.www.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Created by wuhai on 2015/11/17.
 * 常见工具类
 */
public class CommonUtils {
    /**
     * 社区 日期显示规则
     * 注意createTime是秒为单位
     */
    public static String createTime(long createTime) {
        Calendar curCalendar = Calendar.getInstance();//当前日历
        Long currentTime = System.currentTimeMillis() / 1000;// 当前时间
        int cur_year = curCalendar.get(Calendar.YEAR);//年

        Calendar oldCalender = Calendar.getInstance();
        oldCalender.setTimeInMillis(createTime * 1000);
        int old_year = oldCalender.get(Calendar.YEAR);//年

        Long mss = currentTime - createTime;// 时间差
        long days = mss / ( 60 * 60 * 24);
        long hours = mss / ( 60 * 60 );
        long minutes = mss / 60;

        if (cur_year > old_year) {//去年
            return DateUtils.getDateString(null, createTime * 1000);//"yyyy-MM-dd"
        } else if (days >= 7)//大于等于7天
            return DateUtils.getDateString("MM-dd", createTime * 1000);//"MM-dd"
        else if (days >= 1 && days < 7)//大于等于1天且小于7天
            return days + "天前";
        else if(hours >= 1 && hours < 24)
            return  hours+"小时前";
        else if(minutes >= 1 && minutes < 60)
            return minutes+"分钟前";
        else
            return "刚刚";
    }

    /**
     * Assets 资源文件转换成 string
     * Assets里可放josn数据
     * @param fileName
     * @param context
     * @return
     */
    public static String getFromAssets(String fileName, Context context) {
        StringBuffer json = new StringBuffer();
        //.getClass().getClassLoader().getResourceAsStream("assets/" + "query.json");
        InputStream is = null;
        try {
            is = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is==null){
            Log.e("fileName err", "fileName 路径有问题");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                // sb.append(line + "/n");
                sb.append(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
