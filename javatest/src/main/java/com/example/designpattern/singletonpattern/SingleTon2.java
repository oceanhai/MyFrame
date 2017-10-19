package com.example.designpattern.singletonpattern;

/**
 * Created by wuhai on 2017/10/19 14:57.
 * 描述：饿汉式
 */
public class SingleTon2 {

    private static SingleTon2 ourInstance = new SingleTon2();

    public static SingleTon2 getInstance() {
        return ourInstance;
    }

    private SingleTon2() {
    }
}
