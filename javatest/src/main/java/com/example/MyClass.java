package com.example;

public class MyClass {
    private static Foo[] mArray = new Foo[10000001];
//    Foo[] mArray ;


    public static void main(String arg[]){
        System.out.println(System.currentTimeMillis());
        System.out.println(100);
        System.out.println(100l);
        initData();

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        zero();
        endTime = System.currentTimeMillis();
        long useTime1 = endTime - startTime;
//        BigDecimal a = new BigDecimal(startTime);
//        BigDecimal b = new BigDecimal(endTime);
//        long useTime2 = a.subtract(b).longValue();
        System.out.println(useTime1);
//        System.out.println(useTime2);

        startTime = System.currentTimeMillis();
        one();
        endTime = System.currentTimeMillis();
        long useTime2 = endTime - startTime;
        System.out.println(useTime2);

        startTime = System.currentTimeMillis();
        two();
        endTime = System.currentTimeMillis();
        long useTime3 = endTime - startTime;
        System.out.println(String.valueOf(useTime3));

    }

    private static void initData() {
        for(int x=0; x<=10000000; x++){
            Foo foo = new Foo(x);
            mArray[x] = foo;
        }
    }

    public static void zero() {
        int sum = 0;

        for (int i = 0; i < mArray.length; ++i) {
            sum += mArray[i].mSplat;
        }
    }

    /**
     * 稍微快点
     */
    public static void one() {
        int sum = 0;
        Foo[] localArray = mArray;
        int len = localArray.length;

        for (int i = 0; i < len; ++i) {
            sum += localArray[i].mSplat;
        }
    }

    public static void two() {
        int sum = 0;
        for (Foo a : mArray) {
            sum += a.mSplat;
        }
    }

    static class Foo {
        int mSplat;
        public Foo(int splat){
            this.mSplat = splat;
        }
    }
}
