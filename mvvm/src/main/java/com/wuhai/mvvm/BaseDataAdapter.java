package com.wuhai.mvvm;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wuhai on 2015/11/18.
 * BaseAdapter基类 针对listview gridview
 * 直接继承即可
 */
public abstract class BaseDataAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;

    public BaseDataAdapter(Context context) {
        mContext = context;
    }

    public List<T> getData(){
        return mData;
    }

    /**
     * 初始数据
     * @param data
     */
    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * 加载用
     * @param data
     */
    public void addData(List<T> data) {
        if (mData != null) {
            mData.addAll(data);
        } else {
            mData = data;
        }
        notifyDataSetChanged();
    }

    //list下坐标0开始，第三方各种ListView的postion从1开始
    public void deleteData(int position){
        if(position>0 && position<=mData.size()){
            mData.remove(position-1);
        }
    }

    public void deleteDataALL(){
        mData.clear();
    }

    /**
     * 首位置追加一条数据
     * @param data
     */
    public void insertData(T data){
        if(mData != null){
            mData.add(0,data);
        }
    }

    public void insertData(List<T> data) {
        if (mData != null) {
            mData.addAll(0, data);
        } else {
            mData = data;
        }
    }

    @Override
    public int getCount() {
        return (mData != null) ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (mData != null) ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getDataItem(int position) {
        return (mData != null) ? mData.get(position) : null;
    }
}
