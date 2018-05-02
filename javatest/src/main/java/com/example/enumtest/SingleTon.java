package com.example.enumtest;

/**
 * Created by wuhai on 2018/05/02 14:39.
 * 描述：
 */

public enum SingleTon {
    INSTANCE;

    private Instance instance = null;

    SingleTon(){
        instance = new Instance();
    }

    public Instance getInstance(){
        return instance;
    }
}
