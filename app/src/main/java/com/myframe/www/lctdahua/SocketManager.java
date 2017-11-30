package com.myframe.www.lctdahua;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wuhai on 2017/11/29 17:37.
 * 描述：
 */
public class SocketManager {
    private static final String TAG = "SocketManager";

    private static SocketManager ourInstance = new SocketManager();

    private Socket mSocket;
    private BufferedReader in;
    private PrintWriter out;

    public static SocketManager getInstance() {
        return ourInstance;
    }

    private SocketManager() {
    }

    public void setSocket(Socket socket){
        try {
            mSocket = socket;
            in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //接收线程发送过来信息，并用TextView显示
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG,"msg:"+msg);
        }
    };

    public void sendInfo(String str){
        if (mSocket.isConnected()) {
            if (!mSocket.isOutputShutdown() && out != null) {
                out.println(str);
                out.close();
            }
        }
    }
}
