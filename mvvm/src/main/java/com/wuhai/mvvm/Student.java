package com.wuhai.mvvm;

/**
 * Created by wuhai on 2017/08/14 15:47.
 * 描述：
 */

public class Student {

    private String name;
    private String addr;

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
