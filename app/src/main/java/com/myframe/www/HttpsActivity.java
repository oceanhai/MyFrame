package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HttpsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.iv01)
    ImageView iv01;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HttpsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                getUnSafeFromServer();
                break;
        }
    }

    /**
     * https://blog.csdn.net/hfeng101/article/details/10163627
     */
    private static final String CLIENT_AGREEMENT = "TLS";//使用协议
    private static final String CLIENT_KEY_MANAGER = "X509";//密钥管理器
    private static final String CLIENT_TRUST_MANAGER = "X509";//
    private static final String CLIENT_KEY_KEYSTORE = "BKS";//密库，这里用的是BouncyCastle密库
    private static final String CLIENT_TRUST_KEYSTORE = "BKS";//
    private static final String CLIENT_KET_PASSWORD = "123456";//私钥密码
    private static final String CLIENT_TRUST_PASSWORD = "123456";//信任证书密码
    private static final int SERVER_PORT = 50030;//端口号
    private static final String SERVER_IP = "218.206.176.146";//连接IP
    private SSLSocket Client_sslSocket;
    public void init(){
        try {
            //取得SSL的SSLContext实例
            SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //取得KeyManagerFactory和TrustManagerFactory的X509密钥管理器实例
            KeyManagerFactory keyManager = KeyManagerFactory.getInstance(CLIENT_KEY_MANAGER);
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);

            //取得BKS密库实例
            KeyStore kks = KeyStore.getInstance(CLIENT_KEY_KEYSTORE);
            KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
            //加客户端载证书和私钥,通过读取资源文件的方式读取密钥和信任证书
//            kks.load(getBaseContext().getResources().openRawResource(R.drawable.kclient),CLIENT_KET_PASSWORD.toCharArray());
//            tks.load(getBaseContext().getResources().openRawResource(R.drawable.kclient),CLIENT_TRUST_PASSWORD.toCharArray());

            //初始化密钥管理器
            keyManager.init(kks,CLIENT_KET_PASSWORD.toCharArray());
            trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(keyManager.getKeyManagers(),trustManager.getTrustManagers(),null);
            //生成SSLSocket
            Client_sslSocket = (SSLSocket) sslContext.getSocketFactory().createSocket(SERVER_IP,SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUnSafeFromServer() {
        String imageviewUrl1 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";

        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                return null;
            }
        };
    }
}
