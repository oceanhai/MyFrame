package com.example.designpattern.facade;


/**
 * Created by wuhai on 2017/10/24 13:51.
 * 描述：
 */

public class Camera {
    public void TurnOn()
    {
        Console.WriteLine("Turning on the camera.");
    }

    public void TurnOff()
    {
        Console.WriteLine("Turning off the camera.");
    }

    public void Rotate(int degrees)
    {
        Console.WriteLine("Rotating the camera by {0} degrees.");
    }
}
