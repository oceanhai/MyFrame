package com.myframe.www.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.http.GET;

/**
 * Created by wuhai on 2018/5/17.
 */

public class Test {
    public interface ITest{
        @GET("/heiheihei")
        public void add(int a, int b);
    }
    public static void main(String[] args){
        ITest iTest = (ITest) Proxy.newProxyInstance(ITest.class.getClassLoader(),
                new Class<?>[]{ITest.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Integer a = (Integer) args[0];
                        Integer b = (Integer) args[1];
                        System.out.println("方法名：" + method.getName());
                        System.out.println("参数：" + a + " , " + b);

                        GET get = method.getAnnotation(GET.class);
                        System.out.println("注解：" + get.value());
                        return null;
                    }
                });
        iTest.add(3,5);
    }
}
