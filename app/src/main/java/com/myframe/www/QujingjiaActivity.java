package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;


public class QujingjiaActivity extends AppCompatActivity {
    @Bind(R.id.fr_home_bid_feelings_rv)
    RecyclerView frHomeBidFeelingsRv;
    @Bind(R.id.activity_qujingjia)
    CoordinatorLayout activityQujingjia;

    private List<String> mDatas;
    private HomeAdapter mAdapter;

    private static String itemStr = "fragment2";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, QujingjiaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qujingjia);
        ButterKnife.bind(this);

        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add(itemStr + (char) i);
        }
        L.v("data:" + mDatas.toString());

        frHomeBidFeelingsRv.setLayoutManager(new LinearLayoutManager(this));
        frHomeBidFeelingsRv.setAdapter(mAdapter = new HomeAdapter());


    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            HomeAdapter.MyViewHolder holder = new HomeAdapter.
                    MyViewHolder(LayoutInflater.from(QujingjiaActivity.this).
                    inflate(R.layout.item_home, parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
