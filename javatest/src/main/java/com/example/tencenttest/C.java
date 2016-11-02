package com.example.tencenttest;

/**
 * 实现类C
 */
public class C{

//    public int method01(){
//        return new D().method01();
//    }

    static class D implements A{

        @Override
        public int method01() {
            System.out.println("A接口method01()");
            return 0;
        }

        @Override
        public int method02() {
            System.out.println("A接口method02()");
            return 0;
        }
    }

    static class E implements B{

        @Override
        public int method01() {
            System.out.println("B接口method01()");
            return 0;
        }

        @Override
        public String method02() {
            System.out.println("B接口method02()");
            return null;
        }
    }
}
