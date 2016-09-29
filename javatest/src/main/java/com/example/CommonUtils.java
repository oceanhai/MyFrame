package com.example;

/**
 * Created by wuhai on 2016/9/27.
 */
public class CommonUtils {

    /**
     * 算法  输入不限制int型，不使用方法前提下挨个输出数字
     * @param num
     */
    public static void printInt(int num){
        if(num > 10){
            printInt(num/10);
        }
        System.out.print(num%10);
    }

}
