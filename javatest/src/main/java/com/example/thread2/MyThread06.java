package com.example.thread2;

public class MyThread06 implements Runnable {
	private int i = 0;

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + "\t" + i++);
		}
	}

	public static void main(String[] args) {
		MyThread06 mt = new MyThread06();
		Thread t1 = new Thread(mt, "线程");
		t1.setDaemon(true);// 将t1设置为守护线程
		t1.start();
		for (int i = 0; i < 100000; i++) {
			System.out.println("\t\t..main......" + i);
		}
	}

}
