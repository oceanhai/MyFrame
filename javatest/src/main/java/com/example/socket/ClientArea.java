package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ClientArea {

	public static void main(String[] args) {
		Socket client = null;
		BufferedReader input = null;// 接收键盘
		BufferedWriter bw = null;// 将数据传给服务器
		BufferedReader br = null;// 从服务器获取结果
		try {
			client = new Socket("192.168.1.3", 34567);
			System.out.println("客户端已经建立。。申请连接。。。");
			input = new BufferedReader(new InputStreamReader(System.in));
			br = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			String[] data = new String[3];
			for (int i = 0; i < data.length; i++) {
				System.out.println("请输入第" + (i + 1) + "条边长：");
				data[i] = input.readLine();
				bw.write(data[i]);// 将变成写给服务器
				bw.newLine();
				bw.flush();
			}
			System.out.println(Arrays.toString(data));
			System.out.println("以上是将边长数据传给服务器。。。等待结果：");
			String result = br.readLine();
			System.out.println("服务器运算后：三角形面积为：" + result);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
