package com.example;

/**
 * Created by wuhai on 2016/10/11.
 */
class Code{
    {
        System.out.println("Code的构造块");
    }

    static{
        System.out.println("Code的静态代码块");
    }

    public Code(){
        System.out.println("Code的构造方法");
    }
}
