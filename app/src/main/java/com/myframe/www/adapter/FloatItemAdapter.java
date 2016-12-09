package com.myframe.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myframe.www.R;

import java.util.ArrayList;

import www.wuhai.common.utils.L;

/**
 * @author: wuhai
 * @类   说   明:	
 * @version 1.0
 * @创建时间：2015-10-23 下午6:17:09
 * 
 */
public class FloatItemAdapter extends BaseAdapter {
	private static final String TAG = "BaseAdapter";

	private ArrayList<String> mData;
	private Context mContext;
	private int num = 0;

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
		ViewHolder viewHolder = null;
		if(convertView==null){
			convertView=LayoutInflater.from(mContext).inflate(R.layout.item_floatview2_activity, parent, false);
			//TODO 这里查看复用 没意义，因为listview重写了
			L.e(TAG,"convertView="+convertView+",num="+(++num));
			viewHolder = new ViewHolder();
			viewHolder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mTvTitle.setText(mData.get(position));
		return convertView;
	}

	static class ViewHolder{
		private TextView mTvTitle;
	}

}
