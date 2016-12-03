package com.example.mianshi;

/**
 * Created by Administrator on 2016/12/2.
 */
public class Test01 {
    public static int num = 0;

    public static void main(String[] args){
        addNum(num);
    }

    public static void addNum(int num){
        for(int x=0;x<20;x++){
            num++;
        }
        System.out.println(num);// 20
//        System.out.println(this.num);//TODO static 不能引用this
    }
}
