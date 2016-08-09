package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.refresh1.XListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * XListView
 *
 * @author wuhai
 *         create at 2016/4/6 9:40
 */
public class PullToRefresh1Activity extends BaseActivity implements XListView.IXListViewListener {

    @Bind(R.id.xlistview)
    XListView xlistview;

    private View loadFinishView;
    private LinearLayout loadFinishViewParent;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PullToRefresh1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh1);
        ButterKnife.bind(this);

        initView();
        getData();
    }

    private void getData() {
        
    }

    private void initView() {
        //addfooter 注意必须setAdapter前
        loadFinishView = LayoutInflater.from(this).inflate(R.layout.footer_load_finish, xlistview, false);
        loadFinishViewParent = new LinearLayout(this);
        loadFinishViewParent.addView(loadFinishView);
        xlistview.addFooterView(loadFinishViewParent, null, false);//不可点击
        loadFinishView.setVisibility(View.GONE);

        xlistview.setPullLoadEnable(true);//是否能上拉加载
        xlistview.setPullRefreshEnable(true);//是否能下拉刷新
        xlistview.setXListViewListener(this);//下拉刷新 和 上拉加载 实现接口
        xlistview.setItemAnimEnable(true);//是否有缩放动画
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
