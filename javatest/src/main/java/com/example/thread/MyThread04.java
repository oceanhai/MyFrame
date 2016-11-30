package com.example.thread;

public class MyThread04 extends Thread {

	private static int i = 0;//非静态 每一个线程自己的；静态 共用的

	public void run() {
		while (i < 20) {
			i++;// i:5,6,7
			System.out.println(Thread.currentThread().getName() + "\t" + i);
		}
	}

	public static void main(String[] args) {
		MyThread04 t1 = new MyThread04();
		MyThread04 t2 = new MyThread04();
		MyThread04 t3 = new MyThread04();
		t1.start();
		t2.start();
		t3.start();
	}

}

