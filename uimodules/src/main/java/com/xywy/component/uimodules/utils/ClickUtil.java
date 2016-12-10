package com.xywy.component.uimodules.utils;

/**
 * 点击工具类
 *
 * @author wuhai
 */
public class ClickUtil {

    private static long mLastClickTime;

    /**
     * 是否是快速点击，防止快速点击某个按钮打开多个页面
     *
     * @return 如果点击距上次点击时间小于500毫秒，则返回true，否则返回false
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }

}
