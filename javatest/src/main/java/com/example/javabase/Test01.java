package com.example.javabase;

/**
 * Created by wuhai on 2018/01/04 9:53.
 * 描述：
 */

public class Test01 {

    private static long mLastClickTime;

    public static void main(String[] args){
        for (int x=0;x<10;x++){
            method01();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void method01() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        System.out.println("time="+time+",mLastClickTime="+mLastClickTime+"，timeD="+timeD);
        mLastClickTime = time;
    }
}
