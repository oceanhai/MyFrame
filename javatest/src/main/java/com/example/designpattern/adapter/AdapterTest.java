package com.example.designpattern.adapter;

/**
 * Created by wuhai on 2017/10/24 14:44.
 * 描述：
 *
 * 将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 */

public class AdapterTest {


    public static void main(String[] args) {
        method03();
    }

    /**
     * 类的适配器模式
     * 这样Targetable接口的实现类就具有了Source类的功能。
     */
    private static void method01() {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }

    /**
     * 对象的适配器模式
     * 基本思路和类的适配器模式相同，只是将Adapter类作修改，
     * 这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
     */
    private static void method02() {
        Targetable target = new Wrapper(new Source());
        target.method1();
        target.method2();
    }

    /**
     * 第三种适配器模式是接口的适配器模式，接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，
     * 当我们写该接口的实现类时，必须实现该接口的所有方法，这明显有时比较浪费，
     * 因为并不是所有的方法都是我们需要的，有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，
     * 借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，只和该抽象类取得联系，
     * 所以我们写一个类，继承该抽象类，重写我们需要的方法就行
     */
    private static void method03() {
        Sourceable sourceable1 = new SourceSub1();
        sourceable1.method1();
        sourceable1.method2();

        System.out.println();

        Sourceable sourceable2 = new SourceSub2();
        sourceable2.method1();
        sourceable2.method2();
    }
}
