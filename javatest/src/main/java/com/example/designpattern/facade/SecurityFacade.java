package com.example.designpattern.facade;

/**
 * Created by wuhai on 2017/10/24 14:09.
 * 描述：门面
 */

public class SecurityFacade {
    private Camera camera1, camera2;
    private Light light1, light2, light3;
    private Sensor sensor;
    private Alarm alarm;

    public SecurityFacade() {
        camera1 = new Camera();
        camera2 = new Camera();
        light1 = new Light();
        light2 = new Light();
        light3 = new Light();
        sensor = new Sensor();
        alarm = new Alarm();
    }

    public void activate(){
        camera1.TurnOn();
        camera2.TurnOn();
        light1.TurnOn();
        light2.TurnOn();
        light3.TurnOn();
        sensor.Activate();
        alarm.Activate();
    }

    public void deactivate(){
        camera1.TurnOff();
        camera2.TurnOff();
        light1.TurnOff();
        light2.TurnOff();
        light3.TurnOff();
        sensor.Deactivate();
        alarm.Deactivate();
    }
}
