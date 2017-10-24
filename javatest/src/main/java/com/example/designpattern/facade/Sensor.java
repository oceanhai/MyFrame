package com.example.designpattern.facade;

/**
 * Created by wuhai on 2017/10/24 13:54.
 * 描述：
 */

public class Sensor {
    public void Activate()
    {
        Console.WriteLine("Activating the sensor.");
    }

    public void Deactivate()
    {
        Console.WriteLine("Deactivating the sensor.");
    }

    public void Trigger()
    {
        Console.WriteLine("The sensor has triggered.");
    }
}
