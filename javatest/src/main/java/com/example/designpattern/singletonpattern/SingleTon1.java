package com.example.designpattern.singletonpattern;

/**
 * Created by wuhai on 2017/10/19 14:55.
 * 描述：懒汉式
 */

public class SingleTon1 {

    private static SingleTon1 instance = null;

    private SingleTon1() {
    }

    public static synchronized SingleTon1 getInstance(){
        if(instance == null){
            instance = new SingleTon1();
        }
        return instance;
    }
}
