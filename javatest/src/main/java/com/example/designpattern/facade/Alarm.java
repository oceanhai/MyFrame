package com.example.designpattern.facade;

/**
 * Created by wuhai on 2017/10/24 13:54.
 * 描述：
 */

public class Alarm {
    public void Activate()
    {
        Console.WriteLine("Activating the alarm.");
    }

    public void Deactivate()
    {
        Console.WriteLine("Deactivating the alarm.");
    }

    public void Ring()
    {
        Console.WriteLine("Ringing the alarm.");
    }

    public void StopRing()
    {
        Console.WriteLine("Stop the alarm.");
    }
}
