package com.example;

import java.util.Comparator;

/**
 * Created by wuhai on 2016/10/17.
 */
public class Worker2Comparator implements Comparator<Worker2>{

    @Override
    public int compare(Worker2 o1, Worker2 o2) {
        int temp = o1.getAge() - o2.getAge();
        return temp == 0 ? o1.getName().compareTo(o2.getName()):temp;
    }
}
