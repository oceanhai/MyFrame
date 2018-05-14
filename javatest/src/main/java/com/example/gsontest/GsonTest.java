package com.example.gsontest;

import com.google.gson.Gson;

/**
 * Created by wuhai on 2018/5/14.
 */

public class GsonTest {

    private static Gson gson;

    public static void main(String[] args){
        gson = new Gson();
        method01();
        method02();
        method03();
        method04();
    }

    /**
     * 字段类型不一样
     */
    private static void method04() {
        User01 user01 = gson.fromJson(getUser04(),User01.class);
//        System.out.println("name="+user01.name+",age="+user01.age);
        System.out.println("name="+user01.name+",age="+user01.getAge());
    }

    /**
     * 多字段
     */
    private static void method03() {
        User01 user01 = gson.fromJson(getUser03(),User01.class);
        System.out.println("name="+user01.name+",age="+user01.age);
    }

    /**
     * 少字段
     */
    private static void method02() {
        User01 user01 = gson.fromJson(getUser02(),User01.class);
        System.out.println("name="+user01.name+",age="+user01.age);
    }

    /**
     * 正常
     */
    private static void method01() {
        User01 user01 = gson.fromJson(getUser01(),User01.class);
        System.out.println("name="+user01.name+",age="+user01.age);
    }

    private static String getUser01(){
        User01 user = new User01();
        user.name = "wuhai";
        user.age = "18";
        return gson.toJson(user);
    }
    private static String getUser02(){
        User02 user = new User02();
        user.name = "wuhai";
        return gson.toJson(user);
    }
    private static String getUser03(){
        User03 user = new User03();
        user.name = "wuhai";
        user.age = "18";
        user.address = "北京";
        return gson.toJson(user);
    }
    private static String getUser04(){
        User04 user = new User04();
        user.name = "wuhai";
        user.age = 18;
        return gson.toJson(user);
    }

}
