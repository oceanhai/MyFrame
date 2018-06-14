package com.example.javaee.day14.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *  实现TCP的服务器
 *  java.net.ServerSocket
 *  实现步骤:
 *    1. 创建ServerSocket对象,绑定端口号
 *    ServerSocket(int port) 传递端口号
 *
 *    2. ServerSocket类方法: 获取到是哪个客户端和我连接了
 *     Socket accept() 拿到是客户端的连接对象Socket
 *
 *    3. 使用客户端的连接对象Socket获取IO流
 *       OutputStream getOutputStream() 字节输出流,写数据,写到客户端
 *       InputStream getInputStream() 字节输入流,读取数据,读取客户端发来的数据
      4. 释放资源
 */
public class TCPServer {
	public static void main(String[] args) throws IOException{
		// 1. 创建ServerSocket对象,绑定端口号
		ServerSocket server = new ServerSocket(8000);
		// 2. Socket accept() 拿到是客户端的连接对象Socket
		//等待功能,等待客户端的连接,没有客户端连接,永远等待
		Socket socket = server.accept();
		//3. Socket类的方法获取字节输入流,读取客户的发送的数据
		InputStream in = socket.getInputStream();
		byte[] bytes = new byte[10];
		int len = 0 ;
		len = in.read(bytes);
		System.out.println(new String(bytes,0,len));

		//Socket类的方法获取字节输出流,数据写回客户端
		OutputStream out = socket.getOutputStream();
		out.write("收到了!".getBytes());

		//4. 释放资源
		socket.close();
		server.close();
	}
}
