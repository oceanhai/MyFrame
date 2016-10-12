package com.example;

/**
 * Created by wuhai on 2016/10/11.
 */
public class Outer {
    void method(){
        Inner ic = new Inner();//Causes generation of accessor class
    }
    public class Inner {
        private Inner(){}
    }
}
