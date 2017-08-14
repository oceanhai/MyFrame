package com.example.lctusb;

import java.io.File;

/**
 * Created by wuhai on 2017/08/14 15:34.
 * 描述：
 */

public class FileTest {

    public static void main(String[] args){
        File file = new File("D:\\lct.txt");
        file.setLastModified(System.currentTimeMillis());
    }

}
