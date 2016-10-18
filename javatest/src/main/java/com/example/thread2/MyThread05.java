package com.example.thread2;

public class MyThread05 extends Thread {
	private int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		test1();
	}

	public void test1() {
		System.out.println("test1z尝试修改count的值：");
		count = 1000;// 1000
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test1修改了count的值：" + count);// 1000
	}

	public void test2() {
		System.out.println("test2z尝试修改count的值：");
		count = 3000;// 3000
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test2修改了count的值：" + count);// 3000
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread05 t1 = new MyThread05();
		t1.start();// 启动线程
		t1.test2();// 对象调用：main函数中执行
	}

}
