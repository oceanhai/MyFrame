package com.example.lctusb;

import java.io.File;
import java.io.IOException;

/**
 * Created by wuhai on 2017/05/12 21:02.
 * 描述：
 */

public class MD5FileTest {

    public static void main(String[] args){
        try {
            File file = new File("F:\\pos.db");
            if(file.exists()){
                String md5Num = MD5Util.getMd5ByFile(file);
                System.out.println("md5值:"+md5Num);
            }else{
                System.out.println("不存在文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
