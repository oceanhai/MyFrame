package com.example.lctusb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by wuhai on 2017/02/10 17:03.
 * 描述：
 */

public class Test01 {

    public static void main(String[] args){
        long time = System.currentTimeMillis();
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String dateStr = formatData(time, sformat);
        System.out.println("dateStr="+dateStr);

        for (int i=0;i<5;i++){
            System.out.println(getFixLenthString(7));
        }
    }

    public static String formatData(long timestamp, DateFormat sdf) {
        String format = "";
        try {
            Date date = new Date(timestamp);
            format = sdf.format(date);
        } catch (Exception e) {

        }
        return format;
    }

    /**
     * 返回长度为【strLength】的随机数，在前面补0
     */
    public static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        System.out.println(pross);
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(2, strLength + 2);
    }
}
