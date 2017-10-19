package com.example.designpattern.multitionpattern;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wuhai on 2017/10/19 15:13.
 * 描述：皇帝
 */

public class Emperor {
    private static int maxNumOfEmperor = 2; //最多只能有连个皇帝
    private static ArrayList emperorInfoList = new ArrayList(maxNumOfEmperor); //皇帝叫什么名字
    private static ArrayList emperorList = new ArrayList(maxNumOfEmperor); //装皇帝的列表；
    private static int countNumOfEmperor =0; //正在被人尊称的是那个皇帝

    //先把2个皇帝产生出来
    static{
        //把所有的皇帝都产生出来
        for(int i=0;i<maxNumOfEmperor;i++){
            emperorList.add(new Emperor("皇"+(i+1)+"帝"));
        }
    }

    //就这么多皇帝了，不允许再推举一个皇帝(new 一个皇帝）
    private Emperor(){
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }

    private Emperor(String info){
        emperorInfoList.add(info);
    }

    public static Emperor getInstance(){
        Random random = new Random();
        countNumOfEmperor = random.nextInt(maxNumOfEmperor); //随机拉出一个皇帝，只要是个精神领袖就成
        return (Emperor)emperorList.get(countNumOfEmperor);
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println(emperorInfoList.get(countNumOfEmperor));
    }

}
