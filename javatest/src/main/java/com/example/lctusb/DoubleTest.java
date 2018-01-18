package com.example.lctusb;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by wuhai on 2017/08/18 17:34.
 * 描述：http://www.jb51.net/article/46010.htm
 */

public class DoubleTest {

    public static void main(String[] args){
//        double double1 = 5 * 5;
//        System.out.println(double1);

//        System.out.println(Double.parseDouble("1"));
//        System.out.println(Double.parseDouble("2.00"));

//        method01();
//        method02("-4.440892098500626E-16");
        method03();
    }

    public static void method03(){
        double d1 = 0.26f;
        double d2 = 0.26f;
        double d3 = d1 - d2;
        System.out.println(d3);
    }

    public static void method01(){
        DecimalFormat    df   = new DecimalFormat("######0.00");

        double d1 = 3.23456;
        double d2 = 0.0;
        double d3 = 2.0;
        double d4 = 3.23556;
        double d5 = 12345678.23556;
        double d6 = 3.29556;
        System.out.println(df.format(d1));
        System.out.println(df.format(d2));
        System.out.println(df.format(d3));
        System.out.println(df.format(d4));
        System.out.println(df.format(d5));
        System.out.println(df.format(d6));

        System.out.println(Double.valueOf(df.format(d1)));
        System.out.println(Double.valueOf(df.format(d2)));
        System.out.println(Double.valueOf(df.format(d3)));
        System.out.println(Double.valueOf(df.format(d4)));

        method02("3.33066907387547e-16");
    }

    /**
     * 客户计数法显示  转换成 具体数字
     * @param str
     */
    public static void method02(String str){
        BigDecimal bd = new BigDecimal(str);
        System.out.println(bd.toPlainString());
    }
}
