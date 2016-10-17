package com.example;

/**
 * Created by wuhai on 2016/10/17.
 */
public class Worker extends Person implements Comparable<Worker>{

    private int workYear;

    public Worker(String name, int age, int workYear){
        super(name, age);
        this.workYear = workYear;
    }

    public void work(){
        System.out.println("work");
    }

    /**
     * 返回0代表它们相等；
     * 返回值>0 代表this排在被比较对象之后
     * 返回值<0 代表this排在被比较对象之前
     */
    @Override
    public int compareTo(Worker o) {
        //key age
//        if(this.getAge() > o.getAge()){
//            System.out.println("compareTo:1");
//            return 1;
//        }else if(this.getAge() == o.getAge()){
//            System.out.println("compareTo:0");
//            return 0;
//        }
//        System.out.println("compareTo:-1");
//        return -1;

        //key 1:age  2:name
        int temp = getAge() - o.getAge();
        return temp == 0 ? getName().compareTo(o.getName()):temp;
    }

    @Override
    public String toString() {
        return "Worker{" +"name='" + getName() + '\'' +
                ", age=" + getAge() +
                ",workYear=" + workYear +
                '}';
    }
}
