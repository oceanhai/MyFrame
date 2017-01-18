package com.example.lctusb;

/**
 * Created by wuhai on 2017/01/17 15:47.
 * 描述：
 */

public class USBTest01 {

    public static void main(String[] args){
        int youNumber = 1;
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String str = String.format("% 4d", youNumber);
        System.out.println(str); // 0001

        double num01 = 0.00;
        String str01 = String.format("% 4f",num01);
        System.out.println(str01);

        /**
         * 字符串
         * 长度6
         * 不够后面补" "
         */
        String num02 = "0.00";
        String str02 = String.format("%-6s",num02);
        System.out.println(str02);
        String num03 = "198.23";
        String str03 = String.format("%-6s",num03);
        System.out.println(str03);

        /**
         *
         */
        System.out.println(String.format("%09d", 123));//长度9，数字前面补0
        System.out.println(String.format("%9s", "123"));//长度9，字符串前面补" "  TODO 这个正是我想要的
        System.out.println(String.format("%09d", Long.parseLong("123")));//长度9，字符串前面补0
    }

}
