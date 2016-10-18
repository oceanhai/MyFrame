package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class EchoThread implements Runnable {
	private Socket ss;

	public EchoThread(Socket ss) {
		// TODO Auto-generated constructor stub
		this.ss = ss;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			bw = new BufferedWriter(
					new OutputStreamWriter(ss.getOutputStream()));
			// 处理
			while (true) {// 处理客户端的数据
				String str = br.readLine();// 读取客户端的数据
				System.out.println("客户端的请求：" + str);
				if ("over".equals(str)) {
					System.out.println("此客户端已经推出。。");
					break;
				}
				bw.write("ECHO:" + str);// 处理后返回给客户端
				bw.newLine();
				bw.flush();
			}
		} catch (Exception e) {

		}

	}
}