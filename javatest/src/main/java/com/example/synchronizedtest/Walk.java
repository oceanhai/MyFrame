package com.example.synchronizedtest;

import com.example.StaticTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 锁静态方法和非静态方法的区别
 * 参考http://greemranqq.iteye.com/blog/1974143
 * 及有道笔记 面试
 */
public class Walk {
    public static int num = 100;
    static Object object = new Object();
    public static Walk walk = new Walk();
    // 静态
    public static int run(){
        synchronized (walk){
            int i = 0;
            while (i < 10) {
                try {
                    num --;
                    i++;
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return num ;
        }
    }
    // 非静态
    public synchronized int  walk(){
//        synchronized(object){
            int i = 0;
            while (i < 10) {
                try {
                    num --;
                    i++;
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return num ;
//        }
    }

    // 先建立两个测试类，这里我们默认循环10次
    static class T3 implements Runnable {
        @Override
        public void run() {
//            Walk walk = new Walk();
            Walk walk = Walk.walk;
            walk.walk();
        }
    }

    static class T1 implements Runnable{
        @Override
        public void run() {
//            Walk walk = new Walk();
            Walk walk = Walk.walk;
            // 这里我依然用的new
            walk.run();
        }
     }

    public static void main(String[] args) {
        Thread t1 = new  Thread(new T1());
        Thread t3 = new  Thread(new T3());
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(t1);
        es.execute(t3);
        es.shutdown();
    }
}
