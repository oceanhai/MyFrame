package com.example;

/**
 * Created by Administrator on 2016/10/15.
 */
public class Student{
    public int height = 100;
    private String name;
    private int age;

    public Student(){
        this("hah", 19);
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public void study(){
        this.eat();
        System.out.println("study");
    }

    public void eat(){
        System.out.println("eat");
    }

    public void sleep(){
        System.out.println("sleep");
    }

    public static void gotoShchool(){
        System.out.println("go to Shchool ");
    }

}
