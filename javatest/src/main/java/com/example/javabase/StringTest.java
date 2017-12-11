package com.example.javabase;

import java.text.Format;

/**
 * Created by wuhai on 2017/12/06 15:46.
 * 描述：
 */

public class StringTest {

    public static void main(String[] args){
        method02();
    }

    /**
     * 4位，前面不足自动补零
     */
    public static void method01(){
        String str = "1";
        String newstr = String.format("%04d",Integer.valueOf(str));
        System.out.println(newstr);
    }

    public static void method02(){
        String str = "5.00";
        if(Double.valueOf(str).equals(Double.valueOf(str))){
            System.out.println(1);
        }else{
            System.out.println(2);
        }

        if(Double.valueOf(str)==Double.valueOf(str)){
            System.out.println(1);
        }else{
            System.out.println(2);
        }
    }
}
