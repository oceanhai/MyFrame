package com.myframe.www;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.dragandswipewithrecyclerview.RecyclerListAdapter;
import com.myframe.www.widget.dragandswipewithrecyclerview.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* 可拖拽的横向listview效果
* @author wuhai
* create at 2016/2/17 17:35
*/
public class HorizontalScrollview04Activity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mAdapter;
    private List<Integer> mDatas;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrollview04);

        initDatas();
        //得到控件
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new RecyclerListAdapter(this);
        mAdapter.setDatas(mDatas);
        mAdapter.setOnItemClickLitener(new RecyclerListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(HorizontalScrollview04Activity.this, position + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mAdapter.setOnLongClickListener(new RecyclerListAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder, int position) {
                mItemTouchHelper.startDrag(viewHolder);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initDatas()
    {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.drawable.a,
                R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
                R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.l));
    }

}
