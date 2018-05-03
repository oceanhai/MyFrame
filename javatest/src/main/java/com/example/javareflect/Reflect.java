package com.example.javareflect;

/**
 * Created by wuhai on 2018/4/27.
 */

public class Reflect {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void method01(String str){
        System.out.println("str="+str);
    }
}
