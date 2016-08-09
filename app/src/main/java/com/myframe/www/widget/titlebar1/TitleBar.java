package com.myframe.www.widget.titlebar1;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myframe.www.R;

/**
 *  wuhai
 *  自定义titleBar
 */
public class TitleBar extends FrameLayout {

	private TextView mTitleTextView;
	private ImageButton mBackButton;
	private String mTitleStr;
	
	private Drawable mBackButtonSrc;

	public TitleBar(Context context) {
		super(context);
		initView(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.TitleBar, defStyle, 0);

		String title = null;
		if (typedArray != null) {
			mTitleStr = typedArray.getString(R.styleable.TitleBar_titleText);//自定义属性titleText
			mBackButtonSrc=typedArray.getDrawable(R.styleable.TitleBar_backButtonSrc);
			typedArray.recycle();
		}
		initView(context);
	}

	/**
	 * 返回
	 * @param listener
	 */
	public void setBackOnClickListener(OnClickListener listener) {
		if (listener != null) {
			mBackButton.setOnClickListener(listener);
		}
	}

	/**
	 * 隐藏返回键
	 */
	public void hideBackButton(){
		mBackButton.setVisibility(GONE);
	}

	public void setBackButtonVisibility(int visibility) {
		mBackButton.setVisibility(visibility);
	}
	
	//BackButton是否可点击
	public void setBackButtonClickable(boolean enabled){
		mBackButton.setClickable(enabled);
	}

	public void setTitleColor(int color) {
		mTitleTextView.setTextColor(color);
	}

	public void setLeftView(View view) {
		if (view == null) {
			return;
		}

		mBackButton.setVisibility(GONE);
		ViewGroup container = (ViewGroup) findViewById(R.id.title_bar_layout_left);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		container.addView(view, layoutParams);
	}

	/**
	 * 
	 * @Title: setRightView 
	 * @说       明: 设置titleBar右图标
	 * @参       数: @param view   
	 * @return void    返回类型 
	 * @throws
	 */
	public void setRightView(View view) {
		if (view == null) {
			return;
		}

		ViewGroup container = (ViewGroup) findViewById(R.id.title_bar_layout_right);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		container.addView(view, layoutParams);
	}

	public void setCenterView(View view) {
		if (view == null) {
			return;
		}

		mTitleTextView.setVisibility(GONE);
		ViewGroup container = (ViewGroup) findViewById(R.id.title_bar_layout_center);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		container.addView(view, layoutParams);
	}

	/**
	 * 
	 * @Title: setTitle 
	 * @说       明: 手动设置标题（也可直接在XML里初始化titleText属性）
	 * @参       数: @param title   
	 * @return void    返回类型 
	 * @throws
	 */
	public void setTitle(String title) {
		if (title != null) {
			mTitleStr = title;
			mTitleTextView.setText(mTitleStr);
		}
	}
	
	/*
	 * 设置背景颜色
	 */
	public void setBackgroudResource(int resid){
		setBackgroundResource(resid);
	}

	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.widget_title_bar, this);

		setBackgroundResource(R.color.titlebar_bg);//标题背景色

		mTitleTextView = (TextView) findViewById(R.id.title_bar_text_view);

		mBackButton = (ImageButton) findViewById(R.id.title_bar_back_button);

		if (mTitleStr != null) {
			mTitleTextView.setText(mTitleStr);
		}
		
		if(mBackButtonSrc!=null){
			mBackButton.setImageDrawable(mBackButtonSrc);
		}
	}
}
