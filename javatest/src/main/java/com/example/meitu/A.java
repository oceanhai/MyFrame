package com.example.meitu;

import java.util.ArrayList;
import java.util.List;

/**
 * 多态测试1
 */
public class A {

    public static void main(String[] args){
        method03();
    }

    /**
     * ok
     * 向下转是有条件的
     */
    public static void method00(){
        GrandFather grandFather = new Father();
//        Father father = (Father) new GrandFather();//编译报错，不能转换
        Father father1 = (Father) grandFather;//编译成功,能向下转换
    }

    /**
     * OK
     * <? extends A>表示类型必须是A或者A的子类
     * <? super A>表示类型必须是A或者A的超类
     * 按上面描述下面add都不应该报错啊
     * 其实不然
     * List<? extends A>表示一个集合,这个集合存放的是A具体子类中的某一种,
     * 而非只要是A的子类就可以放入.
     * 同理，List<? super A>表示一个集合,这个集合存放的是A具体父类中的某一种,
     * 由于A是集成此父类，所以A可以上向转成此父类（并且A类的子类也是没问题的）
     * 上述论证可以在method011()证实
     */
    public static void method01(){
        List<? super A> list1 = new ArrayList<>();
        List<? extends A> list2 = new ArrayList<>();
        A a = new A();
        list1.add(a);//编译成功，向上转没问题
//        list2.add(a);//错误提示，向下转有问题（向下转是有条件的）
    }

    /**
     * <? extends A>表示类型必须是A或者A的子类
     * <? super A>表示类型必须是A或者A的超类
     * 其实这两个解释适用于method03() 入参泛型
     * 而不适用集合add中，个人感觉其实你并不确定Father的超类是谁，所以下面提示错误
     */
    public static void method011(){
        List<? super Father> list1 = new ArrayList<>();
        List<? extends Father> list2 = new ArrayList<>();
        GrandFather grandFather = new GrandFather();
        Father father = new Father();
        Child child = new Child();
//        list1.add(grandFather);//错误提示，Father的超类不能确定
        list1.add(father);//编译成功，向上转没问题
        list1.add(child);//编译成功，向上转没问题
//        list2.add(grandFather);//错误提示，向下转有问题（向下转是有条件的）
//        list2.add(father);//错误提示，向下转有问题（向下转是有条件的）
//        list2.add(child);//错误提示，向下转有问题（向下转是有条件的）
    }

    /**
     * ok
     */
    public static void method02(){
        //多态
        List<GrandFather> list1 = new ArrayList<>();
        List<Father> list2 = new ArrayList<>();
        List<Child> list3 = new ArrayList<>();
        GrandFather grandFather = new GrandFather();
        Father father = new Father();
        Child child = new Child();

        GrandFather father1 = new Father();
        GrandFather child1 = new Child();
        Father child2 = new Child();

        list1.add(grandFather);
        list1.add(father);
        list1.add(child);

//        list2.add((Father) grandFather);//编译报错，不能转换
        list2.add((Father) father1);//编译成功,能向下转换
        list2.add(father);
        list2.add(child);

//        list3.add((Child) grandFather);//编译报错，不能转换
        list3.add((Child) child1);//编译成功,能向下转换
//        list3.add((Child) father);//编译报错，不能转换
        list3.add((Child) child2);//编译成功,能向下转换
        list3.add(child);
    }

    /**
     * <? extends A>表示类型必须是A或者A的子类
     * <? super A>表示类型必须是A或者A的超类
     */
    public static void method03(){
        tde1(new ArrayList<Father>());
        tde1(new ArrayList<GrandFather>());
//        tde1(new ArrayList<Child>());//错误提示，Child不是Father超类

        tde2(new ArrayList<Father>());
//        tde2(new ArrayList<GrandFather>());//错误提示，GrandFather不是Father子类
        tde2(new ArrayList<Child>());
    }

    public static void tde1(List<? super Father> list){

    }

    public static void tde2(List<? extends Father> list){

    }
}
