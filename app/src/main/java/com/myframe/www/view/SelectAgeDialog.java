package com.myframe.www.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.myframe.www.R;
import com.myframe.www.widget.wheel.adapters.AbstractWheelTextAdapter;
import com.myframe.www.widget.wheel.views.OnWheelChangedListener;
import com.myframe.www.widget.wheel.views.OnWheelScrollListener;
import com.myframe.www.widget.wheel.views.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * wuhai
 * 天/月/岁  年龄选择
 */
public class SelectAgeDialog extends Dialog implements View.OnClickListener {

	private WheelView wvData;
	private WheelView wvType;
	private TextView btnComplete;
	private View lyBg;
	private View lyBgChild;

	private Context context;
	private String[] mTypeDatas = {"天", "月", "岁"};
	private Map<String, String[]> mTypesDatasMap = new HashMap<String, String[]>();

	private ArrayList<String> arrTypes = new ArrayList<String>();
	private ArrayList<String> arrDatas = new ArrayList<String>();
	private TextAdapter typeAdapter;
	private TextAdapter dataAdapter;

	private String strType = "天";
	private String strData = "1";
	private OnGetAgeListener onGetAgeListener;//回调接口

	private int maxsize = 24;
	private int minsize = 14;

	public SelectAgeDialog(Context context) {
		super(context, R.style.SelectAgeDialogStyle);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_question_select_age);

		wvType = (WheelView) findViewById(R.id.wv_type);//"天/月/岁"
		wvData = (WheelView) findViewById(R.id.wv_data);//数据
		btnComplete = (TextView) findViewById(R.id.btn_complete);//完成
		lyBg =  findViewById(R.id.ly_bg);//周边黑色区域  点击dismiss
		lyBgChild =  findViewById(R.id.ly_bg_child);//dialog框 点击return

		btnComplete.setOnClickListener(this);
		lyBg.setOnClickListener(this);
		lyBgChild.setOnClickListener(this);

		initMap();
		initTypes();
		typeAdapter = new TextAdapter(context, arrTypes, getTypeItem(strType), maxsize, minsize);
		wvType.setVisibleItems(5);
		wvType.setViewAdapter(typeAdapter);
		wvType.setCurrentItem(getTypeItem(strType));

		initDatas(mTypesDatasMap.get(strType));
		dataAdapter = new TextAdapter(context, arrDatas, getDataItem(strData), maxsize, minsize);
		wvData.setVisibleItems(5);
		wvData.setViewAdapter(dataAdapter);
		wvData.setCurrentItem(getDataItem(strData));

		wvType.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) typeAdapter.getItemText(wheel.getCurrentItem());
				strType = currentText;
				setTextviewSize(currentText, typeAdapter);//字体放大
				String[] datas = mTypesDatasMap.get(currentText);
				initDatas(datas);//重新初始化type关联 datas
				dataAdapter = new TextAdapter(context, arrDatas, 0, maxsize, minsize);
				wvData.setVisibleItems(5);
				wvData.setViewAdapter(dataAdapter);
				wvData.setCurrentItem(0);
			}
		});

		wvType.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) typeAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, typeAdapter);
			}
		});

		wvData.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) dataAdapter.getItemText(wheel.getCurrentItem());
				strData = currentText;
				setTextviewSize(currentText, dataAdapter);
			}
		});

		wvData.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) dataAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, dataAdapter);
			}
		});
	}

	private class TextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected TextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_wheelview, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, TextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	public void setOnGetAgeListener(OnGetAgeListener onGetAgeListener) {
		this.onGetAgeListener = onGetAgeListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_complete:

				if (onGetAgeListener != null) {
					onGetAgeListener.onGetAge(strType, strData);
				}
				dismiss();
				break;
			case R.id.ly_bg:
				dismiss();
				break;
			case R.id.ly_bg_child:
				break;
		}
	}

	/**
	 * 回调接口
	 * 
	 */
	public interface OnGetAgeListener {
		void onGetAge(String type, String data);
	}


	/**
	 * 初始化位置
	 *
	 */
	public void setPosition(String type, String data) {
		if (type != null && type.length() > 0) {
			this.strType = type;
		}
		if (data != null && data.length() > 0) {
			this.strData = data;
		}
	}

	/**
	 * 初始化数据
	 */
	private void initMap() {
		for(int x=0; x<mTypeDatas.length; x++){
			if(x==0){//天
				String[] mDatas = new String[100];
				for(int y=0;y<100;y++){
					mDatas[y] =""+(y+1);
				}
				mTypesDatasMap.put(mTypeDatas[x],mDatas);
			}

			if(x==1){//月
				String[] mDatas = new String[12];
				for(int y=0;y<12;y++){
					mDatas[y] =""+(y+1);
				}
				mTypesDatasMap.put(mTypeDatas[x],mDatas);
			}

			if(x==2){//岁
				String[] mDatas = new String[120];
				for(int y=0;y<120;y++){
					mDatas[y] =""+(y+1);
				}
				mTypesDatasMap.put(mTypeDatas[x],mDatas);
			}
		}
	}

	/**
	 * 初始types
	 */
	public void initTypes() {
		int length = mTypeDatas.length;
		for (int i = 0; i < length; i++) {
			arrTypes.add(mTypeDatas[i]);
		}
	}

	/**
	 * 根据type,生成数据datas
	 * 
	 */
	public void initDatas(String[] datas) {
		if (datas != null) {
			arrDatas.clear();
			int length = datas.length;
			for (int i = 0; i < length; i++) {
				arrDatas.add(datas[i]);
			}
		} 
		if (arrDatas != null && arrDatas.size() > 0 && !arrDatas.contains(strData) ) {
			strData = arrDatas.get(0);
		}
	}

	/**
	 * 返回type索引
	 * 取不到返回 0：天
	 */
	public int getTypeItem(String type) {
		int size = arrTypes.size();
		int provinceIndex = 0;
		boolean notype = true;
		for (int i = 0; i < size; i++) {
			if (type.equals(arrTypes.get(i))) {
				notype = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (notype) {
			strType = "天";
			return 0;
		}
		return provinceIndex;
	}

	/**
	 * 得到data索引
	 * 没有返回默认0:“1”
	 * 
	 */
	public int getDataItem(String data) {
		int size = arrDatas.size();
		int dataIndex = 0;
		boolean nodata = true;
		for (int i = 0; i < size; i++) {
			System.out.println("getDataItem"+arrDatas.get(i));
			if (data.equals(arrDatas.get(i))) {
				nodata = false;
				return dataIndex;
			} else {
				dataIndex++;
			}
		}
		if (nodata) {
			strData = "1";
			return 0;
		}
		return dataIndex;
	}

}