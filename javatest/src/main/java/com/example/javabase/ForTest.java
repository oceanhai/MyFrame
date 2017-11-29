package com.example.javabase;

import java.util.ArrayList;

/**
 * Created by wuhai on 2017/11/29 14:23.
 * 描述：
 */

public class ForTest {

    public static void main(String[] args){
        method02();
    }

    /**
     * 1.for循环遍历中加入一行add对象的代码，运行确实会抛出异常
     * 2.非对象不修改会更改集合值
     */
    private static void method01() {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for (int i : list) {
//            if(i == 4)list.add(1024);
            if(i == 4)i=1024;
            System.out.println(i);
        }

        for (int x=0;x<list.size();x++){
            System.out.print(list.get(x)+",");
        }
    }

    /**
     * 2.对象不修改会更改集合值
     */
    private static void method02() {
        ArrayList<Data> list = new ArrayList();
        list.add(new Data("name1",-1));
        list.add(new Data("name2",-1));
        list.add(new Data("name3",-1));
        list.add(new Data("name4",-1));
        list.add(new Data("name5",-1));
        list.add(new Data("name6",-1));
        System.out.println("before"+list.toString());
        for (Data data : list) {
            data.setVersion(0);
        }
        System.out.println("after:"+list.toString());
    }

    static class Data{
        private String name;
        private int version;

        public Data(String name, int version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", version=" + version +
                    '}';
        }
    }
}
