package com.example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 一对一聊天服务端
 */
public class ServerChatOntToOne {

	public static void main(String[] args) {
		ServerSocket server = null;
		Map<String, Socket> map = new HashMap<String, Socket>();
		Socket ss = null;
		try {
			server = new ServerSocket(5566);
			System.out.println("服务器端已经建立。。。");
			while (true) {
				ss = server.accept();
				new Thread(new ServerThread(ss, map)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ServerThread implements Runnable {
	private Socket ss;
	private Map<String, Socket> map;

	public ServerThread(Socket ss, Map<String, Socket> map) {
		super();
		this.ss = ss;
		this.map = map;
	}

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						ss.getInputStream()));
				String info = br.readLine();
				// registe aa
				String name = "";
				// 表示注册
				if (info.startsWith("registe")) {
					// 如果以registe开头，表示此行是客户端传给服务器的注册信息
					String sa[] = info.split(" +");
					name = sa[1];// 或取注册客户端的名字
					map.put(name, ss);// 将名字以及对应的Socket存入到map中
					System.out.println(map);
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(ss.getOutputStream()));
					bw.write(name + ",您已注册成功！");
					bw.newLine();
					bw.flush();
				}
				// @cc:吃饭啦。。。
				String another = "";
				String msg = "";
				if (info.startsWith("@")) {
					another = info.substring(1, info.indexOf(":"));// cc
					msg = info.substring(info.indexOf(":") + 1);
					Socket socket = map.get(another);// 要接收数据的一方
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));
					/**
					 * 发送another:getAnother(ss)
					 */
					bw.write(getAnother(ss)+":"+msg);
					bw.newLine();
					bw.flush();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	/**
	 * 默认为value对应唯一key
	 * @param socket
	 * @return
	 */
	private String getAnother(Socket socket){
		Set<String> kset = map.keySet();
		for(String ks:kset){
			if(socket.equals(map.get(ks))){
				return ks;
			}
		}
		return null;
	}
}
