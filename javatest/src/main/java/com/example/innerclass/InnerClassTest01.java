package com.example.innerclass;

/**
 * Created by wuhai on 2017/10/12 15:06.
 * 描述：
 */

public class InnerClassTest01 {

    public static void main(String[] args) {
        new B().bMethod();
        System.out.println("main y="+y);
    }

    int x;
    static int y;

    public class A{
        int x;
        public void aMethod(){
            int x;
            x=3;            // x为aMethod方法的局部变量x
            this.x=4;       // x为内部类A的成员变量x  this是对A类对象的引用
            InnerClassTest01.this.x=5;  // x为外部类Test的成员变量x  Test.this是对外部类的Test类对象的引用
        }
    }

    public static class B{  //静态内部类
        int y;
        public void bMethod(){
            int y;
            y=3;            // y为bMethod方法的局部变量y
            this.y=4;       // y为内部类B的成员变量y  this是对B类对象的引用
            InnerClassTest01.y=5;  // y为外部类Test的静态成员变量y  因为静态（内部类/匿名内部类）只能调用外部类的静态属性，所以内部类没有持有外部引用
            System.out.println("B bMethod() y="+y);
            System.out.println("B y="+this.y);
        }
    }
}
