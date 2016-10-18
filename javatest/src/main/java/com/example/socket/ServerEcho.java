package com.example.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		Socket ss = null;

		try {
			server = new ServerSocket(6699);
			while (true) {// 接收客户端
				ss = server.accept();// 阻塞至
				System.out.println(ss.getInetAddress() + "\t" + ss.getPort());
				new Thread(new EchoThread(ss)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
