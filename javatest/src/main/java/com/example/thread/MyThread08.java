package com.example.thread;

public class MyThread08 extends Thread {
	@Override
	public void run() {
		System.out.println("1进入子线程准备执行。。。");
		try {
			System.out.println("2.准备睡眠。。。。");
			Thread.sleep(2000);
			System.out.println("3.睡到自然醒。。。");
		} catch (InterruptedException e) {
			System.out.println("4.我被吵醒了。。。");
			// return;
		}
		System.out.println("子线程执行完毕，即将结束。。");
	}

	public static void main(String[] args) {
		MyThread08 t1 = new MyThread08();
		t1.start();
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.interrupt();// 打断线程
	}
}
