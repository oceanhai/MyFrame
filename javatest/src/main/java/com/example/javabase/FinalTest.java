package com.example.javabase;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Created by wuhai on 2019/1/2.
 */

public class FinalTest {

    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String a = "xiaoming2";
        final String b = "xiaoming";
        String d = "xiaoming";
        String c = b+2;
        String e = d+2;
        String f = "xiaoming2";
        String g = new String("xiaoming2");

        System.out.println(a == c);
        System.out.println(a == e);
        System.out.println(a.equals(e));
        System.out.println(a == f);
        System.out.println(a == g);
        System.out.println(a.hashCode());
        System.out.println(e.hashCode());
        System.out.println(""+addressOf(a));
        System.out.println(""+addressOf(e));
        System.out.println("a="+a+",c="+c+",e="+e);

    }

    public static long addressOf(Object o) throws Exception {

        Object[] array = new Object[] { o };

        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        int addressSize = unsafe.addressSize();
        long objectAddress;
        switch (addressSize) {
            case 4:
                objectAddress = unsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = unsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }
        return (objectAddress);
    }
}
