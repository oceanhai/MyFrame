package com.example.lctusb;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2017/03/03 14:27.
 * 描述：
 */

public class ListTest {

    public static void main(String[] args){

//        method01();
        method02();

    }

    private static void method02() {
        List<String> mDatas = new ArrayList<>();
        String str = mDatas.get(0);
    }

    private static void method01() {
        List<List<String>> mDatas = new ArrayList<>();

        List<String> list = new ArrayList<>();
        for(int x=1; x<=40; x++){
            list.add("item"+x);
        }

        for(int x=0;x<4;x++){
            List<String> subList = null;
            if(x==3){
                subList = list.subList(x*12,list.size());
            }else{
                subList = list.subList(x*12,(x+1)*12);
            }
            mDatas.add(subList);
        }

        for(int y=0;y<mDatas.size();y++){
            List<String> list1 = mDatas.get(y);
            for(int z=0;z<list1.size();z++){
                System.out.print(list1.get(z)+",");
            }
            System.out.println();
        }
    }
}
