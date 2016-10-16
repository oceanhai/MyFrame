package com.example;

/**
 * Created by Administrator on 2016/10/16.
 */
public class SearchUtils {

    /**
     * 折半查询
     * @param key
     * @param arr
     */
    public static void zhebanSearch(int key, int[] arr){
        int max = arr.length-1;
        int min = 0;
        int mid = (max + min)/2;
        while (key != arr[mid]){
            if(key < arr[mid]){
                max = mid-1;
            }else{
                min = mid+1;
            }
            if(min > max){
                System.out.println("数据中检索不到"+key);
                return;
            }
            mid = (max + min)/2;
        }
        System.out.println("检索到："+key+"对应数组下标是"+mid);
    }
}
