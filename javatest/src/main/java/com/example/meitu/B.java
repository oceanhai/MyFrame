package com.example.meitu;

/**
 * 多态测试2
 */
public class B {

    public static void main(String[] args){
        method01();
    }

    public static void method01(){
        GrandFather grandFather = new GrandFather();
        System.out.println(grandFather.getName());
        System.out.println(grandFather.birth);
        System.out.println(grandFather.qq);
        System.out.println(grandFather.getAge());
        grandFather.method01();
        grandFather.method02();

        System.out.println("--------------------------");

        Father father = new Father();
        System.out.println(father.getName());
        System.out.println(father.birth);
        System.out.println(father.qq);
        System.out.println(father.getAge());
        System.out.println(father.getHeight());
        father.method01();
        father.method02();
        father.method03();

        System.out.println("--------------------------");

        GrandFather grandFather1 = new Father();
        System.out.println(grandFather1.getName());
        System.out.println(grandFather1.birth);
        System.out.println(grandFather1.qq);
        System.out.println(grandFather1.getAge());
//        System.out.println(grandFather1.getHeight());//提示错误，调用不了子类方法
        grandFather1.method01();
        grandFather1.method02();
//        grandFather1.method03();//提示错误，调用不了子类方法

    }

}
