package com.example.javaee.day14.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/*
 *  实现TCP文件上传服务器
 *   读取客户端发来的图片,保存在e:\\upload目录中
 *   实现步骤:
 *     1. 创建ServerSocket对象,绑定8000端口
 *     2. 方法accept() 获取客户端连接对象
 *     3. 客户端对象获取字节输入流,读取客户端发来的图片(字节数组)
 *     4. 创建字节输出流对象,字节数字写到目的文件夹e:\\upload目录中
 *     5. 上传成功 写回客户端
 *     6. 释放资源
 *
 *     隐藏步骤: 修改掉客户端的文件名
 *     规则: 域名+毫秒值+随机数
 */
public class TCPServer {
	public static void main(String[] args) throws IOException {
		// 1. 创建ServerSocket对象,绑定8000端口
		ServerSocket server = new ServerSocket(8000);
		//  2. 方法accept() 获取客户端连接对象
		Socket socket = server.accept();
		//3. 客户端对象获取字节输入流,读取客户端发来的图片(字节数组)
		InputStream in = socket.getInputStream();
		//File对象的上传目录
		File uploadDir = new File("D:\\upload");
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		//自定义的规则,写个文件名
		String filename="itcast"+System.currentTimeMillis()+new Random().nextInt(9999999)+".jpg";
		//4. 创建字节输出流对象
		FileOutputStream fos = new FileOutputStream(uploadDir+"\\"+filename);
		byte[] bytes = new byte[1024];
		int len = 0 ;
		while((len = in.read(bytes))!=-1){
			fos.write(bytes, 0, len);
		}
		//5. 上传成功 写回客户端
		socket.getOutputStream().write("上传成功".getBytes());
		fos.close();
		socket.close();
		server.close();
	}
}
