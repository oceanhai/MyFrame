package com.example.designpattern.facade;

/**
 * Created by wuhai on 2017/10/24 13:53.
 * 描述：
 */

public class Light {
    public void TurnOff()
    {
        Console.WriteLine("Turning off the light.");
    }

    public void TurnOn()
    {
        Console.WriteLine("Turning on the light.");
    }

    public void ChangeBulb()
    {
        Console.WriteLine("changing the light-bulb.");
    }
}
