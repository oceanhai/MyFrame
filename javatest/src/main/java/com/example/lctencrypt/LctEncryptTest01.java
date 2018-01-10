package com.example.lctencrypt;

/**
 * Created by wuhai on 2018/01/10 14:47.
 * 描述：
 */

public class LctEncryptTest01 {

    public static void main(String[] args){
        encryptAES("");//加密
        decryptAES("37ibiz0Au8WlujVNASPErQ==");//解密
    }

    /**
     * 解密
     * @param msg
     */
    public static void decryptAES(String msg){
        if(msg.equals("") || msg == null){
            return;
        }

        String msgDecrypt = PosEncryptUtils.decryptAES(msg);
        System.out.println("解密后："+msgDecrypt);
    }

    /**
     * 加密
     * @param msg
     */
    public static void encryptAES(String msg){
        if(msg.equals("") || msg == null){
            return;
        }

        String msgEncrypt = PosEncryptUtils.encryptAES(msg);
        System.out.println("加密后："+msgEncrypt);
    }

    public static void method01(){
        String msg = "15";
        String msgEncrypt = PosEncryptUtils.encryptAES(msg);
        System.out.println("加密后："+msgEncrypt);

        String msgDecrypt = PosEncryptUtils.decryptAES(msgEncrypt);
        System.out.println("解密后："+msgDecrypt);
    }

}
