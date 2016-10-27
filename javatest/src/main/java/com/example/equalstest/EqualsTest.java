package com.example.equalstest;

import java.lang.reflect.Method;

/**
 * ==  和 Equals 的区别
 */
public class EqualsTest {

    public static void main(String[] arg){
        method01();
    }

    public static void method01(){
        Integer a = 10;
        Integer b = 10;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        String str1 = "abc";//"abc"在常量池，引用str1存储的是"abc"在常量池的地址
        String str2 = "abc";//"abc"在常量池，引用str1存储的是"abc"在常量池的地址
        //new String("abc") 在堆上分配内存，引用str2存储的是new String("abc") 在堆上的内存地址
        //还有一点区别是str1的类加载时就完成了初始化，而str2 要在执行引擎执行到那一行代码时才完成初始化。
        String str3 = new String("abc");

        System.out.println(str1 == str2);//地址相同
        System.out.println(str1.equals(str2));//地址相同 内容都是"abc"
        System.out.println(str1 == str3);//地址不同   false
        System.out.println(str1.equals(str3));//地址不同  但内容是相同的(String 类型)

        System.out.println("str1.hashCode():"+str1.hashCode());
        System.out.println("str2.hashCode():"+str2.hashCode());
        System.out.println("str3.hashCode():"+str3.hashCode());
    }
}
