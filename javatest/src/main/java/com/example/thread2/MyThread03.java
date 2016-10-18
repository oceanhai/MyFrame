package com.example.thread2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//饿汉式
class Single1 {
	// step1:私有化构造函数
	private Single1() {
	}

	// step2:本类中创建一个当前的对象
	private static final Single1 s = new Single1();

	// step3：返回s对象
	public static Single1 getInstance() {
		return s;
	}
}

// 懒汉式：延迟加载对象
class Single2 {
	// step1:私有化构造函数
	private Single2() {
	}

	// stpe2:声明一个本类的对象
	private static Single2 s = null;// 只做声明

	// step3:返回当前对象
	public static Single2 getInsSingle() {// 一种解决办法：同步方法：synchronized
		synchronized (Single2.class) {
			if (s == null) {// t1,t2,t3,t4,t5,t6
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s = new Single2();// new s2 new s2
			}
			return s;// s2 s2
		}

	}
}

public class MyThread03 implements Runnable {
	private Set<Single2> set = Collections
			.synchronizedSet(new HashSet<Single2>());// 让set集合也同步

	@Override
	public void run() {
		Single2 s = Single2.getInsSingle();
		boolean flag = set.add(s);
		System.out.println(Thread.currentThread().getName() + "\t" + flag
				+ "\t" + s);
	}

	public static void main(String[] args) {
		MyThread03 mt = new MyThread03();
		Thread t1 = new Thread(mt, "1");
		Thread t2 = new Thread(mt, "2");
		Thread t3 = new Thread(mt, "3");
		Thread t4 = new Thread(mt, "4");
		Thread t5 = new Thread(mt, "5");
		Thread t6 = new Thread(mt, "6");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}

}
