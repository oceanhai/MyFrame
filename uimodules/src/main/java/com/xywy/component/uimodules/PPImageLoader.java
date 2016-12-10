package com.xywy.component.uimodules;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.xywy.component.R;
import com.xywy.component.datarequest.imageWrapper.ImageLoaderUtils;

/**
 * Created by shijiazi on 16/1/22.
 */
public class PPImageLoader {

    private static PPImageLoader instance;
    private DisplayImageOptions mAlbumImageOption;

    private PPImageLoader() {
        mAlbumImageOption = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageOnLoading(R.drawable.ic_pp_default_photo)
                .build();
    }

    public static synchronized PPImageLoader getInstance() {
        if (instance == null) {
            instance = new PPImageLoader();
        }
        return instance;
    }

    /**
     * 显示相册缩略图
     *
     * @param url
     * @param view
     */
    public void displayAlbumImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view, mAlbumImageOption);
    }


    public void displayImage(String url, ImageView view) {
        ImageLoaderUtils.getInstance().displayImage(url, view);
    }
}
