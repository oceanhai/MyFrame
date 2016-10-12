package www.wuhai.common.utils;

import android.util.Log;

/**
 * Log管理工具类
 */
public final class L {

    private static final String TAG = "Default Tag";
    public static boolean isDebug = true;

    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static void i(String msg) {
        if (isDebug){
            Log.i(TAG, msg);
        }
    }

    public static void i(int msg) {
        if (isDebug){
            Log.i(TAG, msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug){
            Log.d(TAG, msg);
        }
    }

    public static void d(int msg) {
        if (isDebug){
            Log.d(TAG, msg + "");
        }
    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }
}
