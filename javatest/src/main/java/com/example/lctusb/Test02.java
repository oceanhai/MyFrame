package com.example.lctusb;

/**
 * Created by wuhai on 2017/03/24 14:38.
 * 描述：
 */

public class Test02 {

    public static void main(String[] args){

        System.out.println(Integer.valueOf("1659"));
        System.out.println(Integer.valueOf("8963"));

        String str = String
                .format("%4d:%4d",
                        new Object[] {
                                Integer.valueOf("1659"),
                                Integer.valueOf("8963") });
        System.out.println(str);

        if (String
                .format("%4d:%4d",
                        new Object[] {
                                Integer.valueOf("1659"),
                                Integer.valueOf("8963") }).equals("1659:8963")) {
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
}
