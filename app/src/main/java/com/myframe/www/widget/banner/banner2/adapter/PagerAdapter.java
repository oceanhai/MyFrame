/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.myframe.www.widget.banner.banner2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.myframe.www.BannerActivity;
import com.myframe.www.R;
import com.myframe.www.manager.XYWYImageLoader;
import com.myframe.www.widget.banner.banner2.model.ADInfo;
import com.myframe.www.widget.banner.banner2.utils.ListUtils;

import java.util.List;

/**
 * ImagePagerAdapter
 * 
 * @author 
 */
public class PagerAdapter extends RecyclingPagerAdapter {
    private Context       context;
    private List<ADInfo> imageResults;
    private int           size;
    private boolean       isInfiniteLoop;
    private BannerActivity.PagerViewOnClick mPagerViewOnClick;
    private int flag;
    public PagerAdapter(Context context, List<ADInfo> imageResults,
                        BannerActivity.PagerViewOnClick mPagerViewOnClick, int flag) {
        this.context = context;
        this.imageResults = imageResults;
        this.size = ListUtils.getSize(imageResults);
        this.mPagerViewOnClick = mPagerViewOnClick;
        isInfiniteLoop = false;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        // Infinite loop
    	if(imageResults.size()==1){
    		return imageResults.size();
    	}else{
    		return isInfiniteLoop ? Integer.MAX_VALUE : ListUtils.getSize(imageResults);
    	}
        
    }

    /**
     * get really position
     * 
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = new ImageView(context);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView;

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        viewHolder.imageView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.default_banner1));
        final String backurl = (imageResults.get(position % ListUtils.getSize
                (imageResults))).getImageUrl();
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(backurl!=null&&!"".equals(backurl)){
                    mPagerViewOnClick.onClick(position % ListUtils.getSize
                            (imageResults));
                }else{
                }

            }
        });
        String url = (imageResults.get(position % ListUtils.getSize
                (imageResults))).getImageUrl();

        if(url!=null&&!"".equals(url)){
            XYWYImageLoader.getInstance().setLBImage(url, viewHolder.imageView, flag);
        }
        return convertView;
    }


    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public PagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
    
    public void setData(List<ADInfo> imageResults){
    	this.imageResults = imageResults;
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        private ImageView imageView;
    }
}
