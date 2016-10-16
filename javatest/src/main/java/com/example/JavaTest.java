package com.example;

import java.util.Random;

/**
 * Created by Administrator on 2016/10/15.
 */
public class JavaTest {

    private int num = 0;

    public static void main(String agr[]){
        method09();
    }

    /**
     * 随机数
     */
    public static void method09(){
        int x = (int) (Math.random()*10+1);//1-10随机数

        Random random = new Random();
        int y = random.nextInt(10);//0-9随机数

        System.out.println("x="+x+",y="+y);
    }

    /**
     * 排序和折半查询
     */
    public static void method08(){
        int[] arr = new int[]{9,1,45,3,22,5,0};
        SortUtils.maopaoSort(arr);
        System.out.println();
        int[] arr1 = new int[]{9,1,45,3,22,5,0};
        SortUtils.xuanzheSort(arr1);
        System.out.println();
        int[] arr2 = new int[]{9,1,45,3,22,5,0};
        SortUtils.charuSort(arr2);

        System.out.println();
        int[] arr3 = new int[]{1,5,9,13,22,65,99};
        SearchUtils.zhebanSearch(13, arr3);
    }

    private static void method06(){
        Student student = new Student("shen", 20);
        student.getAge();
        System.out.println(student);

        Student student1 = new Student();
        System.out.println(student1);

        student1.study();
        student1.gotoShchool();

//        System.out.println(num);
//        method07();

        UniversityStudent student2 = new UniversityStudent();
        student2.print();
        System.out.println(student2);
        student2.study();

        PrimarySchoolStudent student3 = new PrimarySchoolStudent();
        System.out.println(student3);
//        PrimarySchoolStudent student4 = new PrimarySchoolStudent("xiaoxuesheng", 11);

        //多态
        Student student4 = new UniversityStudent();
        student4.study();//非静态方法
        student4.gotoShchool();
        student4.sleep();
    }

    private void method07(){
        System.out.println("非静态方法");
        method05();
    }

    private static void method05() {
        int x =3;
//        if(x >0){
//            System.out.println(x);
//        }else{
//            continue;
//        }

        for(int y=0;y<5;y++){
            if(y>3){
                continue;
            }else{
                System.out.println(y);
            }
        }
    }

    private static void method01() {
        short s=2;
//        s = s+2;//TODO 损失精度
    }

    private static void method02() {
        short s=2;
        s = +2;
    }

    private static void method03() {
        byte b1 = 2;
        byte b2 = 7;
        byte b3 = 2+7;

        //TODO 因为byte的取值范围是-128~127
        byte b4 = 127;
//        byte b5 = 128;
        byte b6 = -128;
//        byte b7 = -129;
    }

    private static void method04() {
        byte b1 = 2;
        byte b2 = 7;
//        byte b3 = b1+b2;//TODO 损失精度
    }

}
