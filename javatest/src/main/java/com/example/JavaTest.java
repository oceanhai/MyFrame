package com.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/10/15.
 */
public class JavaTest {

    private int num = 0;

    public static void main(String agr[]){
        method14();
    }


    public static void method14(){
//        AlgorithmUtils.nineNineMultiplication();
//        System.out.println(AlgorithmUtils.factorial(4));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{},null));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'a','b'},new char[]{'a'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'a','b'},new char[]{'a','b'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'a','b'},new char[]{'a','c'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'a','b'},new char[]{'a','c','b'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'a','b'},new char[]{'a','b','c','d'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'b','c'},new char[]{'a','b','c','d'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'c','d'},new char[]{'a','b','c','d'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'d','e'},new char[]{'a','b','c','d'}));
//        System.out.println(AlgorithmUtils.judgeArrayContain(new char[]{'d','e'},new char[]{'a','b','c','e'}));
        method12();
    }

    public static void method13(){
        //测试1
        int[] data = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        print(data);
        AlgorithmUtils.shuffleSort(data);
        System.out.println("排序后的数组：");
        print(data);

        //测试2
        int[] data1 = new int[] {0};
        print(data1);
        AlgorithmUtils.shuffleSort(data1);
        System.out.println("排序后的数组：");
        print(data1);

        //测试3
        int[] data2 = new int[54];
        for(int x=0;x<54;x++){
            data2[x] = x;
        }
        print(data2);
        AlgorithmUtils.shuffleSort(data2);
        System.out.println("排序后的数组：");
        print(data2);

        //测试4
        int[] data3 = new int[]{} ;
        print(data3);
        AlgorithmUtils.shuffleSort(data3);
        System.out.println("排序后的数组：");
        print(data3);

        //测试4
        int[] data4 = null ;
        print(data4);
        AlgorithmUtils.shuffleSort(data4);
        System.out.println("排序后的数组：");
        print(data4);
    }

    private static void print(int[] data) {
        if(data == null || data.length == 0){
            return;
        }

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }

    public static void method12(){
        AlgorithmUtils.printOut(263);
    }

    /**
     * map
     */
    public static void method11(){
        Map<String,String> map = new HashMap<>();
        map.put("01","zhangsan");
        map.put("02","lisi");
        map.put("03", "wangwu");

        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            System.out.println("key="+key+",value="+value);
        }

        Set<Map.Entry<String,String>> set1 = map.entrySet();
        Iterator<Map.Entry<String,String>> iterator1 = set1.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String,String> entry = iterator1.next();
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
    }

    /**
     * set
     */
    public static void method10(){
//        Set<Worker> set = new TreeSet<Worker>();
//        Worker worker1 = new Worker("zhangsan", 20, 1);
//        Worker worker4 = new Worker("ahai", 20, 1);
//        Worker worker2 = new Worker("lisi", 19, 2);
//        Worker worker3 = new Worker("wangwu", 18, 3);
//        set.add(worker1);
//        set.add(worker2);
//        set.add(worker3);
//        set.add(worker4);
//
//        Iterator<Worker> iterator = set.iterator();
//        while (iterator.hasNext()){
//            Worker worker = iterator.next();
//            System.out.println(worker);
//        }

        TreeSet<Worker2> treeSet = new TreeSet<Worker2>(new Worker2Comparator());
        Worker2 worker2a = new Worker2("zhangsan", 20, 1);
        Worker2 worker2b = new Worker2("ahai", 20, 1);
        Worker2 worker2c = new Worker2("lisi", 19, 2);
        Worker2 worker2d = new Worker2("wangwu", 18, 3);
        treeSet.add(worker2a);
        treeSet.add(worker2b);
        treeSet.add(worker2c);
        treeSet.add(worker2d);
        Iterator<Worker2> iterator2 = treeSet.iterator();
        while (iterator2.hasNext()){
            Worker2 worker = iterator2.next();
            System.out.println(worker);
        }

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
