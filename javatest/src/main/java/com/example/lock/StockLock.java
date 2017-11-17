package com.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wuhai on 2017/11/16 11:38.
 * 描述：库存操作锁
 */
public class StockLock {
    private Lock lock;

    private static StockLock ourInstance = new StockLock();

    public static StockLock getInstance() {
        return ourInstance;
    }

    private StockLock() {
        lock = new ReentrantLock();
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }
}
