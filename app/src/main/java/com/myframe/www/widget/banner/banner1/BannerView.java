package com.myframe.www.widget.banner.banner1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.myframe.www.R;
import com.myframe.www.ShowEdittextActivity;
import com.myframe.www.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/** 
* 
* @author wuhai
* create at 2016/1/26 10:49
*/  
public class BannerView extends RelativeLayout {

    private Context context;
    private AutoBannerViewPager pager;
    private CircleIndicator defaultIndicator;
    private BinnerPagerAdapter adapter;
    private List<View> views;

    private int count = 1; // 广告的数量

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        views = new ArrayList<View>();
        LayoutInflater.from(getContext()).inflate(R.layout.widget_banner1, this);
        pager = (AutoBannerViewPager) findViewById(R.id.binner_pager);
        defaultIndicator = (CircleIndicator) findViewById(R.id.binner_indicator);
        adapter = new BinnerPagerAdapter();
        pager.setAdapter(adapter);
        pager.setSpeed(3000);
        pager.setMethod(AutoBannerViewPager.METHOD_RIGHT);
        pager.setStopScrollWhenTouch();

    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
        adapter.notifyDataSetChanged();
        defaultIndicator.setViewPager(pager);
        pager.startScroll();
    }


    private class BinnerPagerAdapter extends PagerAdapter {

        public BinnerPagerAdapter() {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position % views.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(views.get(position % views.size()));


            final int newpostion = position % views.size();
            /**
             * 点击图片操作
             */
            views.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort(context, "点击了" + newpostion);
                    ShowEdittextActivity.startActivity(context);
                }
            });

            return views.get(position);
        }

    }


}
