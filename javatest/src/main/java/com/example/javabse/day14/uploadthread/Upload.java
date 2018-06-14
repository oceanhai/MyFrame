package com.example.javabse.day14.uploadthread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

public class Upload implements Runnable{
	private Socket socket;
	public Upload(Socket socket){
		this.socket = socket;
	}

	public void run(){
		try{
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
		}catch(IOException ex){

		}

	}
}
