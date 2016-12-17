package com.example.designpattern.observer.observer2;

/**
 * 具体的观察者
 */
public class ConcreteWatcher implements Watcher{

    private String name;

    public ConcreteWatcher(String name) {
        this.name = name;
    }

    @Override
    public void update(String str) {
        System.out.println(this.name +":"+ str);
    }
}
