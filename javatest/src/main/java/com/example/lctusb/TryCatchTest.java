package com.example.lctusb;

/**
 * Created by wuhai on 2017/05/04 17:18.
 * 描述：
 */

public class TryCatchTest {

    public static void main(String[] args){
        try {
            err();
        }catch (Exception e){
            System.out.println("main catch:"+e.getMessage());
        }finally {
            System.out.println("main finally:");
        }

    }

    public static void err(){
        try {
            int a = 100/0;
        }catch (Exception e){
            System.out.println("err() catch:"+e.getMessage());
            throw new RuntimeException("err()出现异常");
        }finally {
            System.out.println("err() finally:");
        }
    }
}
