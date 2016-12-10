package com.xywy.component.uimodules.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by shijiazi on 16/1/22.
 * 设备相关工具类
 */
public class DeviceUtils {

    /**
     * 获取手机大小（分辨率）
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenPix(Context context) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        try {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(displaysMetrics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displaysMetrics;
    }

}
