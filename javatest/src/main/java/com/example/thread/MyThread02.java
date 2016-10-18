package com.example.thread;

public class MyThread02 extends Thread {
	private static int m = 0;

	@Override
	public void run() {
		for (int i = 0; i < 52; i++) {
			System.out.println("我的另外一个线程。。。" + i);
			System.out.println(m++);
		}
	}

	public static void main(String[] args) {
		// 练习1：一个线程打印52个字母，另外一个线程52个数字。。
		// A-Z:65-90
		// a - z:97-122
		MyThread02 t1 = new MyThread02();
		MyThread02 t2 = new MyThread02();
		t1.start();
		t2.start();// ?每个线程只能启动一次
		for (int i = 'A'; i < 123; i++) {
			if (i >= 'A' && i <= 'Z' || i >= 'a' && i <= 'z') {
				System.out.println("\t\tmain....." + (char) i);
			}
			System.out.println(m++);
		}
	}
}
