package com.example.designpattern.singletonpattern;

/**
 * Created by wuhai on 2017/10/19 14:58.
 * 描述：线程不安全的
 * 虽然加了锁，但一样线程不安全
 null == instance 判断的是instance分配的内存是否存在，即使内存存在但也不一定构造完全
 new 一个对象时候是先分配内存，然后执行构造方法....
 这样就造成其他线程可能跳过if直接返回instance ,但这时候还没执行完构造方法
 综上 线程并不安全
 */
public class SingleTon3 {
    private static SingleTon3 ourInstance = null;

    public static SingleTon3 getInstance() {
        if(ourInstance == null){
            synchronized (ourInstance){
                ourInstance = new SingleTon3();
            }
        }
        return ourInstance;
    }

    private SingleTon3() {
    }
}
