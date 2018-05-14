package com.example.javabase.duotai;

/**
 * Created by wuhai on 2018/5/14.
 */

public class Teacher implements Singer,Painter {

    private String name;
    public Teacher(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void teach(){
        System.out.println("teaching");
    }
    public void paint() { // @Override
        System.out.println("teacher is painting");
    }
    public void eat() { // @Override
        System.out.println("teacher is eating");
    }
    public void sing() { // @Override
        System.out.println("teacher is singing");
    }
    public void sleep() { // @Override
        System.out.println("teacher is sleeping");
    }
}
