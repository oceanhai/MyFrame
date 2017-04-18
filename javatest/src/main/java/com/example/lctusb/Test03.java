package com.example.lctusb;

/**
 * Created by wuhai on 2017/03/24 14:38.
 * 描述：
 */

public class Test03 {

    public static void main(String[] args){
        double num1 = 8.85;
        double num2 = 5.0;
        System.out.println(num1 - num2);
        System.out.println(StringUtils.getDecimalFormat(null,num1 - num2));
    }
}
