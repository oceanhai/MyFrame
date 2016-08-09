package com.myframe.www.widget.titlebar2;

import android.content.Context;
import android.util.AttributeSet;

import com.myframe.www.R;


/**
 * 带返回键的标题栏
 * Created by bailiangjin on 16/3/31.
 */
public class TitleViewWithBack extends BaseTitleView {
    public TitleViewWithBack(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getTitleTvSize() {
        return 18;
    }

    @Override
    protected int getBtnTvSize() {
        return 14;
    }

    @Override
    protected int getTitleTvColor() {
        return R.color.white;
    }

    @Override
    protected int getBtnTvColor() {
        return R.color.white;
    }

    @Override
    protected int getLeftImageResId() {
        return R.drawable.titlebar2_left_image;
    }

    @Override
    protected int getRightImageResId() {
        return R.drawable.titlebar2_right_image;
    }

    @Override
    protected int getTitleBackgroundResId() {
        return R.drawable.titlebar2_title_background;
    }

    @Override
    protected int getLeftBtnResId() {
        return 0;
    }

    @Override
    protected int getRightBtnResId() {
        return 0;
    }

    @Override
    protected boolean getLeftImgVisibility() {
        return true;
    }

    @Override
    protected boolean getRightImgVisibility() {
        return false;
    }

    @Override
    protected boolean getRightBtnVisibility() {
        return true;
    }

    @Override
    protected boolean getLeftBtnVisibility() {
        return false;
    }
}
