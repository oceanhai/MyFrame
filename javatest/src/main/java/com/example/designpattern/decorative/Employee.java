package com.example.designpattern.decorative;


/**
 * 装饰者类
 */
public class Employee implements Worker{

    private Worker worker;

    public Employee(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void work() {
        System.out.println("你们都去干活去1");
        worker.work();
    }
}
