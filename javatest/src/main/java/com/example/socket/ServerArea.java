package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerArea {
	/**
	 * 求给定三角形的面积
	 *
	 * @param data
	 * @return
	 */
	public static double getArea(double[] data) {
		double p = 0.0;// 求三边的总和
		for (int i = 0; i < data.length; i++) {
			p += data[i];
		}
		double s = 0.0;// 三角形的面积
		p /= 2;
		s = Math.sqrt(p * (p - data[0]) * (p - data[1]) * (p - data[2]));
		return s;
	}

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket ss = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			server = new ServerSocket(34567);
			ss = server.accept();
			System.out.println("已有客户端连接。。。");
			br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			bw = new BufferedWriter(
					new OutputStreamWriter(ss.getOutputStream()));
			double[] data = new double[3];
			for (int i = 0; i < data.length; i++) {
				data[i] = Double.parseDouble(br.readLine());// 将读入的String类型，转为double
			}
			System.out.println("接收到了3条边长：" + Arrays.toString(data));
			// 获取运算的结果
			double s = getArea(data);
			bw.write(s + "");
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

