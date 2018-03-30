package com.example.javabase;

import com.example.designpattern.adapter.Source;

import java.io.File;
import java.util.List;

/**
 * Created by wuhai on 2018/01/04 9:53.
 * 描述：
 */

public class Test01 {

    private static long mLastClickTime;
    private static String path = "D:\\LctPosCrash";

    public static void main(String[] args){
        method01("xx\\xx\\err.txt");
        method02();
        method03();
    }

    private static void method03() {
        List<File> list = FileUtils.listFilesInDir(path);
        for(int x=0;x<list.size();x++){
           list.get(x).delete();
        }
    }

    private static void method02() {
        List<File> list = FileUtils.listFilesInDir(path);
        for(int x=0;x<list.size();x++){
            System.out.println(list.get(x).getAbsolutePath());
        }
    }

    private static void method01(String str) {
        System.out.println(str.substring(str.lastIndexOf("\\")+1));
    }
}
