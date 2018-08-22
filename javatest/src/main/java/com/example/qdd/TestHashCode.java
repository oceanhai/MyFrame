package com.example.qdd;

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
        method02();
    }

    /**
     *  比较两个list是否一致，考虑顺序
     *  hashcode比较即可
     */
    public static void method02(){
        List<BannerModel> list1 = new ArrayList<>();
        list1.add(new BannerModel("test2","http://47.96.75.89/ftp_user/banner/1526435374047.jpg",
                "https://www.baidu.com/"));
        list1.add(new BannerModel("test1","http://47.96.75.89/ftp_user/banner/1526131328820.jpg",
                "https://www.baidu.com/"));
        list1.add(new BannerModel("tt","http://47.96.75.89/ftp_user/banner/1526131514169.png",
                "https://www.baidu.com/"));
        List<BannerModel> list2 = new ArrayList<>();
        list2.add(new BannerModel("test2","http://47.96.75.89/ftp_user/banner/1526435374047.jpg",
                "https://www.baidu.com/"));
        list2.add(new BannerModel("test1","http://47.96.75.89/ftp_user/banner/1526131328820.jpg",
                "https://www.baidu.com/"));
        list2.add(new BannerModel("tt","http://47.96.75.89/ftp_user/banner/1526131514169.png",
                "https://www.baidu.com/"));
        System.out.println("list1和list2一致：" + (list1.hashCode() == list2.hashCode()));
        System.out.println("--------------------------");
    }

//    /**
//     *  比较两个list是否一致，不考虑顺序
//     *  重写hashcode方法
//     */
//    public static void method03(){
//        List<People> list1 = new DisorderArrayList<>();
//        list1.add(new People("lufei",18,"100"));
//        list1.add(new People("suolong",19,"101"));
//        list1.add(new People("namei",20,"102"));
//        List<People> list2 = new DisorderArrayList<>();
//        list2.add(new People("lufei",18,"100"));
//        list2.add(new People("suolong",19,"101"));
//        list2.add(new People("namei",20,"102"));
//        List<People> list3 = new DisorderArrayList<>();
//        list3.add(new People("lufei",18,"100"));
//        list3.add(new People("suolong",19,"101"));
//        list3.add(new People("namei",20,"103"));
//        List<People> list4 = new DisorderArrayList<>();
//        list4.add(new People("suolong",19,"101"));
//        list4.add(new People("lufei",18,"100"));
//        list4.add(new People("namei",20,"102"));
//        System.out.println("list1和list2一致：" + (list1.hashCode() == list2.hashCode()));
//        System.out.println("list1和list3一致：" + (list1.hashCode() == list3.hashCode()));
//        System.out.println("list1和list4一致：" + (list1.hashCode() == list4.hashCode()));
//        System.out.println("hashcode1:" + new People("suolong",19,"101").hashCode());
//        System.out.println("hashcode2:" + new People("suolong", 19, "101").hashCode());
//        System.out.println("--------------------------");
//    }


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
