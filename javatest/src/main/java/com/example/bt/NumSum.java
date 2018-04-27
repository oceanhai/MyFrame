package com.example.bt;

/**
 * Created by wuhai on 2018/04/27 9:38.
 * 描述：整数各位数平方之和 =1  OK
 *      整数各位数平方之和 !=1   继续递归
 */

public class NumSum {

    public static void main(String[] args){
//        int num = 86;
        int num = -12;
        try {
            sum(num);
        }catch (StackOverflowError e){
            System.out.println("err");
        }catch (Exception e){
            if(e.getMessage().equals("1")){
                System.out.println("yes");
            }else{
                System.out.println("err");
            }
        }
    }

    public static class resultException extends RuntimeException{

        public resultException(String msg){
            super(msg);
        }

    }

    public static void sum(int num){
        System.out.println("num="+num);
        if(num <= 0){
            throw new resultException("-1");
        }
        if(num == 1){
            throw new resultException("1");
        }
        int sum = 0;
        while (num>0){
            int x = num %10;
            num = num/10;
            sum += x*x;
        }
        sum(sum);
    }
}
