package www.wuhai.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * UI 密度 dp px
 * 单位换算工具类
 */
public final class DensityUtils {

    private final static String TAG = "DensityUtils";

    private DensityUtils() {
        /** cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(float spVal) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spVal * fontScale + 0.5f);
    }

    /**
     * px转dp
     */
    public static float px2dp(float pxVal) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取密度
     *
     * @param acitivity
     * @return
     */
    public static float getDensity(Activity acitivity) {
        DisplayMetrics dm = new DisplayMetrics();
        Display d = acitivity.getWindowManager().getDefaultDisplay();
        d.getMetrics(dm);

        /**
         * 两种方法求得结果一样
         */
        L.v(TAG,"density:"+Resources.getSystem().getDisplayMetrics().density);
        L.v(TAG,"densityDpi:"+Resources.getSystem().getDisplayMetrics().densityDpi);
        L.v(TAG,"scaledDensity:"+Resources.getSystem().getDisplayMetrics().scaledDensity);//字体的缩放因子
        L.v(TAG, "dm.density:"+dm.density);
        L.v(TAG, "dm.densityDpi:"+dm.densityDpi);
        L.v(TAG, "dm.scaledDensity:"+dm.scaledDensity);
        L.v(TAG, "dm.xdpi:"+dm.xdpi);
        L.v(TAG, "dm.ydpi:"+dm.ydpi);
        return dm.density;
    }

    /**
     * 手机的dpi TODO 没意义 densityDpi可以直接取到
     * @param inch
     * @return
     */
    public static double getDpi(double inch){

        DisplayMetrics metrics1 = Resources.getSystem().getDisplayMetrics();
//        float density1 = metrics1.density;// 密度值
//        L.v(TAG,"density1:"+density1);
        int width1 = metrics1.widthPixels;
        int height1 = metrics1.heightPixels;
        double diagonal1 = Math.sqrt(Math.pow(width1, 2) + Math.pow(height1, 2));
        L.v(TAG,"dpi:" + (diagonal1/inch));
        return diagonal1/inch;
    }

    /**
     * 手机尺寸 英寸 TODO 此方法不对
     * @param context
     * @return
     */
    public static double getWindowInch(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;// 密度值
        float xdpi = metrics.xdpi;//TODO 其实这个值就可以作为对角线dpi用
        L.v(TAG,"xdpi：" + xdpi);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        double z = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));

        // 根据实践，我个人觉得xdpi这个值也可以这么理解，真正的dpi / xdpi = density ,
        // 所以要获取真正的dpi就成了，xdpi*density,所以最后，根据勾股定理算对角线像素，除以dpi，就算出屏幕尺寸了
        double f = (z / (xdpi * density));
        L.v(TAG,"屏幕尺寸：" + f);
        return f;
    }

    /**
     * 手机尺寸 英寸  TODO xdpi用这个值求出的结果最接近 densityDpi值160整数倍都是不准（求出值偏大）
     * @return
     */
    public static double getWindowInch(){
        DisplayMetrics metrics1 = Resources.getSystem().getDisplayMetrics();
//        float densityDpi = metrics1.densityDpi;// 密度值
        float densityDpi = metrics1.xdpi;// 密度值
        L.v(TAG,"densityDpi:"+densityDpi);
        int width = metrics1.widthPixels;
        int height = metrics1.heightPixels;
        double diagonal = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        L.v(TAG,"inch:" + (diagonal/densityDpi));
        return diagonal/densityDpi;
    }
}
