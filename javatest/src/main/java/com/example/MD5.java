package com.example;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static final String SIGNKEY_QUERY = "eb960bf928ae3d72";
	public static final String SIGNKEY_FAMOUS = "eb960bf928ae3d72";
	public static String KeyMD5(String key,String st) {
		
		String inStr = key+ st;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)

			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();

	}
	public static String KeyMD5(String st) {
		
		String inStr = st;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		
		byte[] byteArray = new byte[charArray.length];
		
		for (int i = 0; i < charArray.length; i++)
			
			byteArray[i] = (byte) charArray[i];
		
		byte[] md5Bytes = md5.digest(byteArray);
		
		StringBuffer hexValue = new StringBuffer();
		
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
		
	}

	/**
	 * 请求中有汉字参数时候  使用此加密（无汉字时候也可以使用）
	 * @param data
	 * @return
	 */
	public static String generateSign(String data) {

		try {
			byte[] result = MessageDigest.getInstance("MD5").digest(
					data.getBytes("UTF-8"));
			String signature = new String(HexEncoder.encode(result), "UTF-8");
			return signature;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
