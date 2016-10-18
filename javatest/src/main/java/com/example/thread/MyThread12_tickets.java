package com.example.thread;

public class MyThread12_tickets implements Runnable {
	private int ticket = 10;
	private Object obj = new Object();

	@Override
	public void run() {
		while (true) {// t2,t4,t1,t3
			synchronized (obj) {// 同步代码块：意味着一次被一个线程执行完
				// t2:obj上的锁：锁
				// t4：obj：锁
				if (ticket > 0) {
					// //t2,1,t4,1,t1,1,t3,1
					try {
						Thread.sleep((int) (Math.random() * (500 - 100 + 1) + 100));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}//
					System.out.println(Thread.currentThread().getName()
							+ "\t\t" + ticket);// 1,0,-1,-2
					ticket--;// 0,-1,-2,-3
				} else {
					break;
				}
			}// t2结束，打开锁,t4,打开锁
		}
	}

	public static void main(String[] args) {
		MyThread12_tickets mt = new MyThread12_tickets();
		Thread t1 = new Thread(mt, "售票口1");
		Thread t2 = new Thread(mt, "售票口2");
		Thread t3 = new Thread(mt, "售票口3");
		Thread t4 = new Thread(mt, "售票口4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		// [100,1000]
		// Math.random();// [0.0,1.0)
		// [0,900]
		// [0.0,901.0)
		// (int)( Math.random()*901)+100;
		// Random r1 = new Random();
		// r1.nextInt(901)+100;//[0,901)
		// 取钱：线程类：账户：3000，变量：2000，
		// 1.不同步时，是否存在数据安全问题(睡)
		// 2.加同步
	}
}
