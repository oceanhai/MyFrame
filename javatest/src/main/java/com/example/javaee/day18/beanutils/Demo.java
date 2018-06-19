package com.example.javaee.day18.beanutils;

import java.util.Arrays;

/*
 *  java.util.Arrays
 *  数组的工具类
 *  方法toString(数组)
 *  变成一个字符串
 */
public class Demo {
	public static void main(String[] args) {
		String[] str = {"aaa","cc","ee","b"};
		String s = Arrays.toString(str);
		System.out.println(s);
	}
	public static boolean equals(String s1,String s2){
		return s1.equals(s2);
	}
}
