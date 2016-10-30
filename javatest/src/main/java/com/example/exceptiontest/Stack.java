package com.example.exceptiontest;

/**
 * 栈溢出
 */
public class Stack {
    public static void main(String[] args){
        new Stack().test();
    }

    public void test(){
        test();
    }
}
