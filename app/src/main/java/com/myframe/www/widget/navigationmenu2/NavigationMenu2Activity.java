package com.myframe.www.widget.navigationmenu2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.navigationmenu1.Fragment1;
import com.myframe.www.widget.navigationmenu1.Fragment2;
import com.myframe.www.widget.navigationmenu1.Fragment3;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationMenu2Activity extends BaseActivity {

    @Bind(R.id.search_result_medicine_button)
    RadioButton searchResultMedicineButton;
    @Bind(R.id.search_result_query_button)
    RadioButton searchResultQueryButton;
    @Bind(R.id.search_result_disease_button)
    RadioButton searchResultDiseaseButton;
    @Bind(R.id.search_result_radio_group)
    SegmentedRadioGroup mRadioGroup;
    @Bind(R.id.search_result_view_pager)
    ViewPager mViewPager;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private static int button;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NavigationMenu2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu2);
        ButterKnife.bind(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragment1;
                    case 1:
                        return fragment2;
                    case 2:
                        return fragment3;
                    default:
                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup
                                .check(R.id.search_result_medicine_button);
                        button = R.id.search_result_medicine_button;
                        break;
                    case 1:
                        mRadioGroup
                                .check(R.id.search_result_query_button);
                        button = R.id.search_result_query_button;
                        break;
                    case 2:
                        mRadioGroup
                                .check(R.id.search_result_disease_button);
                        button = R.id.search_result_disease_button;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId) {
                        switch (checkedId) {
                            case R.id.search_result_medicine_button:
                                mViewPager.setCurrentItem(0);
                                break;
                            case R.id.search_result_query_button:
                                mViewPager.setCurrentItem(1);
                                break;
                            case R.id.search_result_disease_button:
                                mViewPager.setCurrentItem(2);
                                break;
                        }
                    }
                });

        mRadioGroup.check(R.id.search_result_medicine_button);//默认：商品
    }
}
