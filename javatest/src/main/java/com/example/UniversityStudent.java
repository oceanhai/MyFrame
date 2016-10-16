package com.example;

/**
 * Created by Administrator on 2016/10/15.
 */
public class UniversityStudent extends Student{
    private int height = 173;
    private String name;
    private boolean practice;

    public UniversityStudent(){
        super("daxue", 24);
        practice = true;
    }

    @Override
    public void study(){
        super.study();
        System.out.println("study more");
    }

    @Override
    public void eat() {
        System.out.println("eat more better");
    }

    public static void gotoShchool(){
        System.out.println("go to University Shchool ");
    }

    public void print(){
        System.out.println("name="+name+",super.name="+super.getName()+",height="+height+"super.height="+super.height);
    }
}
