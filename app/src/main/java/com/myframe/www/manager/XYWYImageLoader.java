package com.myframe.www.manager;


import android.graphics.Bitmap;
import android.widget.ImageView;

import com.myframe.www.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;
import com.xywy.component.datarequest.imageWrapper.RoundedBitmapWithBorderDisplayer;

/**
 * Created by shijiazi on 16/1/7.
 */
public class XYWYImageLoader {

    private DisplayImageOptions mDoctorHeadImageOption;
    private DisplayImageOptions mUserHeadImageOption;
    private DisplayImageOptions mUserHeadImageOptionForQuanzi;
    private DisplayImageOptions mPatientHeadImagmeOption;
    private DisplayImageOptions mLBImagmeOption1;
    private DisplayImageOptions mLBImagmeOption2;

    private RoundedBitmapWithBorderDisplayer mDoctorRoundDisplayer;

    public static synchronized XYWYImageLoader getInstance() {
        if(instance == null) {
            instance = new XYWYImageLoader();
        }
        return instance;
    }
    private static XYWYImageLoader instance;
    private XYWYImageLoader() {
        mDoctorRoundDisplayer = new RoundedBitmapWithBorderDisplayer(1, 0xffe0e0e0);
        mDoctorHeadImageOption = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(mDoctorRoundDisplayer)
                .showImageOnLoading(R.drawable.fam_header)
                .showImageOnFail(R.drawable.fam_header)
                .build();

        mUserHeadImageOptionForQuanzi = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .displayer(mDoctorRoundDisplayer)
                .showImageOnLoading(R.drawable.touxiang_for_quanzi)
                .showImageOnFail(R.drawable.touxiang_for_quanzi)
                .showImageForEmptyUri(R.drawable.touxiang_for_quanzi)
                .build();

        mUserHeadImageOption = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .displayer(mDoctorRoundDisplayer)
                .showImageOnLoading(R.drawable.touxiang1)
                .showImageOnFail(R.drawable.touxiang1)
                .showImageForEmptyUri(R.drawable.touxiang1)
                .build();

        mPatientHeadImagmeOption = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageOnLoading(R.drawable.touxiang_ys)
                .showImageOnFail(R.drawable.touxiang_ys)
                .showImageForEmptyUri(R.drawable.touxiang_ys)
                .build();
        mLBImagmeOption1 = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageOnLoading(R.drawable.default_banner1)
                .showImageOnFail(R.drawable.default_banner1)
                .showImageForEmptyUri(R.drawable.default_banner1)
                .build();
        mLBImagmeOption2 = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageOnLoading(R.drawable.default_banner2)
                .showImageOnFail(R.drawable.default_banner2)
                .showImageForEmptyUri(R.drawable.default_banner2)
                .build();
    }

    /**
     * 显示医生头像
     * 注意：医生头像的圆图
     * @param url
     * @param view
     */
    public void displayDoctorHeadImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view, mDoctorHeadImageOption);
    }

    /**
     * 显示用户头像
     * @param url
     * @param view
     */
    public void displayUserHeadImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view, mUserHeadImageOption);
    }

    /**
     * 社区用：头像
     * @param url
     * @param view
     */
    public void displayUserHeadImageForQuanzi(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view, mUserHeadImageOptionForQuanzi);
    }

    public void displayImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view);
    }
    public void patientImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view,mPatientHeadImagmeOption);
    }

    public void setLBImage(String url, ImageView view,int flag) {
        if(flag==1){
            ImageLoaderUtils.getInstance().displayImage(url, view,
                    mLBImagmeOption1);
        }else{
            ImageLoaderUtils.getInstance().displayImage(url, view,
                    mLBImagmeOption2);
        }
    }

}
