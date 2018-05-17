package com.myframe.www.retrofit.xywy.utils;


import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author flyeek
 * @version created at 15/8/24.
 */
public class DeviceUtil {

    /**
     * Get the ANDROID_ID of the OS.
     */
    public static String getAndroidId(Context context) {
        final String androidId =  Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return androidId == null ? "" : androidId;
    }

    /**
     * Return android version eg:5.1.1
     */
    public static String getPublicOs(){
       return Build.VERSION.RELEASE;
    }

    /**
     * Return the model of the device.
     */
    public static String getModel() {
        return Build.MODEL.toLowerCase();
    }

    /**
     * Return the supported cpu abis
     */
    public static String getSupportABIs() {
        return Build.CPU_ABI + ":" + Build.CPU_ABI2;
    }

    /**
     * Return true if the device brand is equal to the given brand name, or false
     * otherwise.
     *
     * @param brand brand name of device.
     */
    public static boolean isBrand(String brand) {
        return Build.BRAND.equalsIgnoreCase(brand);
    }

    public static boolean isMIUI() {
        final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
        final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
        final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

        Properties prop= new Properties();
        boolean isMIUI;
        try {
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        isMIUI= prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        return isMIUI;
    }


    /**
     * Return user agent header of webview.
     * ※最好保存到sp下使用，效率高些
     */
    public static String getUserAgentHeader(Context context) {
        WebView webview;
        webview = new WebView(context);
        webview.layout(0, 0, 0, 0);
        WebSettings settings = webview.getSettings();
        String userAgent = settings.getUserAgentString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String getBaseFilePath(Context context) {
        boolean isSDCanRead = getExternalStorageState();
        String baseLocation = "";
        if (isSDCanRead) {
            baseLocation = getSDCardPath();
        } else {
            baseLocation = context.getFilesDir().getAbsolutePath();
        }
        return baseLocation;
    }

    public static boolean getExternalStorageState() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return false;
        } else {
            return false;
        }
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().toString();
    }

}
