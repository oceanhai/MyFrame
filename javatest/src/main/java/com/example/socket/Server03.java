package com.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server03 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(9527);
		System.out.println("1服务器已经建立。。。");
		while (true) {
			Socket s = server.accept();// 每次连接一个。。。
			System.out.println(s.getInetAddress().getHostName() + "\t"
					+ s.getPort());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			System.out.println(br.readLine());
		}

	}

}