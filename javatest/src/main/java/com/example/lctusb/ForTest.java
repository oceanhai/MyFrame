package com.example.lctusb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2017/08/22 18:15.
 * 描述：
 */

public class ForTest {

    public static void main(String[] args){
        method02();
    }

    public static void method02(){
        List<String> list = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            System.out.println("x="+x);
        }
    }

    public static void method01(){
        int numTemp = 2;
        int unpack_num = 10;
        int bigNeedUnpackNum = 0;
        int sellOutNum = 53;
        for(int z=1;;z++){
            numTemp += unpack_num;
            bigNeedUnpackNum=z;
            if(numTemp >= sellOutNum){
                break;
            }
        }
        System.out.println("bigNeedUnpackNum="+bigNeedUnpackNum);
    }
}
