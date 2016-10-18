package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client03 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// TODO Auto-generated method stub
		Socket client = new Socket("192.168.1.3", 9527);
		System.out.println("a申请连接服务器。。。");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		String str = input.readLine();
		bw.write(str);
		bw.newLine();
		bw.flush();
		bw.close();
	}

}
