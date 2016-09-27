package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myframe.www.base.BaseActivity;

import java.net.URLEncoder;

public class WebViewActivity extends BaseActivity {

    private WebView webView;
    private View mRootView;
    // private View mPromptView;

    private boolean mIsLoadPageSuccess;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webview_01);
    }

    /**
     * 关于
     * @param view
     */
    public void about(View view){
        AboutActivity.startActivity(this);
    }

    public void button01(View view) {
        webSetting();
//        webView.loadUrl("http://3gtest.yao.xywy.com/?fromurl=xywy_app");
        webView.loadUrl("http://3g.club.xywy.com/static/20090527/2695389.htm");
//		webView.loadUrl("http://3gtest.yao.xywy.com/store/607-info.htm");
//		webView.loadUrl("http://a.m.tmall.com/i42440062755.htm");

//		webView.setOnLongClickListener(new WebView.OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View v) {
//				return true;
//			}
//		});


    }

    private void webSetting() {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setSupportZoom(true);
        setting.setDomStorageEnabled(true);
        setting.setAppCacheMaxSize(1024 * 1024 * 8);// 设置缓冲大小，我设的是8M
        String appCacheDir = getDir("cache", Context.MODE_PRIVATE).getPath();
        setting.setAppCachePath(appCacheDir);
        setting.setAllowFileAccess(true);
        setting.setAppCacheEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebChromeClient(new WebChromeClient(){

        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				view.loadUrl(url);
//				return true;
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
//				view.setBackgroundResource(R.drawable.png_404_error);
//				mIsLoadPageSuccess = false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//				if (!mIsLoadPageSuccess) {
//					view.setBackgroundResource(R.drawable.png_404_error);
//				}
            }

        });
    }

//	class MyChromeClient extends WebChromeClient {
//		@Override
//		public void onProgressChanged(WebView view, int progress) {
//			if(!MainActivity.this.isFinishing()){//判断下activity是否被销毁
//				showDialog();
//			}
//			if (progress == 100) {
//				loadingDialog.dismiss();
//				webView.setVisibility(View.VISIBLE);
//			} else {
//				webView.setVisibility(View.GONE);
//			}
//		}
//	}

    //浏览器打开
    public void button05(View view){
        Intent intent = new Intent();
        intent.setData(Uri.parse("http://3gtest.yao.xywy.com/?fromurl=xywy_app"));
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    public void button02(View view) {
        webView.getSettings().setJavaScriptEnabled(true);// 启用javascript脚本程序---------？？？？？？？
        // 加载asset中的index.html
//		webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("file:///android_asset/xywy.html");
    }

    //-------------------------------------------------------------------------------------------------
    public void button03(View view){
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(mWebViewClient);
//		webView.loadUrl(getUrl());
        webView.loadUrl(TEST_LOGIN_ROOT_PATH);
    }

    public void button04(View view){
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(mWebViewClient);
        webView.loadUrl("http://doogua.dangdang.com/user/login/");
    }
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            System.out.println("1=============="+url);
            view.loadUrl(url);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            System.out.println("2==============onPageStarted");
        }

        public void onPageFinished(WebView view, String url) {
            System.out.println("3==============onPageFinished");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            System.out.println("4==============onReceivedError");
        }

        @Override
        public void onReceivedSslError(WebView view, android.webkit.SslErrorHandler handler, android.net.http.SslError error) {
            handler.proceed();
            System.out.println("5==============onReceivedSslError");
        };
    };

    private static final String TEST_LOGIN_ROOT_PATH = "http://wxh5test.dangdang.com/touch/login.php?";
    private static final String PREVIEW_ONLINE_LOGIN_ROOT_PATH_NEW = "http://preview.dangdang.com/touch/login.php?";
    private static final String ONLINE_LOGIN_ROOT_PATH = "http://m.dangdang.com/touch/login.php?";

    private String getUrl(){
        StringBuilder sb = new StringBuilder();

        sb.append(TEST_LOGIN_ROOT_PATH);
        sb.append("appkey="+getAppKey());
        sb.append("&source_url="+getSourceUrl());
        sb.append("&deviceType="+"Android");
        sb.append("&deviceSerialNo="+getDeviceSerialNo());
        sb.append("&publicKey="+ getPublicKey());

        return sb.toString();
    }

    private String getAppKey(){
        return "4001";
    }

    private String getSourceUrl(){
        String sourceUrl = "";
        try {
            sourceUrl = "DDreader_Android_"+getVersion();
            return URLEncoder.encode(sourceUrl, "utf-8");
        } catch (Exception e) {
            return sourceUrl;
        }
    }

    private String getDeviceSerialNo(){
        String deviceSerialNo = "";
        try {
            deviceSerialNo = getDeviceId();
            return URLEncoder.encode(deviceSerialNo, "utf-8");
        } catch (Exception e) {
            return deviceSerialNo;
        }
    }

    private String getPublicKey(){
        String pubKey = "";
//		try {
//			DrmWarp drmWarp = DrmWarp.getInstance();
//			int success = drmWarp.getPublicKeyN();
//			if (success != DrmWarp.FAILED) {
//				pubKey = drmWarp.getPublicKey();
//			}
//			return URLEncoder.encode(pubKey, "utf-8");
//		} catch (Exception e) {
//			return pubKey;
//		}
        return pubKey;
    }

    private String getVersion(){
        String clientVersionNo = "";
        try {
            PackageManager pkgm = getPackageManager();
            String pkgName = getPackageName();
            clientVersionNo = pkgm.getPackageInfo(pkgName, 0).versionName;
            return URLEncoder.encode(clientVersionNo, "utf-8");
        } catch (Exception e) {
        }
        return clientVersionNo;
    }

    /**
     * 获取设备id策略： 1，优先IMEI串号； 2，MAC地址串； 3，ANDROID_ID串号(获取 Android 设备的唯一标识码 )；
     *
     * @return
     */
    public String getDeviceId() {
        String deviceId = "";
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = tm.getDeviceId();
        long deviceIdInt = 0;
        try{
            deviceIdInt = Long.parseLong(deviceId);
        }
        catch(Exception e){
            e.printStackTrace();
            deviceIdInt = 0;
        }
        if(deviceIdInt == 0){
            deviceId = getDeviceMacAddress();
            if (deviceId == null) {
                deviceId = getDeviceAndroidId();
            }
        }

        SharedPreferences pref = getSharedPreferences("config",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("device_id", deviceId);
        editor.commit();
        return deviceId;
    }

    /**
     * 获取mac地址作为认证
     *
     * @return
     */
    public String getDeviceMacAddress() {

        String macAddr = "";
        try {
            WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            macAddr = info.getMacAddress();
        } catch (Exception e) {
            macAddr = null;
            e.printStackTrace();
        }

        return macAddr;
    }

    /**
     * 获取设备的唯一标识码
     */
    public String getDeviceAndroidId() {
        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }
}
