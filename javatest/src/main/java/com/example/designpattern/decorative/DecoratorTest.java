package com.example.designpattern.decorative;

/**
 * 演示装饰设计模式
 * 单例设计模式  简单工厂模式
 * 特点:
 * 1.装饰设计模式也称为包装设计模式
 * 2.装饰设计模式可以动态的拓展对象的功能 也可以理解为是继承的替代
 * 3.抽象组件角色
 *   装饰者   employee 主要负责装饰worker类中的对象
 *   被装饰者 具体的worker的子类
 *
 * 4.装饰者和被装饰者都需要继承或者实现抽象角色
 * 5.装饰者类中必须持有被装饰者对象的父类的引用
 * 6.客户端接收装饰者对象 将内容传递给真实的对象
 * 7.装饰者模式的优点:在不改变对象的结构情况下拓展对象的功能
 */
public class DecoratorTest {

    public static void main(String arg[]){
        Worker plumber = new Plumber();
        Employee employee1 = new Employee(plumber);
        employee1.work();

        Worker carpenter =  new Carpenter();
        Employee employee2 = new Employee(carpenter);
        employee2.work();

    }
}
