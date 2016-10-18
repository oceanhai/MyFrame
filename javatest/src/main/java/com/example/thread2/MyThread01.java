package com.example.thread2;

public class MyThread01 implements Runnable {
	private int tickets = 10;
	private boolean flag = true;

	@Override
	public void run() {
		while (flag) {
			this.sale();
		}
	}

	public synchronized void sale() {// 该方法应该是同步的：一次性被一个线程执行完毕,this
		if (tickets > 0) {
			try {
				Thread.sleep((int) (Math.random() * (500 - 300 + 1) + 300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}// [300,500]
			System.out.println(Thread.currentThread().getName() + "\t"
					+ tickets);
			tickets--;
		} else {
			flag = false;
		}
	}

	public static void main(String[] args) {
		MyThread01 mt = new MyThread01();
		Thread t1 = new Thread(mt, "1窗口");
		Thread t2 = new Thread(mt, "2窗口");
		Thread t3 = new Thread(mt, "3窗口");
		t1.start();
		t2.start();
		t3.start();
		// 练习1：通过继承的方式卖票，用同步方法保证数据安全

	}

}
