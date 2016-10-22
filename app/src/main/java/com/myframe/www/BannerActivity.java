package com.myframe.www;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.manager.XYWYImageLoader;
import com.myframe.www.utils.CommonUtils;
import com.myframe.www.utils.MyWindowManager;
import com.myframe.www.widget.titlebar1.TitleBar;
import com.myframe.www.widget.banner.banner1.BannerView;
import com.myframe.www.widget.banner.banner2.AutoScrollViewPager;
import com.myframe.www.widget.banner.banner2.adapter.PagerAdapter;
import com.myframe.www.widget.banner.banner2.model.ADInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

/**
 * 轮播图
 *
 * @author wuhai
 *         create at 2016/2/17 9:41
 */
public class BannerActivity extends BaseActivity {


    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.banner_layout)
    BannerView bannerLayout;
    @Bind(R.id.banner_layout2)
    AutoScrollViewPager bannerLayout2;
    @Bind(R.id.imageview)
    ImageView imageview;

    private List<View> ivs;
    private List<ADInfo> infos1 = new ArrayList<>();
    private PagerAdapter imagePagerAdapter1, imagePagerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        titleBar.setBackOnClickListener(new View.OnClickListener() {//返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //轮播1
        initData();
        bannerLayout.setViews(ivs);

        //轮播2 数据
        String json = CommonUtils.getFromAssets("banner2.json", this);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ADInfo>>() {
        }.getType();
        infos1 = gson.fromJson(json, type);

        //轮播2
        MyWindowManager.setViewHeitgh(this, bannerLayout2,
                MyWindowManager.getWidth(this), 250, 750);
        imagePagerAdapter1 = new PagerAdapter(this, infos1,
                mPagerViewOnClick1, 1).setInfiniteLoop(true);
        bannerLayout2.setAdapter(imagePagerAdapter1);
        bannerLayout2.setInterval(3000);
        //mAutoScrollViewPager1.setCurrentItem( ListUtils.getSize(infos1)*50,
        //        true);
        bannerLayout2.startAutoScroll();

        String str = "http://xs3.op.xywy.com/api.iu1.xywy.com/app_xywy/20160112/eda248479027eef98792a22e143bc1fa32146.png";
        XYWYImageLoader.getInstance().displayImage(str,imageview);
    }

    private void initData() {
        ivs = new ArrayList<View>();
        int[] imgIds = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
//        int[] imgIds = { R.drawable.banner1};
        for (int i : imgIds) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(i);
//			iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ivs.add(iv);
        }
    }

    private PagerViewOnClick mPagerViewOnClick1 = new PagerViewOnClick() {
        @Override
        public void onClick(int i) {
            ToastUtils.showShort(BannerActivity.this, "点我");
        }
    };

    public interface PagerViewOnClick {
        void onClick(int i);
    }

}
