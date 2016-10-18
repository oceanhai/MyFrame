package com.example.thread2;

public class MyThread07 implements Runnable {
	private static String bread = "bread";
	private static String milk = "milk";
	boolean flag = true;

	@Override
	public void run() {
		if (flag) {// 张三
			synchronized (bread) {// t1.
				System.out.println(Thread.currentThread().getName() + "\t已经锁定了"
						+ bread + "即将锁定mil");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (milk) {
					System.out.println("张三哈哈哈。。。及锁定了break,yeyou milk");
				}

			}
		} else {
			// 李四
			synchronized (milk) {// t2
				System.out.println(Thread.currentThread().getName() + "\t已经锁定了"
						+ milk + "即将锁定bread");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (bread) {
					System.out.println("李四哈哈哈。。。及锁定了break,yeyou milk");
				}

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread07 mt1 = new MyThread07();
		MyThread07 mt2 = new MyThread07();
		Thread t1 = new Thread(mt1, "张三");
		Thread t2 = new Thread(mt2, "李四");
		mt1.flag = true;
		mt2.flag = false;
		t1.start();
		t2.start();
	}

}
