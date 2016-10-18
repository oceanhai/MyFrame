package com.example.thread;

/**
 * 线程的第二种启动方式：
 *
 * step1：实现Runnable接口
 *
 * step2：实现run()方法：写线程体
 *
 * step3：用实现类对象，创建Thread类的对象，再启动线程。
 *
 * @author Administrator
 *
 */
public class MyThread03 implements Runnable {
	private int i = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			i++;
			System.out.println(Thread.currentThread().getName() + "\t" + i);
		}
	}

	public static void main(String[] args) {
		MyThread03 mt = new MyThread03();
		Thread t1 = new Thread(mt);
		t1.start();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			System.out.println("\t\tmain...." + i);
		}
		System.out.println("helloworld");
		// 练习1：使用第二种方式启动线程
		// 练习2：比较2种启动方式的区别


		/**
		 * android 里常见启动线程
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();
	}

}