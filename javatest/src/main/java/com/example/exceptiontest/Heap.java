package com.example.exceptiontest;

import java.util.ArrayList;

/**
 * 堆溢出
 */
public class Heap {
    public static void main(String[] args){
        ArrayList list=new ArrayList();
        while(true){
            list.add(new Heap());
        }
    }
}
