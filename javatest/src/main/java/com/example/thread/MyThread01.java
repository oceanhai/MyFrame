package com.example.thread;

/**
 * 1.启动线程的方法一
 *
 *
 * step1：创建一个线程类。让它继承Thread。
 *
 * step2：重写run(),因为该方法是线程体。就表示子线程要执行的内容。
 *
 * step3：调用start(),表示启动线程。通知cpu可执行该线程。
 *
 * @author Administrator
 *
 */
public class MyThread01 extends Thread {
	MyThread01(String name) {
		super(name);
	}

	@Override
	public void run() {// 另外一条线程

		// 一个线程：1-100
		// 另外一个线程：101-200
		for (int i = 1001; i < 2000; i++) {
			System.out.println(Thread.currentThread().getName() + "......" + i);
		}

	}

	public static void main(String[] args) {// JVM启动主线程
		MyThread01 t1 = new MyThread01("A线程");
		// t1.run();// 方法的调用，不属于线程的范畴。
		// t1.setName("我的第一个子线程。。");
		t1.start();// 启动线程
		MyThread01 t2 = new MyThread01("B线程");
		// t2.setName("我的第二个子线程。。");
		t2.start();
		for (int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName()
					+ "main............." + i);
		}
		// 练习1：一个线程打印52个字母，另外一个线程52个数字。。
	}
}

