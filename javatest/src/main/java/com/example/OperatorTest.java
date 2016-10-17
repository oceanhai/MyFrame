package com.example;

/**
 * Created by wuhai on 2016/10/14.
 */
public class OperatorTest {

    public static void main(String arg[]){
        String name = "lol";
        Person person = new Person("zhangsan", 20);
        int x = 3;
        System.out.println(!person.getName().equals("zhangsan"));

        System.out.println(!person.getName().equals("zhangsan") && x > 0 && x < 4);
    }
}
