package com.example;

/**
 * Created by wuhai on 2016/9/27.
 */
public class CommonUtils {

    /**
     * 算法  输入不限制int型，不使用方法前提下挨个输出数字
     * @param num
     */
    public static void printInt(int num){
        if(num > 10){
            printInt(num/10);
        }
        System.out.print(num%10);
    }

    /**
     * 找最大值
     * @param arr
     * @return
     */
//    public static Comparable findMax(Comparable[] arr){
//        int maxIndex = 0;
//        for (int i =1; i< arr.length;i++){
//            if (arr[i].compareTo(arr[maxIndex]) > 0){
//                maxIndex = i;
//            }
//        }
//        return arr[maxIndex];
//    }
    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr){
        int maxIndex = 0;
        for (int i =1; i< arr.length;i++){
            if (arr[i].compareTo(arr[maxIndex]) > 0){
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }



}
