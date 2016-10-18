package com.example.thread;

class LeftHand extends Thread {
	public LeftHand(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 4; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + "A");
		}
	}
}

class RightHand extends Thread {
	RightHand(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 4; i++) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\t" + Thread.currentThread().getName() + "\t\t"
					+ "B");
		}
	}
}

public class MyThread06 {
	public static void main(String[] args) {
		// 睡500左手写A，300右手写B，个写4个
		new LeftHand("左手").start();
		new RightHand("右手").start();
	}
}
