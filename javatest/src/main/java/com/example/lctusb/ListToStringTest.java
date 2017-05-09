package com.example.lctusb;

import com.example.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2017/05/09 18:54.
 * 描述：
 */

public class ListToStringTest {

    public static void main(String[] args){
        Person p1 = new Person("zhangsan",10);
        Person p2 = new Person("zhangsan",10);
        Person p3 = new Person("zhangsan",10);
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        System.out.println("list.tostring:"+list.toString());
    }

}
