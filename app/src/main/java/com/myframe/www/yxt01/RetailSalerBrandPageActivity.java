package com.myframe.www.yxt01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.myframe.www.R;
import com.myframe.www.utils.CommonUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.GsonUtils;

/**
 * 品牌页
 *
 * @author wuhai
 * @time 2016/11/16 14:15
 */
public class RetailSalerBrandPageActivity extends FragmentActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private List<PlasticCategoryModel> categoryModelList;
    private PlasticCategoryAdapter plasticCategoryAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RetailSalerBrandPageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_saler_brand_page);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        plasticCategoryAdapter = new PlasticCategoryAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(plasticCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(plasticCategoryAdapter);
    }

    private void initData() {
        BrandData data = null;
        String json = CommonUtils.getFromAssets("homepagejson1.json", this);
        data = GsonUtils.getInstance().toObj(json, BrandData.class);

        setData(data);
    }

    private void setData(BrandData data) {
        categoryModelList = data.getData().getCategoryList();

        initCategory();
    }

    private void initCategory() {
        plasticCategoryAdapter.setDatas(categoryModelList);
    }
}
