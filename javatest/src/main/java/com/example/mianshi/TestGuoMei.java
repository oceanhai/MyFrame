package com.example.mianshi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 国美金控
 */
public class TestGuoMei {
    public static void main(String[] args){
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        List<Object> list2 = new ArrayList<>();
        list2.add("b");
        Map map1 = new HashMap();
        map1.put("1","hehe1");
        Map map2 = new HashMap();
        map2.put("1","hehe2");

        list1.add(list2);
        list1.add(map1);

        map1.put("1",list1);
        map1.put("2",map2);

    }
}
