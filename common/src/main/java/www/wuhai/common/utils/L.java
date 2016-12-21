package www.wuhai.common.utils;

import android.content.Context;
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

    /**
     * 初始化 debug模式下才打印信息
     * @param context
     */
    public static void init(Context context){
        try {
            Class classZ = Class.forName(context.getPackageName() + ".BuildConfig");
            String build_type = (String) classZ.getDeclaredField("BUILD_TYPE").get(classZ);
            if("release".equalsIgnoreCase(build_type)){
                isDebug = false;
            }else{
                isDebug = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isDebug){
            Log.v(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug){
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isDebug){
            Log.e(tag, msg, tr);
        }
    }

}
