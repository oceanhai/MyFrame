package com.example.lctusb;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuhai on 2017/06/27 11:02.
 * 描述：
 */

public class LinkedListTest {

    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        list.offerFirst("第1插入");
        list.offerFirst("第2插入");
        list.offerFirst("第3插入");
        method01(list);
    }

    public static void method01(List<String> list){
        for(int x=0;x<list.size();x++){
            System.out.println(list.get(x));
        }
    }
}
