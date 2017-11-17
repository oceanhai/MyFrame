package com.example.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wuhai on 2017/11/16 11:05.
 * 描述：
 */

public class LockTest01 {

    private Lock lock = new ReentrantLock();    //注意这个地方

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args){

        LockTest01 lockTest01 = new LockTest01();

        new Thread(){
            public void run() {
                lockTest01.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                lockTest01.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread){
        /**
         * 注释掉的锁是局部锁，所以每个线程都持有一个锁，互不影响
         */
//        Lock lock = new ReentrantLock();    //注意这个地方

        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
