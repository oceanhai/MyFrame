package com.example.javabase.duotai;


/**
 * 作者 wuhai
 *
 * 创建日期 2018/5/14 18:07
 *
 * 描述：接口实现多态
 */
public class Test {

    public static void main(String[] args){
        Singer s1 = new Student("s1");
        s1.sing();
        s1.sleep();

        /**
         * 向上转型OK
         * 但这样的使用场景是啥
         */
        Singer t1 = new Teacher("t1"); //相当于继承中的父类引用指向子类对象
        t1.sing();
        t1.sleep();
        Painter p1 = (Painter) t1; //相当于继承中的父类引用指向子类对象
        p1.paint();
        p1.eat();
    }
}
