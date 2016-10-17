package com.example;

/**
 * Created by wuhai on 2016/10/17.
 */
public class Worker2 extends Person {

    private int workYear;

    public Worker2(String name, int age, int workYear){
        super(name, age);
        this.workYear = workYear;
    }

    public void work(){
        System.out.println("work");
    }

    @Override
    public String toString() {
        return "Worker{" +"name='" + getName() + '\'' +
                ", age=" + getAge() +
                ",workYear=" + workYear +
                '}';
    }
}
