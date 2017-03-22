package com.example.lctusb;

/**
 * Created by wuhai on 2017/03/07 14:28.
 * 描述：
 */

public class SplitTest {

    public static void main(String[] args){
        String str = "2017-03-03 00:00:00";
        String[] time = str.split("-| |:");
        for(int x=0;x<time.length;x++){
            System.out.println(time[x]);
        }
    }

}
