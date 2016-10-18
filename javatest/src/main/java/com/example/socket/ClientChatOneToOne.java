package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientReciver implements Runnable {
	private Socket client = null;

	public ClientReciver(Socket client) {
		super();
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					client.getInputStream()));// 从服务器读数据 的输入流
			while (true) {
				// 读取服务器的数据
				String msg = br.readLine();
				System.out.println(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

/**
 * 一对一聊天客户端
 */
public class ClientChatOneToOne {

	public static void main(String[] args) {
		Socket client = null;
		BufferedReader input = null;
		BufferedWriter bw = null;
		try {
			client = new Socket("192.168.1.3", 5566);
			input = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			System.out.println("客户端申请连接。。。");
			// 读的线程
			new Thread(new ClientReciver(client)).start();// 启动从服务器读数据的线程
			// 写的线程
			while (true) {
				String info = input.readLine();// 读取控制台的数据，
				// @cc:吃饭

				bw.write(info);// 将读入的数据写给服务器
				bw.newLine();
				bw.flush();

			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
