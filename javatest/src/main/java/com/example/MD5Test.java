package com.example;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/11/27.
 */
public class MD5Test {

    public static void main(String[] args){
        System.out.println(md5("abc"));
        System.out.println(MD5.KeyMD5("abc"));
    }


    public static String md5(String str){
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            System.out.println("hash.length:"+hash.length);
            for (byte b : hash) {
                System.out.print(b + ",");
            }
            System.out.println();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
