package com.example.meitu;

/**
 * 爷爷类
 */
public class GrandFather {
    private String name = "GrandFather";
    public String birth = "1986";
    public static String qq = "119";
    private int age = 30;

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

    public void method01(){
        System.out.println("GrandFather:method01()");
    }

    public static void method02(){
        System.out.println("GrandFather:method02()");
    }
}
