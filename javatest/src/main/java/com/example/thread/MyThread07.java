package com.example.thread;

public class MyThread07 implements Runnable {
	private int i = 0;
	private int time = 0;

	public MyThread07(int time) {
		// TODO Auto-generated constructor stub
		this.time = time;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (i < 10) {
			System.out.println(Thread.currentThread().getName() + "\t" + i++);
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// 3个子线程，分别打印数字，分别睡100ms，200ms，300ms
		MyThread07 mt1 = new MyThread07(1000);
		Thread t1 = new Thread(mt1, "线程1");// 100
		MyThread07 mt2 = new MyThread07(2000);
		Thread t2 = new Thread(mt2, "线程2");// 100
		MyThread07 mt3 = new MyThread07(3000);
		Thread t3 = new Thread(mt3, "线程3");// 100
		t1.start();
		t2.start();
		t3.start();
	}
}
