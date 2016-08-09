package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;

/**
* 关于
* @author wuhai
* create at 2016/6/3 16:10
*/
public class AboutActivity  extends BaseActivity{


	public static void startActivity(Context context){
		Intent intent = new Intent(context, AboutActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.book_review_activity_in, R.anim.book_review_group_activity_out);
		setContentView(R.layout.about_activity);
		initUi();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			dealBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void initUi() {
		initTitleView();
		initLeftView();
		initVersionView();
		initWebView();
	}

	private void initTitleView() {
		((TextView) findViewById(R.id.book_center_title)).setText("关于");
	}

	private void initLeftView() {
		findViewById(R.id.btn_action_bar_left).setOnClickListener(mClickListener);
	}

	private void initVersionView() {
		((TextView)findViewById(R.id.about_version)).setText(getVersion());
	}
	
	private void initWebView() {
		WebView webView= (WebView) findViewById(R.id.about_content);
		webView.loadUrl("file:///android_asset/newabout.html");
		webView.setHorizontalScrollBarEnabled(false);
		webView.setHorizontalScrollbarOverlay(false);
		webView.setOnLongClickListener(new WebView.OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
	}
	
	final OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_action_bar_left:
				dealBack();
				break;
			}
		}
	};
	
	private void dealBack() {
		finish();
		overridePendingTransition(R.anim.book_review_group_activity_in, R.anim.book_review_activity_out);
	}
	
	private String getVersion(){
		String clientVersionNo = "";
		try {
			PackageManager pkgm = getPackageManager();
			final String pkgName = getPackageName();
			clientVersionNo = "V" + pkgm.getPackageInfo(pkgName, 0).versionName;
		} catch (Exception e) {
		}
		return clientVersionNo;
	}
	
}
