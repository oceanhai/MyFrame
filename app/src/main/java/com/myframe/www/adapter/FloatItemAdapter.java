package com.myframe.www.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myframe.www.R;

/**
 * @author: wuhai
 * @类   说   明:	
 * @version 1.0
 * @创建时间：2015-10-23 下午6:17:09
 * 
 */
public class FloatItemAdapter extends BaseAdapter {
	private ArrayList<String> mData;
	private Context mContext;

	public FloatItemAdapter(Context context){
		this.mContext=context;
	}
	
	public void setData(ArrayList<String> data){
		this.mData=data;
	}
	
	@Override
	public int getCount() {
		return mData.size()==0?0:mData.size();
	}
	
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_floatview2_activity, parent, false);
		}
		TextView tv_tilte=(TextView) convertView.findViewById(R.id.tv_title);
		tv_tilte.setText(mData.get(position));
		return convertView;
	}



}
