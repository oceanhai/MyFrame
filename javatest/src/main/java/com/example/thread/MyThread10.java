package com.example.thread;

public class MyThread10 extends Thread {
	private int i = 0;
	private boolean flag = true;// 控制线程中的循环

	@Override
	public void run() {
		while (flag) {
			System.out.println(Thread.currentThread().getName() + "\t" + i++);
		}
	}

	/**
	 * 该方法用于结束子线程
	 */
	public void stopThread() {
		flag = false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread10 t1 = new MyThread10();
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.stopThread();
	}

}
