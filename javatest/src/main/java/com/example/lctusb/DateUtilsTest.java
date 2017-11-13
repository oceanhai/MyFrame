package com.example.lctusb;

/**
 * Created by wuhai on 2017/10/31 13:50.
 * 描述：
 */

public class DateUtilsTest {

    public static void main(String[] args){
        //近一个月的 畅销排序
        long  oneMonthAgoTime = System.currentTimeMillis()-30L*24*60*60*1000;
        String oneMonthAgoStr = DateUtils.getDateString("yyyy-MM-dd HH:mm:ss",oneMonthAgoTime);
        System.out.println(oneMonthAgoStr);
    }
}
