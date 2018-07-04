package com.example.javabase;

import org.junit.Test;

/**
 * Created by wuhai on 2018/7/4.
 */

public class ThreadTest {

    private Integer lock = 0;
    private boolean ok = false;//这个开关没用其实，因为x特定值时候就起到开关作用

    @Test
    public void method01(){

        //子线程 执行完之后，唤醒一个等待的线程
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int x=0;x<50;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread-1:"+x);
                }
                synchronized (lock){
//                    ok = true;
                    lock.notify();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int x=0;x<50;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread-2:"+x);
                }
            }
        }).start();

        //主线程 x=25时候 释放锁并等待
        for (int x=0;x<50;x++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main:"+x);
            if(x == 25){
                synchronized (lock){
//                    if(!ok) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                    }
                }
            }
        }
    }

}
