package com.example.mianshi;

import java.util.ArrayList;
import java.util.List;

/**
 *  比较两个list是否一致，考虑顺序
 *  hashcode比较即可
 *  比较两个list是否一致，不考虑顺序
 *  重写hashcode方法
 */
public class TestHashCode {

    public static void main(String[] args){
        method01();
        method02();
        method03();
    }

    public static void method01(){
        List<String> list1 = new ArrayList<>();
        list1.add("lufei");
        list1.add("suolong");
        list1.add("namei");
        List<String> list2 = new ArrayList<>();
        list2.add("lufei");
        list2.add("suolong");
        list2.add("namei");
        List<String> list3 = new ArrayList<>();
        list3.add("lufei");
        list3.add("suolong");
        list3.add("namei1");
        List<String> list4 = new ArrayList<>();
        list4.add("suolong");
        list4.add("lufei");
        list4.add("namei");
        System.out.println("list1和list2一致：" + (list1.hashCode() == list2.hashCode()));
        System.out.println("list1和list3一致：" + (list1.hashCode() == list3.hashCode()));
        System.out.println("list1和list4一致：" + (list1.hashCode() == list4.hashCode()));
        System.out.println("--------------------------");
    }

    /**
     *  比较两个list是否一致，考虑顺序
     *  hashcode比较即可
     */
    public static void method02(){
        List<People> list1 = new ArrayList<>();
        list1.add(new People("lufei",18,"100"));
        list1.add(new People("suolong",19,"101"));
        list1.add(new People("namei",20,"102"));
        List<People> list2 = new ArrayList<>();
        list2.add(new People("lufei",18,"100"));
        list2.add(new People("suolong",19,"101"));
        list2.add(new People("namei",20,"102"));
        List<People> list3 = new ArrayList<>();
        list3.add(new People("lufei",18,"100"));
        list3.add(new People("suolong",19,"101"));
        list3.add(new People("namei",20,"103"));
        List<People> list4 = new ArrayList<>();
        list4.add(new People("suolong",19,"101"));
        list4.add(new People("lufei",18,"100"));
        list4.add(new People("namei",20,"102"));
        System.out.println("list1和list2一致：" + (list1.hashCode() == list2.hashCode()));
        System.out.println("list1和list3一致：" + (list1.hashCode() == list3.hashCode()));
        System.out.println("list1和list4一致：" + (list1.hashCode() == list4.hashCode()));
        System.out.println("hashcode1:" + new People("suolong",19,"101").hashCode());
        System.out.println("hashcode2:" + new People("suolong", 19, "101").hashCode());
        System.out.println("--------------------------");
    }

    /**
     *  比较两个list是否一致，不考虑顺序
     *  重写hashcode方法
     */
    public static void method03(){
        List<People> list1 = new DisorderArrayList<>();
        list1.add(new People("lufei",18,"100"));
        list1.add(new People("suolong",19,"101"));
        list1.add(new People("namei",20,"102"));
        List<People> list2 = new DisorderArrayList<>();
        list2.add(new People("lufei",18,"100"));
        list2.add(new People("suolong",19,"101"));
        list2.add(new People("namei",20,"102"));
        List<People> list3 = new DisorderArrayList<>();
        list3.add(new People("lufei",18,"100"));
        list3.add(new People("suolong",19,"101"));
        list3.add(new People("namei",20,"103"));
        List<People> list4 = new DisorderArrayList<>();
        list4.add(new People("suolong",19,"101"));
        list4.add(new People("lufei",18,"100"));
        list4.add(new People("namei",20,"102"));
        System.out.println("list1和list2一致：" + (list1.hashCode() == list2.hashCode()));
        System.out.println("list1和list3一致：" + (list1.hashCode() == list3.hashCode()));
        System.out.println("list1和list4一致：" + (list1.hashCode() == list4.hashCode()));
        System.out.println("hashcode1:" + new People("suolong",19,"101").hashCode());
        System.out.println("hashcode2:" + new People("suolong", 19, "101").hashCode());
        System.out.println("--------------------------");
    }

    static class People{
        String name;
        int age;
        String phone;

        public People(String name, int age, String phone) {
            this.name = name;
            this.age = age;
            this.phone = phone;
        }

        /**
         * 需要重写hashCode(),不然method02里其实每个对象的hashcode不一样，就会造成list的hashcode不一样
         */
        @Override
        public int hashCode() {
            return name.hashCode()*2+age*3+phone.hashCode()*4;
        }
    }

    static class DisorderArrayList<E> extends ArrayList<E>{
        @Override
        public int hashCode() {
            int hashCode = 1;
            for (E e : this)
                hashCode += e.hashCode();
            return hashCode;
        }
    }
}
