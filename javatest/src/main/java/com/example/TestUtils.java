package com.example;

import java.util.List;

/**
 * Created by wuhai on 2016/9/28.
 */
public class TestUtils {

    public static String test01(String str){
        String newStr = str;
        if (newStr == null)
        {
            newStr = "";
        }
        return newStr;
    }

    public static void test02(List<String> list){
        if(list.isEmpty()){
            System.out.println("list null");
        }else{
            System.out.println("list no null");
        }
    }
}
