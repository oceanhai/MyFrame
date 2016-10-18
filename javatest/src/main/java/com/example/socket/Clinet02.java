package com.example.socket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Clinet02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket client = new Socket("192.168.1.3", 23456);
		System.out.println("4客户端创建完毕，申请连接服务器。。");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		String str = "5我叫张三，服务器你好";
		bw.write(str);
		bw.newLine();// 写入换行符
		bw.flush();
		System.out.println("6数据传完了。");
		bw.close();
		client.close();

	}
}
