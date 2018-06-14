package com.example.javaee.day14.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(8000);
		Socket socket = server.accept();
		InputStream in = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len = in.read(bytes);
		System.out.println(new String(bytes));
		
		OutputStream out = socket.getOutputStream();
		out.write("<font color='red' size='+7'>12345</font><br/>".getBytes());
		out.write("<input type='file'/>".getBytes());
		
		socket.close();
		server.close();
	}
}
