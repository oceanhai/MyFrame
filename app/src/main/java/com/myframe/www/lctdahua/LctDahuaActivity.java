package com.myframe.www.lctdahua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import java.io.IOException;
import java.net.Socket;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wuhai on 2017/11/29 0029 15:29.
 * 描述：
 */
public class LctDahuaActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.local_ip)
    TextView localIp;
    @Bind(R.id.lan_ip)
    TextView lanIp;
    @Bind(R.id.activity_lct_dahua)
    LinearLayout activityLctDahua;
    @Bind(R.id.send_message)
    Button sendMessage;
    @Bind(R.id.get_local_ip)
    Button getLocalIp;
    @Bind(R.id.get_device_ip)
    Button getDeviceIp;
    @Bind(R.id.get_device_ip2)
    Button getDeviceIp2;
    @Bind(R.id.lan_ip2)
    TextView lanIp2;

    private NetTool netTool;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LctDahuaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lct_dahua);
        ButterKnife.bind(this);

        netTool = new NetTool(this);

        getLocalIp.setOnClickListener(this);
        getDeviceIp.setOnClickListener(this);
        getDeviceIp2.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
    }

    //PLU信息
    private String msg =
            "!0V" + "0001" + "A2281080" + "002000" + "000000000803000000000000000000000000000000000000000000000000" +
                    "B186642525027" +
                    "C186642525028D186642525028E";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_message:
                try {
                    Socket socket = new Socket("192.168.9.150", 4001);
                    SocketManager.getInstance().setSocket(socket);
                    SocketManager.getInstance().sendInfo("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.get_local_ip:
                localIp.setText(netTool.getLocAddress());
                break;
            case R.id.get_device_ip:
                //方式一
                NetworkSniffTask task = new NetworkSniffTask(this);
                task.execute();
                break;
            case R.id.get_device_ip2:
                //方式二
                netTool.scan();
                break;
        }
    }
}
