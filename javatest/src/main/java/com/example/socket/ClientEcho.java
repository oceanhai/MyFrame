package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEcho {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket client = null;
		BufferedReader input = null;// 接收控制台的数据
		BufferedWriter bw = null;// 写给服务器的
		BufferedReader br = null;// 读服务器的数据
		try {
			client = new Socket("192.168.1.3", 6699);
			System.out.println("客户端已经创建，，，");
			input = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			while (true) {
				System.out.println("请输入信息：");
				String line = input.readLine();// 从控制台输入
				bw.write(line);
				bw.newLine();
				bw.flush();
				if ("over".equals(line)) {
					System.out.println("客户端已经推出。。");
					break;
				}
				String msg = br.readLine();
				System.out.println(msg);

			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
