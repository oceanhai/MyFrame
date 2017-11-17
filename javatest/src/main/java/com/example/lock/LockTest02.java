package com.example.lock;

/**
 * Created by wuhai on 2017/11/16 11:40.
 * 描述：
 */

public class LockTest02 {

    public static void main(String[] args){

        for(int y=0;y<10;y++){
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    StockLock.getInstance().lock();
                    System.out.println(Thread.currentThread().getName()+"得到了锁");
                    try {
                        for(int x=0;x<10;x++){
                            System.out.println(Thread.currentThread().getName()+":"+x);
                        }
                    }catch (Exception e){

                    }finally {
//                        StockLock.getInstance().unlock();
                        System.out.println(Thread.currentThread().getName()+"释放了锁");
                    }
                }
            }).start();
        }

//        StockLock.getInstance().lock();
        System.out.println(Thread.currentThread().getName()+"得到了锁");
        try {
            for(int x=0;x<10;x++){
                System.out.println(Thread.currentThread().getName()+":"+x);
            }
        }catch (Exception e){

        }finally {
//            StockLock.getInstance().unlock();
            System.out.println(Thread.currentThread().getName()+"释放了锁");
        }

    }

}
