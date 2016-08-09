package com.myframe.www.widget.slidingfinish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.slidingfinish.view.SwipeBackLayout;

/**
 * 想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager
 * 只需要调用SwipeBackLayout的setViewPager()方法即可----------------没有setViewPager()，不用也行
 * 
 * @author xiaanming
 * 
 */
public class SwipeBackActivity extends BaseActivity {
	protected SwipeBackLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//TODO 做个抽象基类 设置一个属性来判断此Activity是否实现侧滑销毁效果
		if(canSlidingFinish()){
			layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
					R.layout.layout_swipeback_base, null);
			layout.attachToActivity(this);
		}
	}

	/**
	 * 侧滑开关
	 * @return
	 */
	public boolean canSlidingFinish(){
		return false;
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
