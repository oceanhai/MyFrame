package com.example.thread2;

//生产者消费者操作的产品对象：馒头
class ManTou {
	private int i;// 每一个馒头的编号

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public ManTou(int i) {
		super();
		this.i = i;
	}

	@Override
	public String toString() {
		return "ManTou [i=" + i + "]";
	}
}

/**
 * 容器类：用于存储馒头或者获取馒头
 *
 * @author Administrator
 *
 */
class Basket {// 容器类
	private ManTou[] arr = new ManTou[8];// 装馒头
	// mantou,mantou,null,null...
	private int index = 0;// 操作数组的下标[0,7]

	public synchronized void push(ManTou manTou) {// 生产者存入馒头
		if (index == arr.length) {// 表示容器满了
			try {
				System.out.println("容器已空。。" + Thread.currentThread().getName()
						+ "..等待");
				wait();// 线程暂时不执行了，
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		arr[index] = manTou;//
		index++;// 存入馒头后下标向后移动
		notify();//
	}

	public synchronized ManTou pop() {// 消费者获取馒头
		if (index == 0) {
			try {
				System.out.println("容器已空。。" + Thread.currentThread().getName()
						+ "..等待");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		index--;// 先将操作数组的下标移动一个
		notify();
		return arr[index];
	}
}

class Pro1 implements Runnable {
	private Basket basket;// 生产者需要操作的容器，用于存入馒头
	private int i = 1;// 馒头的编号

	Pro1(Basket basket) {
		this.basket = basket;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (basket) {
				ManTou manTou = new ManTou(i++);// 大厨生产的馒头对象
				basket.push(manTou);// 将馒头存入容器
				// 生产者等待过程，
				System.out
						.println("\t\t....." + Thread.currentThread().getName()
								+ "生产者生产馒头" + manTou);
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}

class Con1 implements Runnable {
	private Basket basket;// 消费者需要操作的容易，用于取馒头

	Con1(Basket basket) {
		this.basket = basket;
	}

	@Override
	public void run() {
		// 调用pop
		while (true) {
			synchronized (basket) {
				ManTou manTou = basket.pop();// 消费者获取馒头
				System.out.println(Thread.currentThread().getName() + "消费者吃掉馒头"
						+ manTou);
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}

public class TestProducerAndConsumer01 {

	public static void main(String[] args) {
		Basket basket = new Basket();
		Pro1 p1 = new Pro1(basket);
		Con1 c1 = new Con1(basket);
		Thread t1 = new Thread(p1, "大厨");
		Thread t2 = new Thread(c1, "吃货");
		t1.start();
		t2.start();

	}

}