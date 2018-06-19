package com.example.lctusb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wuhai on 2017/05/10 17:38.
 * 描述：
 */

public class LctSortTest {

    public static void main(String[] args){

        Pc_Category pc_category1 = new Pc_Category("a", 1L);
        Pc_Category pc_category2 = new Pc_Category("b", 0L);
        Pc_Category pc_category3 = new Pc_Category("c", 0L);
        List<Pc_Category> list = new ArrayList<>();
        list.add(pc_category1);
        list.add(pc_category2);
        list.add(pc_category3);

        System.out.println("前："+list.toString());
        Collections.sort(list);
        System.out.println("后:"+list.toString());
    }

    static class Pc_Category implements Comparable<Pc_Category>{
        private String name;
        private long count;

        public Pc_Category(String name, long count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        @Override
        public int compareTo(Pc_Category pc_category) {
            System.out.println("compareTo:"+Long.valueOf(this.count).compareTo(Long.valueOf(pc_category.count)));
            return Long.valueOf(this.count).compareTo(Long.valueOf(pc_category.count));
        }

        @Override
        public String toString() {
            return "Pc_Category{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
