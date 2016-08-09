package com.myframe.www.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.widget.wheel.adapters.AbstractWheelTextAdapter;
import com.myframe.www.widget.wheel.views.OnWheelChangedListener;
import com.myframe.www.widget.wheel.views.OnWheelScrollListener;
import com.myframe.www.widget.wheel.views.WheelView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wuhai
 * 地区 滚动选择
 */
public class SelectRegionDialog extends Dialog implements View.OnClickListener {

	private WheelView wvProvinces;
	private WheelView wvCitys;
	private TextView tvHint;
	private TextView btnComplete;
	private View lyBg;
	private View lyBgChild;

	private Context context;

	private ArrayList<String> arrDatas = new ArrayList<String>();// 一级数据对应的二级数据体集合
	private TextAdapter provinceAdapter;
	private TextAdapter cityAdapter;

	private String strProvince = "北京";
	private String strCity = "北京";
	private OnGetAgeListener onGetAgeListener;//回调接口

	private int maxsize = 24;
	private int minsize = 14;

	public SelectRegionDialog(Context context) {
		super(context, R.style.SelectAgeDialogStyle);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_question_select_age);

		tvHint = (TextView) findViewById(R.id.tv_hint);//提示语
		wvCitys = (WheelView) findViewById(R.id.wv_type);//右侧 WheelView
		wvProvinces = (WheelView) findViewById(R.id.wv_data);//左侧 WheelView
		btnComplete = (TextView) findViewById(R.id.btn_complete);//完成
		lyBg =  findViewById(R.id.ly_bg);//周边黑色区域  点击dismiss
		lyBgChild =  findViewById(R.id.ly_bg_child);//dialog框 点击return

		btnComplete.setOnClickListener(this);
		lyBg.setOnClickListener(this);
		lyBgChild.setOnClickListener(this);

		tvHint.setText("选择城市");

		initData();
		provinceAdapter = new TextAdapter(context, proNameList, getTypeItem(strProvince), maxsize, minsize);
		wvProvinces.setVisibleItems(5);
		wvProvinces.setViewAdapter(provinceAdapter);
		wvProvinces.setCurrentItem(getTypeItem(strProvince));

		initDatas(proCitysMap.get(strProvince));
		cityAdapter = new TextAdapter(context, arrDatas, getDataItem(strCity), maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(getDataItem(strCity));

		wvProvinces.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				strProvince = currentText;
				setTextviewSize(currentText, provinceAdapter);//字体放大
				ArrayList<String> datas = proCitysMap.get(currentText);
				initDatas(datas);//重新初始化type关联 datas
				cityAdapter = new TextAdapter(context, arrDatas, 0, maxsize, minsize);
				wvCitys.setVisibleItems(5);
				wvCitys.setViewAdapter(cityAdapter);
				wvCitys.setCurrentItem(0);
			}
		});

		wvProvinces.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				strCity = currentText;
				setTextviewSize(currentText, cityAdapter);
			}
		});

		wvCitys.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
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

	/**
	 * 回传信息用
	 * @param onGetAgeListener
	 */
	public void setOnGetAgeListener(OnGetAgeListener onGetAgeListener) {
		this.onGetAgeListener = onGetAgeListener;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_complete:

				if (onGetAgeListener != null) {
					onGetAgeListener.onGetAge(strProvince, strCity);
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
			this.strProvince = type;
		}
		if (data != null && data.length() > 0) {
			this.strCity = data;
		}
	}

	private ArrayList<String> proNameList, cityNameList;
	private ArrayList<ArrayList<String>> cityNameLists;
	private Map<String, ArrayList<String>> proCitysMap = new HashMap<String, ArrayList<String>>();

	/**
	 * 初始化数据
	 */
	private void initData() {
		XmlResourceParser parser = context.getResources().getXml(R.xml.citys_weather);
		try {
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName = parser.getName();
				switch (eventType) {
					case XmlPullParser.START_DOCUMENT:
						proNameList = new ArrayList<String>();
						cityNameLists = new ArrayList<>();
						break;
					case XmlPullParser.START_TAG:
						if ("p".equals(nodeName)) {
							//每次P标签 创建一个新的
							cityNameList = new ArrayList<String>();

							// 获得p节点的第一个属性值
//							String id = parser.getAttributeValue(0);
							parser.next();
							proNameList.add(parser.nextText());
						}else if("c".equals(nodeName)){
							parser.next();
							cityNameList.add(parser.nextText());
						}
						break;
					case XmlPullParser.END_TAG:
						//p标签结束 把城市name集合add
						if("p".equals(nodeName)){
							cityNameLists.add(cityNameList);
						}
						break;
					default:
						break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 生成二级数据 所有集合的 大集合体
		 */
		for(int x=0; x<proNameList.size(); x++){
			proCitysMap.put(proNameList.get(x), cityNameLists.get(x));
		}

	}


	/**
	 * 根据type,生成数据datas
	 * 
	 */
	public void initDatas(ArrayList<String> datas) {
		if (datas != null) {
			arrDatas.clear();
			int length = datas.size();
			for (int i = 0; i < length; i++) {
				arrDatas.add(datas.get(i));
			}
		}

		if (arrDatas != null && arrDatas.size() > 0 && !arrDatas.contains(strCity) ) {
			strCity = arrDatas.get(0);
		}
	}

	/**
	 * 返回type索引
	 * 取不到返回 0：北京
	 */
	public int getTypeItem(String type) {
		int size = proNameList.size();
		int provinceIndex = 0;
		boolean notype = true;
		for (int i = 0; i < size; i++) {
			if (type.equals(proNameList.get(i))) {
				notype = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (notype) {
			strProvince = "北京";
			return 0;
		}
		return provinceIndex;
	}

	/**
	 * 得到data索引
	 * 没有返回默认0:“北京”
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
			strCity = "北京";
			return 0;
		}
		return dataIndex;
	}

}