package com.myframe.www.widget.inputwidget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.myframe.www.R;

import java.lang.ref.WeakReference;


public class InputWidget extends FrameLayout{

	private EditText mEdit;//输入框
	private TextView mIdentifyingCodeButton;//"获得验证码"
	private ImageView mClearButton;//叉子图标
	private boolean mClearButtonEnable;//是否有销毁按钮 true:有 false:无 
	private ImageView mImageViewLeft;//左图标控件
	private ImageView mImageViewRight;//右图标控件
	private Drawable drawableLeft;//左图标
	private Drawable drawableRightOnly;//右图标
	private Drawable drawableRightOpen;//显示字符模式（图标）
	private Drawable drawableRightClose;//隐藏字符模式（图标）
	private Drawable drawableBackGround;//控件整体背景
	private String hintString;//editText提示语
	private int editLength;//输入框最大输入字符
	private String rightTextStr;//textView内容
	private boolean passwordType;//输入密码框
	private int index;//enum imeOptions
	private String digits;//edit 可输入字符
	
	private boolean openOrClose=true;//true 闭眼
	private WeakReference<InputWidgetActionHandler> mActionHandler;//接口回调，让外围实现功能（请求验证码）（若引用是为了防止OOM）
	private WeakReference<InputWidgetEditTextChanged> mChanged;

	public InputWidget(Context context) {
		super(context);
		initView(context);
	}

	public InputWidget(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public InputWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputWidget,defStyle, 0);
		if(typedArray!=null){
			//背景颜色
			drawableBackGround = typedArray.getDrawable(R.styleable.InputWidget_inputbackground);
			//输入框 清除图标
			mClearButtonEnable = typedArray.getBoolean(R.styleable.InputWidget_clearbuttonenable, false);
			//左边图片
			drawableLeft = typedArray.getDrawable(R.styleable.InputWidget_imageviewleft);
			//右边图片
			drawableRightOnly = typedArray.getDrawable(R.styleable.InputWidget_imageviewright);
			//passwordtype=true时候，imageviewright不起作用 优先级
			//passwordtype=true时候 imageviewrightopen和imageviewrightclose要有对应图片
			passwordType=typedArray.getBoolean(R.styleable.InputWidget_passwordtype, false);
			drawableRightOpen = typedArray.getDrawable(R.styleable.InputWidget_imageviewrightopen);
			drawableRightClose = typedArray.getDrawable(R.styleable.InputWidget_imageviewrightclose);
			//提示语
			hintString=typedArray.getString(R.styleable.InputWidget_edithintstr);
			//获取验证码|获取动态密码 等
			rightTextStr=typedArray.getString(R.styleable.InputWidget_righttextstr);
			//输入框长度
			editLength=typedArray.getInteger(R.styleable.InputWidget_editlength, 30);
			//软键盘回车键 0 NEXT;1 DONE
			index=typedArray.getInt(R.styleable.InputWidget_imeOptions, -1);//enum
			//可输入字符样式
			digits=typedArray.getString(R.styleable.InputWidget_digits);
			typedArray.recycle();
		}
		
		initView(context);
	}

	/**
	 * 获取验证码|获取动态密码 等显示
	 * @param enabled
	 */
	public void setIdentifyingCodeEnable(boolean enabled) {
		if (enabled) {
			mIdentifyingCodeButton.setVisibility(VISIBLE);
		} else {
			mIdentifyingCodeButton.setVisibility(GONE);
		}

	}

	/**
	 * 设置背景 图片
	 * @param background
	 */
	public void setSearchBarBackground(Drawable background) {
		View view = findViewById(R.id.input_edit_layout);
		view.setBackgroundDrawable(background);
	}

	/**
	 * 设置背景 颜色
	 * @param color
	 */
	public void setSearchBarBackground(int color) {
		View view = findViewById(R.id.input_edit_layout);
		view.setBackgroundColor(color);
	}

	/**
	 * 设置输入框提示字体颜色
	 * @param color
	 */
	public void setHintTextColor(int color) {
		mEdit.setHintTextColor(color);
	}

	/**
	 * 设置输入框提示信息
	 * @param str
	 */
	public void setHintText(String str) {
		mEdit.setHint(str);
	}	
	
	/**
	 * 设置输入框字体颜色
	 * @param color
	 */
	public void setEditTextColor(int color) {
		mEdit.setTextColor(color);
	}

	/**
	 * 设置输入框内容
	 * @param text
	 */
	public void setContent(String text) {
		mEdit.setText(text);
		mEdit.setSelection(text.length());//让光标移到末端（这样文字就会向前显示）
	}

	/**
	 * 获取editText view
	 * @return
	 */
	public EditText getEditText(){
		return mEdit;
	}

	/**
	 * 获取editText内容
	 * @return
	 */
	public String getContent(){
		return mEdit.getText().toString().trim();
	}
	
	/**
	 * editText是否可点击
	 * @param enabled
	 */
	public void setEditTextClickable(boolean enabled){
		mEdit.setClickable(enabled);
	}

	/**
	 * 设置验证码btn 字体颜色
	 * @param color
	 */
	public void setButtonTextColor(int color) {
		mIdentifyingCodeButton.setTextColor(color);
	}
	
	/**
	 * 获取验证码/获取动态密码  view
	 * @return
	 */
	public TextView getButtonText(){
		return mIdentifyingCodeButton;
	}

	/**
	 * 设置验证码btn 内容
	 * @param id
	 */
	public void setButtonText(int id){
		mIdentifyingCodeButton.setText(id);
	}
	
	/**
	 * 隐藏软键盘
	 */
	public void hindSoftInput() {
		InputMethodManager inputManger = (InputMethodManager) getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManger.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
	}
	

	/**
	 * 代码设置 右图标显隐
	 * @param enable
	 */
	public void setIconRightEnable(boolean enable){
		if(enable){
			mImageViewRight.setVisibility(VISIBLE);
		}else {
			mImageViewRight.setVisibility(GONE);
		}
	}

	/**
	 * 为右图标设置图片(非输入密码框)
	 * @param drawable
	 */
	public void setImageViewRight(Drawable drawable){
		if(!passwordType){
			mImageViewRight.setVisibility(View.VISIBLE);
			mImageViewRight.setImageDrawable(drawable);
		}
	}
	
	/**
	 * 接口回调，让外围实现点击"获取验证码"/"获取动态密码"操作（弱引用防止OOM）
	 * @param handler
	 */
	public void setInputWidgetActionHandler(InputWidgetActionHandler handler){
		this.mActionHandler=new WeakReference<InputWidgetActionHandler>(handler);
	}
	
	/**
	 * 接口回调，让外围监听EditText变化
	 * @param changed
	 */
	public void setInputWidgetEditTextChanged(InputWidgetEditTextChanged changed){
		this.mChanged = new WeakReference<InputWidgetEditTextChanged>(changed);
	}
	
	/**
	 * 
	 * @Title: initView 
	 * @说       明: 初始化
	 * @参       数: @param context   
	 * @return void    返回类型 
	 * @throws
	 */
	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.widget_input_layout, this);
		
		mImageViewLeft = (ImageView) findViewById(R.id.imageview_left);
		mImageViewRight=(ImageView) findViewById(R.id.input_imageview_right);
		mEdit = (EditText) findViewById(R.id.input_edit_text);
		mIdentifyingCodeButton = (TextView) findViewById(R.id.input_identifyingcode_button);
