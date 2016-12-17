package com.example.designpattern.observer.observer1;

import java.util.Observable;
import java.util.Observer;

/**
 * JAVA提供的对观察者模式的支持 测试
 */
public class Test {

    public static void main(String[] args){
        //创建被观察者对象
        Watched watched = new Watched();
        //创建观察者对象，并将被观察者对象登记
        Observer watcher = new Watcher(watched);
        Observer watcher1 = new Watcher1(watched);
        //给被观察者状态赋值
        watched.setData("start");
        watched.setData("run");
        watched.setData("stop");
    }

    /**
     * 第二个观察者
     */
    static class Watcher1 implements Observer{

        public Watcher1(Observable o) {
            o.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("Watcher1:"+((Watched)o).getData());
        }
    }

}
