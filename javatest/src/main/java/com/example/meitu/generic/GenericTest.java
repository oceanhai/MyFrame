package com.example.meitu.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuhai on 2018/04/19 11:48.
 * 描述：泛型
 * https://www.cnblogs.com/Vcanccc/p/5701351.html
 */

public class GenericTest {

    public static void main(String[] args){
        method06();
    }

    public static void method0N(){
        String[] names = { "John", "Q.", "public" };
        String middle = GenericTest.getMiddle(names);
        System.out.println("middle:"+middle);
    }

    public static <T> T getMiddle(T[] b){
        return b[b.length/2];
    }

    //--------------------------------------------------------------------------------------

    /**
     * 泛型类
     */

    public static void method06(){
        Pair2<Integer,String> pair = new Pair2<>(1,"abc");
        System.out.println("pair.a:"+pair.a);
        System.out.println("pair.b:"+pair.b);
    }

    /*声明两个类型变量也是可以的*/
    public static class Pair2<T,U> {
        private T a;
        private U b;
        public Pair2(T a, U b){
            this.a = a;
            this.b = b;
        }
    }
//Pair<String,Integer> p new Pair<>("abc",12);
//创建泛型类实例变量的时候，可以省略类型变量，编译器可以推测出来

    public static void method05(){
        Pair<Integer> pair = new Pair<>(1,2);
        System.out.println("pair.a:"+pair.a);
        System.out.println("pair.b:"+pair.b);
    }

    /*一个简单的泛型类的声明如下*/
    public static class Pair<T> {
        private T a;
        private T b;
        public Pair(T a, T b){
            this.a = a;
            this.b = b;
        }
    }

    //--------------------------------------------------------------------------------------

    /**
     *  https://www.cnblogs.com/Vcanccc/p/5701351.html
     *
     泛型三种：
     [1]ArrayList<T> al=new ArrayList<T>();指定集合元素只能是T类型
     [2]ArrayList<?> al=new ArrayList<?>();集合元素可以是任意类型，这种没有意义，一般是方法中，只是为了说明用法
     [3]ArrayList<? extends E> al=new ArrayList<? extends E>();
     泛型的限定：TODO 待确认
     ? extends E:接收E类型或者E的子类型。
     ？super E:接收E类型或者E的父类型。
     */

    /**
     * 2. 当一个类型变脸用来表达两个参数之间或者参数与返回值之间的关系时，
     * 即统一各类型变量在方法签名的两处被使用，或者类型变量在方法体代码中也被使用而不仅 仅在签名的时候使用，
     * 这是应该用自定义泛型<T>。泛型方可以调用一些时间类型的方法。比如集合的add方法。
     */
    public static void method04(){
        System.out.println(autoConvertType(new String("abc")));
    }

    public static <T> T autoConvertType(T obj){
        return(T)obj;
    }

    //--------------------------------------------------------------------------------------

    /**
     * 1.在整个类中只有一处使用了泛型,使用时注意加了泛型了参数不能调用与参数类型有关的方法比如“+”，
     * 比如打印出任意参数化类型集合中的所有内容，就适合用通配符泛型<?>
     */
    public static void method03(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        printCollection(list);
    }

    public static void printCollection(Collection<?> collection){
        for (Object object : collection){
            System.out.println(object);
        }
    }

    //--------------------------------------------------------------------------------------

    /**
     * method01 method02  总结
     * ？和T都表示不确定的类型  但如果是T的话 函数里面可以对T进行操作 比如while里面可以这样写
     * T自定义泛型和?通配符泛型
     */
    public static void method02(){
        List<String> list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        printColl2(list);
    }

    public static void printColl2(List<?> al){
        Iterator<?> it = al.iterator();
        while (it.hasNext()){
            //方法一  err 这里是不可以的
//            ? t = it.next();
//            System.out.println(t.toString());
            //方法二
            System.out.println(it.next().toString());
        }
    }

    public static void method01(){
        List<String> list = new ArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        printColl1(list);
    }

    /**
     * 题外注意一点：多态，如果参数是arraylist那么入参必须是ArrayList<String> list = new ArrayList<>();
     * @param al
     * @param <T>
     */
    public static <T> void printColl1(List<T> al){
        Iterator<T> it = al.iterator();
        while (it.hasNext()){
            //方法一
            T t = it.next();
            System.out.println(t.toString());
            //方法二
            System.out.println(it.next().toString());
        }
    }
}
