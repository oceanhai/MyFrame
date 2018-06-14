package com.example.javabse.day14.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 *  TCP的客户端
 *  java.net.Socket类
 *  实现步骤:
 *   1. 创建Socket类的对象
 *     Socket(String host, int port)
 *     服务器IP地址和端口号
 *   2. Socket类的方法
 *     OutputStream getOutputStream()获取字节输出流: 数据写出去,服务器
 *
 *   3. Socket类的方法
 *     InputStream getInputStream() 获取字节输入流: 读取数据,服务器发回来的数据
 *
 *   4. 释放资源

 */
public class TCPClient {
	public static void main(String[] args)throws IOException {
		//1. 创建Socket类的对象
		//主动连接服务器,没有服务器程序,抛出异常
		Socket socket = new Socket("192.168.1.110", 8000);
		//2.  OutputStream getOutputStream()获取字节输出流: 数据写出去,服务器
		OutputStream out = socket.getOutputStream();
		//方法write写出数据到服务器
		out.write("你好TCP".getBytes());

		//3. InputStream getInputStream() 获取字节输入流: 读取数据,服务器发回来的数据
		InputStream in = socket.getInputStream();
		byte[] bytes = new byte[10];
		int len = 0 ;
		len = in.read(bytes);
		System.out.println(new String(bytes,0,len));

		//4 释放资源
		socket.close();
	}
}
