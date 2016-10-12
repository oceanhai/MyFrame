package com.example;

/**
 * Created by wuhai on 2016/10/11.
 */
//构造块：直接在类中定义且没有加static关键字的代码块称为{}构造代码块。构造代码块在创建对象时被调用，每次创建对象都会被调用，并且构造代码块的执行次序优先于类构造函数。

public class CodeBlock02{
    {
        System.out.println("第一代码块");
    }

    public CodeBlock02(){
        System.out.println("构造方法");
    }

    {
        System.out.println("第二构造块");
    }
    public static void main(String[] args){
        new CodeBlock02();
        new CodeBlock02();
        new CodeBlock02();

    }
}

/*
*
执行结果：
第一代码块
第二构造块
构造方法
第一代码块
第二构造块
构造方法
第一代码块
第二构造块
构造方法
*/
