package com.myframe.www.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
* RecyclerView基类adapter
* @author wuhai
* create at 2016/2/17 16:19
*/
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView
        .ViewHolder> extends RecyclerView.Adapter<VH> {
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_FOOTER = 1;
    public static final int ITEM_TYPE_ITEM = 2;

    Context context;
    LayoutInflater layoutInflater;
    View headerView, footerView;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public BaseRecyclerViewAdapter setHeaderView(View headerView) {
        this.headerView = headerView;
        return this;
    }

    public BaseRecyclerViewAdapter setFooterView(View footerView) {
        this.footerView = footerView;
        return this;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 && headerView != null) return ITEM_TYPE_HEADER;
        if (position == getItemCount() && footerView != null) return ITEM_TYPE_FOOTER;
        return ITEM_TYPE_ITEM;
    }

    public abstract Object getEntityAt(int position);
}
