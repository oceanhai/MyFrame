package com.example.meitu;

/**
 * 父类
 */
public class Father extends GrandFather {
    private String name = "Father";
    public String birth = "2016";
    public static String qq = "110";
    private int height = 173;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void method01() {
        System.out.println("Father:method01()");
    }

    public static void method02(){
        System.out.println("Father:method02()");
    }

    public void method03(){
        System.out.println("Father:method03()");
    }
}
