package com.example;

/**
 * Created by wuhai on 2016/10/14.
 */
/*
当窗口消毁时，Activity的静态变量是存在的，因为静态变量是属全局变量、
静态变量是整个应用程序的公共变量（即使你这个地方写的是私有），所以Activity消毁时，
静态变量是不会清除的。但当什么时候才会清除呢，上面说过静态变量是整个应用程序的，
所以只有当各个应用程序的进程消毁时，静态变量才会毁，
所以当LZ调用android.os.Process.killProcess(android.os.Process.myPid());就正常了。
java这里测试不出效果来
 */
public class StaticTest {

    public static int anInt = 3;

    public static void main(String agr[]){
        System.out.println("anInt-1:" + anInt);
        anInt ++;
        System.out.println("anInt-2:" + anInt);
    }

}
