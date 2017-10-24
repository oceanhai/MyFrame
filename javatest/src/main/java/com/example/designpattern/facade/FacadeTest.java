package com.example.designpattern.facade;

/**
 * Created by wuhai on 2017/10/24 13:56.
 * 描述：门面模式
 * 我们考察一个保安系统的例子，以说明门面模式的功效。一个保安系统由两个录像机、三个电灯、
 * 一个遥感器和一个警报器组成。保安系统的操作人员需要经常将这些仪器启动和关闭。
 */

public class FacadeTest {
    private static Camera camera1, camera2;
    private static Light light1, light2, light3;
    private static Sensor sensor;
    private static Alarm alarm;

    static {
        camera1 = new Camera();
        camera2 = new Camera();
        light1 = new Light();
        light2 = new Light();
        light3 = new Light();
        sensor = new Sensor();
        alarm = new Alarm();
    }

    public static void main(String[] args){
        method02();
    }

    /**
     * 使用门面
     */
    private static void method02() {
        SecurityFacade facade = new SecurityFacade();
        facade.activate();
    }

    /**
     * 未使用门面模式
     */
    private static void method01() {
        camera1.TurnOn();
        camera2.TurnOn();
        light1.TurnOn();
        light2.TurnOn();
        light3.TurnOn();
        sensor.Activate();
        alarm.Activate();
    }

}
