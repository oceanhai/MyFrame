package com.myframe.www.widget.slidingfinish;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.myframe.www.R;
import com.myframe.www.widget.slidingfinish.view.SwipeBackLayout;

/**
 * 
 * @ClassName: SwipeBackFragmentActivity
 * @说明:用于Fragment使用
 * @author 吴海
 * @date 2014-11-27 下午1:35:02
 */

public class SwipeBackFragmentActivity extends FragmentActivity {
	protected SwipeBackLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
				R.layout.layout_swipeback_base, null);
		layout.attachToActivity(this);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.swipeback_base_slide_right_in,
				R.anim.swipeback_base_slide_remain);
	}

	// Press the back button in mobile phone
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.swipeback_base_slide_right_out);
	}

}
