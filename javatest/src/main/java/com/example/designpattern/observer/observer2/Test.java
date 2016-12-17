package com.example.designpattern.observer.observer2;

/**
 * 观察者 模式
 */
public class Test {

    public static void main(String[] args){
        ConcreteWatched watched = new ConcreteWatched();
        ConcreteWatcher watcher1 = new ConcreteWatcher("观察者1");
        ConcreteWatcher watcher2 = new ConcreteWatcher("观察者2");
        ConcreteWatcher watcher3 = new ConcreteWatcher("观察者3");
        ConcreteWatcher watcher4 = new ConcreteWatcher("观察者4");
        watched.addWatcher(watcher1);
        watched.addWatcher(watcher2);
        watched.addWatcher(watcher3);
        watched.addWatcher(watcher4);
        watched.notifyWatchers("开心");

        watched.removeWatcher(watcher4);

        watched.notifyWatchers("年关找工作真不容易啊，任性");

    }
}
