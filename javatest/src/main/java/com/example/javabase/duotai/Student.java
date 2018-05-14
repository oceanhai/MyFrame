package com.example.javabase.duotai;

/**
 * Created by wuhai on 2018/5/14.
 */

public class Student implements Singer {
    private String name;
    public Student(String name) {
        this.name = name;
    }
    public void study(){
        System.out.println("studying");
    }
    public String getName(){
        return name;
    }

    @Override
    public void sing() { // @Override
        System.out.println("student is singing");
    }
    @Override
    public void sleep() { // @Override
        System.out.println("student is sleeping");
    }
}
