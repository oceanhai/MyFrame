package com.example;

/**
 * Created by wuhai on 2016/10/11.
 */
public class Outer {
    private static String name;
    private String name1;

    void method(){
        Inner ic = new Inner();//Causes generation of accessor class
    }
    public class Inner {
        private String name1;
        private Inner(){}

        public void method01(){
            this.name1 = Outer.this.name1;
        }
    }

    public static class Inner1{

        private String name;

        public void method01(){
            this.name = Outer.name;
        }
    }
}