//		mIdentifyingCodeButton.setEnabled(false);//默认不可点击
		mClearButton = (ImageView) findViewById(R.id.input_clear_button);

		if(drawableBackGround!=null){
			View view = findViewById(R.id.input_edit_layout);
			view.setBackgroundDrawable(drawableBackGround);
		}

		if (drawableLeft!=null){
			mImageViewLeft.setImageDrawable(drawableLeft);
		}else {
			mImageViewLeft.setVisibility(GONE);
		}

		if (hintString!=null){
			mEdit.setHint(hintString);
		}

		if (editLength!=30){
			mEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editLength)});//设置输入框最大输入字符数
		}
		
		if(index>=0){//输入法软键盘右下角键
			if(index==0){
				mEdit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
			}else {
				mEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
			}
		}
		
		if(digits!=null){//可输入字符样式
			mEdit.setKeyListener(DigitsKeyListener.getInstance(digits));
		}

		if (rightTextStr!=null){
			mIdentifyingCodeButton.setVisibility(View.VISIBLE);
			mIdentifyingCodeButton.setText(rightTextStr);
		}
		
		if(drawableRightOnly != null){
			if(!passwordType){
				mImageViewRight.setVisibility(VISIBLE);
				mImageViewRight.setImageDrawable(drawableRightOnly);//只是有右图标的输入框
			}
		}
		
		//是否是"输入密码"框
		if (passwordType) {
			mEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
			mImageViewRight.setVisibility(VISIBLE);
			mImageViewRight.setImageDrawable(drawableRightClose);
		}
		
		//右图标点击(仅当输入密码框时)
		mImageViewRight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(passwordType){
					if(openOrClose){
						mEdit.setTransformationMethod(null);
						mImageViewRight.setImageDrawable(drawableRightOpen);
						openOrClose=false;
					}else {
						mEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
						mImageViewRight.setImageDrawable(drawableRightClose);
						openOrClose=true;
					}
					mEdit.setSelection(mEdit.getText().toString().length());//让光标移到末端（这样文字就会向前显示）
				}
			}
		});
		
		//获取验证码，点击事件
		mIdentifyingCodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			InputMethodManager inputManger = (InputMethodManager) getContext()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManger.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);

			if(mActionHandler!=null){
				InputWidgetActionHandler handler=mActionHandler.get();
				if(handler!=null){
					handler.onGetIdentifyingCode();//外围实现操作
				}
			}
			}
		});

		//edit内容消除
		mClearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mEdit.setText("");
			}
		});

		//edit内容变化监听
		mEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(mChanged!=null){
					InputWidgetEditTextChanged changed=mChanged.get();
					if(changed!=null){
						changed.onEditTextChanged(s);
					}
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

				if(mClearButtonEnable){
					if (s.length() > 0 ) {//长度>0
						mClearButton.setVisibility(VISIBLE);
					} else {
						mClearButton.setVisibility(GONE);
					}
				}
			}
		});
		
		//edit焦点变化监听
		mEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					if(mEdit.getText().toString().length()>0){
						mClearButton.setVisibility(VISIBLE);
					}
				}else {
					mClearButton.setVisibility(GONE);
				}
			}
		});
	}

	/**
	 * 接口监听事件("获取动态密码"等)
	 */
	public interface InputWidgetActionHandler {
		void onGetIdentifyingCode();
	}

	/**
	 * editText输入内容变化监听
	 */
	public interface InputWidgetEditTextChanged {
		void onEditTextChanged(CharSequence s);
	}
}
