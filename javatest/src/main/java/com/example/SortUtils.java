package com.example;

/**
 * Created by Administrator on 2016/10/16.
 */
public class SortUtils {

    /**
     * 冒泡排序
     * @param arr
     */
    public static void maopaoSort(int[] arr){
        for(int x=0;x<arr.length-1;x++){
            for(int y=0;y<arr.length-1-x;y++ ){
                if(arr[y] > arr[y+1]){
                    int temp = arr[y];
                    arr[y] = arr[y+1];
                    arr[y+1] = temp;
                }
            }
        }

        print(arr);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void xuanzheSort(int[] arr){
        for (int x=0;x<arr.length-1;x++){
            for(int y=x+1;y<arr.length;y++){
                if(arr[x] > arr[y]){
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }

        print(arr);
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void charuSort(int[] arr){
        for(int x=1;x<arr.length;x++){
            for(int y=x;y>0;y--){
                if(arr[y]<arr[y-1]){
                    int temp = arr[y];
                    arr[y] = arr[y-1];
                    arr[y-1] = temp;
                }
            }
        }

        print(arr);
    }

    private static void print(int[] arr){
        for(int x=0;x<arr.length;x++){
            if(x != arr.length-1){
                System.out.print(arr[x]+",");
            }else{
                System.out.print(arr[x]);
            }
        }
    }
}
