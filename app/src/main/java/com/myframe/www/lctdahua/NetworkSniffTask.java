package com.myframe.www.lctdahua;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.net.InetAddress;

/**
 * Created by wuhai on 2017/11/30 11:30.
 * 描述：不靠谱
 */

public class NetworkSniffTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "nstask";

    private WeakReference<Context> mContextRef;

    public NetworkSniffTask(Context context) {
        mContextRef = new WeakReference<Context>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Let's sniff the network");

        try {
            Context context = mContextRef.get();

            if (context != null) {

                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

                WifiInfo connectionInfo = wm.getConnectionInfo();
                int ipAddress = connectionInfo.getIpAddress();
                String ipString = Formatter.formatIpAddress(ipAddress);


                Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
                Log.d(TAG, "ipString: " + String.valueOf(ipString));

                String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                Log.d(TAG, "prefix: " + prefix);

                long startTime = System.currentTimeMillis();
                for (int i = 150; i < 160; i++) {
                    String testIp = prefix + String.valueOf(i);

                    InetAddress address = InetAddress.getByName(testIp);
                    //测试是否可以达到该地址
                    boolean reachable = address.isReachable(2000);
                    String hostName = address.getCanonicalHostName();

                    if (reachable)
                        Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is reachable!");
                }
                Log.e(TAG, "检索ip一共耗时："+(System.currentTimeMillis()-startTime));
            }
        } catch (Throwable t) {
            Log.e(TAG, "Well that's not good.", t);
        }

        return null;
    }
}
