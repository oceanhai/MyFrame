package com.example.fanxing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhai on 2017/11/21 14:34.
 * 描述：泛型
 * https://www.cnblogs.com/lzq198754/p/5780426.html
 */

public class FanXingTest01 {

    public static void main(String[] args){
//        method();
//        method01();
//        method02();
//        method03();
//        method04();
//        method07();
        method08();
    }

    /**
     * 本例向list类型集合中加入了一个字符串类型的值和一个Integer类型的值。
     * （这样合法，因为此时list默认的类型为Object类型）。在之后的循环中，
     * 由于忘记了之前在list中也加入了Integer类型的值或其他原因，
     * 运行时会出现java.lang.ClassCastException异常。为了解决这个问题，泛型应运而生
     */
    public static void method(){
        List list = new ArrayList();
        list.add("CSDN_SEU_Cavin");
        list.add(100);
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); //取出Integer时，运行时出现异常
            System.out.println("name:" + name);
        }
    }

    /**
     * 上面程序的输出结果为true。所有反射的操作都是在运行时的，既然为true，就证明了编译之后，
     * 程序会采取去泛型化的措施，也就是说Java中的泛型，只在编译阶段有效。在编译过程中，
     * 正确检验泛型结果后，会将泛型的相关信息擦出，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
     * 也就是说，成功编译过后的class文件中是不包含任何泛型信息的。泛型信息不会进入到运行时阶段。
     */
    public static void method01(){
        List<String> list1 = new ArrayList<>();
        List list2 = new ArrayList();
        Class c1 = list1.getClass();
        Class c2 = list2.getClass();
        System.out.println(c1 == c2);
    }

    /**
     * 因为绕过了编译阶段也就绕过了泛型，输出结果为：[wuhai, 100]
     * 验证method01
     */
    public static void method02(){
        List<String> list = new ArrayList<>();
        list.add("wuhai");
        Class c = list.getClass();
        try {
            Method method = c.getMethod("add",Object.class);
            method.invoke(list,100);
            System.out.println(list);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static class FX<T>{
        private T ob;//定义泛型成员变量

        public FX(T ob) {
            this.ob = ob;
        }

        public T getOb() {
            return ob;
        }

        public void setOb(T ob) {
            this.ob = ob;
        }

        public void showTyep() {
            System.out.println("T的实际类型是: " + ob.getClass().getName());
        }
    }

    /**
     * 使用泛型
     */
    public static void method03(){
        FX<Integer> intOb = new FX<>(100);
        intOb.showTyep();
        System.out.println("value = "+intOb.getOb());
        System.out.println("---------------------------------");

        FX<String> strOb = new FX<String>("CSDN_SEU_Calvin");
        strOb.showTyep();
        System.out.println("value= " + strOb.getOb());
    }

    public static class FXno{
        private Object ob;//定义泛型成员变量

        public FXno(Object ob) {
            this.ob = ob;
        }

        public Object getOb() {
            return ob;
        }

        public void setOb(Object ob) {
            this.ob = ob;
        }

        public void showTyep() {
            System.out.println("T的实际类型是: " + ob.getClass().getName());
        }
    }

    /**
     * 未使用泛型
     */
    public static void method04(){
        FXno intOb = new FXno(100);
        intOb.showTyep();
        System.out.println("value = "+intOb.getOb());
        System.out.println("---------------------------------");

        FXno strOb = new FXno("CSDN_SEU_Calvin");
        strOb.showTyep();
        System.out.println("value= " + strOb.getOb());
    }

    /**
     * 上述第2行会出现编译错误，因为Integer虽然是Number的子类，但List<Integer>不是List<Number>的子类型。
     假定第2行代码没有问题，那么我们可以使用语句ex_num.add(newDouble())在一个List中装入了各种不同类型的子类，
     这显然是不可以的，因为我们在取出List中的对象时，就分不清楚到底该转型为Integer还是Double了。
     因此，我们需要一个在逻辑上可以用来同时表示为List<Integer>和List<Number>的父类的一个引用类型，
     类型通配符应运而生。在本例中表示为List<?>即可。下面这个例子也说明了这一点，注释已经写的很清楚了。
     */
    public static void method05(){
        List<Integer> ex_int= new ArrayList<Integer>();
//        List<Number> ex_num = ex_int; //非法的
    }

    /**
     * 通配符 使用
     */
    public static void method06(){
        FX<Number> ex_num = new FX<Number>(100);
        FX<Integer> ex_int = new FX<Integer>(200);
        getData(ex_num);
//        getData(ex_int);//编译错误
    }

    public static void getData(FX<Number> temp) { //此行若把Number换为“？”编译通过
        //do something...
    }

    /**
     * 上边界的例子
     * FX<? extends Number>
     */
    public static void method07(){
        FX<Number> ex_num = new FX<Number>(100);
        FX<Integer> ex_int = new FX<Integer>(200);
        getUpperNumberData(ex_num);
        getUpperNumberData(ex_int);
    }
    public static void getUpperNumberData(FX<? extends Number> temp){
        System.out.println("class type :" + temp.getClass());
    }

    /**
     * 下边界的例子
     * FX<? supers Number>
     */
    public static void method08(){
        FX<Number> ex_num = new FX<Number>(100);
        FX<Integer> ex_int = new FX<Integer>(200);
        getDownNumberData(ex_num);
//        getDownNumberData(ex_int);//编译不通过
    }
    public static void getDownNumberData(FX<? super Number> temp){
        System.out.println("class type :" + temp.getClass());
    }
}
