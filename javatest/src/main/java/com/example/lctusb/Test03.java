package com.example.lctusb;

/**
 * Created by wuhai on 2017/03/24 14:38.
 * 描述：金额
 */

public class Test03 {

    public static void main(String[] args){
//        method01(8.85, 5.0);
//        method01(6, 5.99);
//        method01(6, 5.99);
//        method02(13.41, 0);

        method03(2.235*Double.valueOf(45));//100.57
        method03(2.235*43);//96.10
//        method03(100.575);//100.58
//        method03(96.105);//96.11
//        method03(100.57499999999999);//100.57
//        method03(96.10499999999999);//96.10
    }

    public static void method01(double num1, double num2){
        System.out.println(num1 - num2);
        System.out.println(StringUtils.getDecimalFormat(null,num1 - num2));
    }

    public static void method02(double num1, double num2){
        System.out.println(ArithUtils.formatDouble2(num1-num2));
    }

    public static void method03(double num1){
        System.out.println("入参:"+num1);
        System.out.println(StringUtils.getDecimalFormat(null,num1));
    }
}
