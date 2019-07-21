package com.example.maptest;

import java.util.HashMap;

/**
 * Created by wuhai on 2019/4/26.
 */

public class MapTest {

    public static void main(String[] args){
        HashMap map = new HashMap();
        map.put("1","string1");
        map.put("2","string2");
        map.put("3","string4");
        map.put("5","string6");

        System.out.println(map.toString());
    }

}
