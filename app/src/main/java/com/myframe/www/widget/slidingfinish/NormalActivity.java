package com.myframe.www.widget.slidingfinish;

import android.os.Bundle;

import com.myframe.www.R;

public class NormalActivity extends SwipeBackActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);
	}

	@Override
	public boolean canSlidingFinish() {
		return true;
	}
}
