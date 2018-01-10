package com.example.lctencrypt;



/**
 *  工具类
 *
 */
public class PosEncryptUtils {
    /**
     * AES加密key  16、24、32字节秘钥
     */
    private static final String keyAES = "8f00b204e9800998jhtrvvh6j9bg3eh7";
    private static final String charsetName = "UTF-8";



    private  static boolean needEncrypt = true;//是否需要加密


    /**
     * AES算法，加密
     *
     * @param msgString 待加密字符串
     * @return 加密后的结果一般都会用base64编码进行传输
     */
    public static String encryptAES(String msgString) {


        if(needEncrypt) {
            String encryptedText ="";
            try {
                byte[] encryptAES = EncryptUtils.encryptAES(msgString.getBytes(charsetName),
                        EncryptUtils.encryptSHA256(keyAES.getBytes(charsetName)));
                encryptedText = new String(Base64.encode(encryptAES,
                        Base64.DEFAULT), charsetName);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return  encryptedText;
        }
        else{
            return  msgString;
        }

    }

    /**
     * AES算法，解密
     *
     * @param msgString 待解密字符串
     * @return 解密后的字符串（明文）
     */
    public static String decryptAES(String msgString) {

        if(needEncrypt) {
            String decryptText = "";
            try {
                byte[] byteMi = Base64.decode(msgString, Base64.DEFAULT);
                decryptText = new String(EncryptUtils.decryptAES(byteMi,
                        EncryptUtils.encryptSHA256(keyAES.getBytes(charsetName))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return decryptText;
        }
        else{
            return  msgString;
        }

    }
}
