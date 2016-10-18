package com.example.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server02 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// 第一步：创建Socekt
		ServerSocket server = new ServerSocket(23456);
		System.out.println("1服务器已经建立，等待客户端的连接。。。");
		// 第二步：连接客户端

		Socket s = server.accept();// 等待客户端的连接
		System.out.println("2已有客户端连接：" + s.getInetAddress().getHostName()
				+ "\tport:" + s.getPort());
		// 第三步：获取流
		BufferedReader br = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String line = br.readLine();
		System.out.println("3客户端说：" + line);
		br.close();
		s.close();

	}

}
