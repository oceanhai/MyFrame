package com.example.thread;

public class MyThread05 implements Runnable {
	private int i = 0;// 属于mt的

	@Override
	public void run() {
		while (i < 20) {
			i++;

			System.out.println(Thread.currentThread().getName() + "\t" + i);
			try {
				Thread.sleep(1000);// 1s,当前线程进入休眠状态，睡1s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MyThread05 mt = new MyThread05();
		// MyThread05 mt1 = new MyThread05();
		// MyThread05 mt2 = new MyThread05();
		Thread t1 = new Thread(mt, "A线程");
		Thread t2 = new Thread(mt, "B线程");
		Thread t3 = new Thread(mt, "C线程");

		t1.start();
		t2.start();
		t3.start();
		for (int i = 0; i < 30; i++) {
			System.out.println("main...." + i);
			try {
				t1.sleep(500);// main线程睡眠。
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}