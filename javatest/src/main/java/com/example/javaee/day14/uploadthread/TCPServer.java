package com.example.javaee.day14.uploadthread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/*
 *  实现多线程的上传文件
 *  打开8000端口,等待客户端连接
 *  只要有客户端连接,获取客户端的连接,开启新的线程
 */
public class TCPServer {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(8000);
		while(true){
			Socket socket = server.accept();
			new Thread(new Upload(socket)).start();
		}
	}
}
