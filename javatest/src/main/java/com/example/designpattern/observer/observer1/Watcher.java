package com.example.designpattern.observer.observer1;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者类 第一个观察者
 */
public class Watcher implements Observer{

    public Watcher(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("状态发生改变：" + ((Watched)o).getData());
    }

}
