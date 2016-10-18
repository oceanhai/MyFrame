package com.example.thread;

public class MyThread09 extends Thread {
	private int i = 0;

	@Override
	public void run() {
		while (true) {
			boolean flag = Thread.interrupted();// 获取当前线程是否被中断的标志位，同时再清楚。
			System.out.println(Thread.currentThread().getName() + "\t" + i++
					+ "\t" + flag);
			if (flag) {
				// 如果flag为true，表示该线程的中断标志位被改为true
				System.out.println("--->" + Thread.interrupted());// false
				break;
			}
		}
	}

	public static void main(String[] args) {
		MyThread09 t1 = new MyThread09();
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();// 打断t1线程
		System.out.println("hello");
	}
}

