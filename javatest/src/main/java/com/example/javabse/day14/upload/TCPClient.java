package com.example.javabse.day14.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 *  TCP的文件上传客户端
 *   1. Socket对象和服务器连接
 *   2. FileInputStream读取本机上的图片
 *       字节数组缓冲
 *   3. Socket对象获取字节输出流
 *       读取到的字节数组,写到服务器
 *   4. Socket对象获取字节输入流
 *       读取到服务器发来的 上传成功
 */
public class TCPClient {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket("192.168.1.110", 8000);
		//2. FileInputStream读取本机上的图片
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\menghuan.jpg");
		// 3. Socket对象获取字节输出流
		OutputStream out = socket.getOutputStream();
		byte[] bytes = new byte[1024];
		int len = 0 ;
		while((len = fis.read(bytes))!=-1){
			out.write(bytes, 0, len);
		}
		//通知服务器,没有数据了,你别读了
		//socket对象的方法shutdownOutput() 给服务器写了一个终止符号
		socket.shutdownOutput();

		//4. Socket对象获取字节输入流,读取服务器回来的 上传成功
		InputStream in = socket.getInputStream();
		len = in.read(bytes);
		System.out.println("服务器回来: "+ new String(bytes,0,len));

		fis.close();
		socket.close();
	}
}
