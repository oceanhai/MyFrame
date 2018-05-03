package com.example.javareflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wuhai on 2018/4/27.
 */

public class ReflectTest {


    /**
     * 如果我们要使用反射 就必须获得这个类的Class对象
     *
     * 三种方式获得这个Class对象
     * 1.Class类的静态方法    Class.forName();
     * 2.类或者接口的静态语法    String.class;
     * 3.类或者接口的实例化对像   user.getClass();
     */

    public static void main(String[] args){

        try {
            Class cs = Class.forName("com.example.javareflect.Reflect");
            Method method = cs.getDeclaredMethod("method01",new Class[]{String.class});
            method.invoke(new Reflect(),new Object[]{"haha"});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
