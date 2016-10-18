package com.example.thread2;

class Test {
	private static int count = 0;// 计数器

	public void add(String name) {
		count++;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "\t，你是第" + count + "\t访问的线程");
	}
}

public class MyThread04 implements Runnable {
	private Test test = new Test();

	@Override
	public void run() {
		test.add(Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		MyThread04 mt = new MyThread04();
		Thread t1 = new Thread(mt, "线程1");
		Thread t2 = new Thread(mt, "线程2");
		Thread t3 = new Thread(mt, "线程3");
		t1.start();
		t2.start();
		t3.start();
	}

}
