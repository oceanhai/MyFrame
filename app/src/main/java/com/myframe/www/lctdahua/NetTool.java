package com.myframe.www.lctdahua;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by wuhai on 2017/11/29 15:48.
 * 描述：Android:手机扫描局域网所有ip，并进行socket通讯
 * https://www.2cto.com/kf/201312/265657.html
 * socket 参考：http://www.jianshu.com/p/089fb79e308b
 */

public class NetTool {
    private static final String TAG = "NetTool";

    private int SERVERPORT = 4001;

    private String locAddress;//存储本机ip，例：本地ip ：192.168.1.

    private Runtime run = Runtime.getRuntime();//获取当前运行环境，来执行ping，相当于windows的cmd

    private Process proc = null;

    private String ping = "ping -c 1 -w 0.5 " ;//其中 -c 1为发送的次数，-w 表示发送后等待响应的时间

    private int j;//存放ip最后一位地址 0-255

    private Context ctx;//上下文

    public NetTool(Context ctx){
        this.ctx = ctx;
    }



    private Handler handler = new Handler(){

        public void dispatchMessage(Message msg) {
            switch (msg.what) {

                case 222:// 服务器消息
                    break;

                case 333:// 扫描完毕消息
//                    Toast.makeText(ctx, "扫描到主机："+((String)msg.obj).substring(6), Toast.LENGTH_LONG).show();
                    Log.e(TAG,"扫描到主机："+((String)msg.obj).substring(6));
                    break;
                case 444://扫描失败
//                    Toast.makeText(ctx, (String)msg.obj, Toast.LENGTH_LONG).show();
                    Log.e(TAG,"扫描失败："+ msg.obj);
                    break;
            }
        }

    };

    public String bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    private BufferedWriter bw = null;
    private Socket socket = null;

    //向serversocket发送消息
    public String sendMsg(String ip,String msg) {
        Log.e(TAG,"sendMsg ip="+ip+",msg="+msg);
        String res = null;

        String product_name = "干脆面";
        String str = ChineseAreaCode.toAreaCode(product_name);

        String msg1 =
                "!0V" + "0001" + //plu_num 秤的plu编码
                        "A22"+
                        "81080" + //scale_barcode 条码秤编码 5位
                        "003000" + //单价 单位为:分/kg eg:30元/KG
                        "0000000"+
                        "030"+//保质期
                        "03"+//scale_shop_num 电子秤店铺号
                        "000000000000000"+
                        "00000"+//皮重
                        "0000000000000000000000000000" +
                        "B" +str+//商品名
                        "C186642525028D186642525028E";
        Log.e(TAG,"sendMsg ip="+ip+",msg1="+msg1);
        try {
            socket = new Socket(ip, SERVERPORT);
            if(socket.isConnected()){
                Log.e(TAG,"socket 链接成功");
//                //向服务器发送消息
//                PrintWriter os = new PrintWriter(socket.getOutputStream());
//                os.println(msg);
//                os.flush();// 刷新输出流，使Server马上收到该字符串
//
//                //从服务器获取返回消息
//                DataInputStream input = new DataInputStream(socket.getInputStream());
//                res = input.readUTF();
//                Log.e(TAG,"server 返回信息：" + res);
//                Message.obtain(handler, 222, res).sendToTarget();//发送服务器返回消息

                bw = new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream()));
                bw.write(msg1);// 给服务器发一条消息
                bw.newLine();
                bw.flush();

                // 读的线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    socket.getInputStream()));// 从服务器读数据 的输入流
                            while (true) {
                                // 读取服务器的数据
                                String msg = br.readLine();
                                Log.e(TAG,"服务器的数据返回："+msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();// 启动从服务器读数据的线程
            }else{
                Log.e(TAG,"socket 链接失败");
            }

        } catch (Exception unknownHost) {
            Log.e(TAG,"You are trying to connect to an unknown host!");
        }
        return res;
    }


    /**
     * 扫描局域网内ip，找到对应服务器
     */
    public void scan(){

        locAddress = getLocAddrIndex();//获取本地ip前缀

        if(locAddress.equals("")){
            Toast.makeText(ctx, "扫描失败，请检查wifi网络", Toast.LENGTH_LONG).show();
            return ;
        }

        for ( int i = 150; i < 151; i++) {//创建256个线程分别去ping

            j = i ;

            new Thread(new Runnable() {

                public void run() {

                    String p = NetTool.this.ping + locAddress + j ;

                    String current_ip = locAddress+ j;

                    try {
                        proc = run.exec(p);

                        int result = proc.waitFor();
                        if (result == 0) {
                            Log.e(TAG,"连接成功" + current_ip);
                            // 向服务器发送验证信息
                            String msg = sendMsg(current_ip,"scan"+getLocAddress()+" ( "+android.os.Build.MODEL+" ) ");

                            //如果验证通过...
                            if (msg != null){
                                if (msg.contains("OK")){
                                    Log.e(TAG,"服务器IP：" + msg.substring(8,msg.length()));
                                    Message.obtain(handler, 333, msg.substring(2,msg.length())).sendToTarget();//返回扫描完毕消息
                                }
                            }
                        } else {

                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    } finally {
                        proc.destroy();
                    }
                }
            }).start();

        }

    }


    //获取本地ip地址
    public String getLocAddress(){

        String ipaddress = "";

        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            // 遍历所用的网络接口
            while (en.hasMoreElements()) {
                NetworkInterface networks = en.nextElement();
                // 得到每一个网络接口绑定的所有ip
                Enumeration<InetAddress> address = networks.getInetAddresses();
                // 遍历每一个接口绑定的所有ip
                while (address.hasMoreElements()) {
                    InetAddress ip = address.nextElement();
//                    if (!ip.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ip.getHostAddress())) {
                    /**
                     * Android API-23: InetAddressUtils replacement
                     * ip instanceof Inet4Address   替换  InetAddressUtils.isIPv4Address(ip.getHostAddress())
                     */
                    if (!ip.isLoopbackAddress() && (ip instanceof Inet4Address)) {
                        ipaddress = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Log.e(TAG, "获取本地ip地址失败");
            e.printStackTrace();
        }

        Log.e(TAG, "本机IP:" + ipaddress);
        return ipaddress;

    }

    //获取IP前缀
    public String getLocAddrIndex(){

        String str = getLocAddress();

        if(!str.equals("")){
            return str.substring(0,str.lastIndexOf(".")+1);
        }

        return null;
    }

    //获取本机设备名称
    public String getLocDeviceName() {

        return android.os.Build.MODEL;

    }
}
