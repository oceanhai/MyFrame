package com.myframe.www.widget.titlebar2;

import android.content.Context;
import android.util.AttributeSet;

import com.myframe.www.R;


/**
 * 左侧按钮为关闭的 title 只有左侧img图标与 返回键title 不同 只需要Override getLeftImageResId 方法 其他与TitleViewWithBack一致
 * Created by bailiangjin on 16/4/1.
 */
public class TitleViewWithCloseBtn extends TitleViewWithBack {
    public TitleViewWithCloseBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLeftImageResId() {
        return R.drawable.titlebar2_left_image_close;
    }
}
