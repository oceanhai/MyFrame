package com.myframe.www;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.horizontalscrollview02.CopyOfMyRecyclerView;
import com.myframe.www.widget.horizontalscrollview03.GalleryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* 横向的Scrollview 联动图片Gallery
* 但只有 MotionEvent.ACTION_MOVE时候才会换图
* 甩滑的时候 不能正常换图
* @author wuhai
* create at 2016/2/17 11:40
*/
public class HorizontalScrollview02Activity extends BaseActivity {

    private CopyOfMyRecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrollview02);
        initDatas();
        //得到控件
        mRecyclerView = (CopyOfMyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        mImg = (ImageView) findViewById(R.id.id_content);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this, mDatas);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
//                Toast.makeText(HorizontalScrollview02Activity.this, position + "", Toast.LENGTH_SHORT)
//                        .show();

                mImg.setImageResource(mDatas.get(position));
            }
        });
        mRecyclerView.setOnItemScrollChangeListener(new CopyOfMyRecyclerView.OnItemScrollChangeListener() {
            @Override
            public void onChange(View view, int position) {
                mImg.setImageResource(mDatas.get(position));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.l));
    }
}
