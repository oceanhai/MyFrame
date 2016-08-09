package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.myframe.www.base.BaseActivity;
import com.myframe.www.utils.ToastUtils;
import com.myframe.www.widget.titlebar1.TitleBar;
import com.myframe.www.widget.titlebar2.TitleViewListener;
import com.myframe.www.widget.titlebar2.TitleViewWithBack;
import com.myframe.www.widget.titlebar2.TitleViewWithCloseBtn;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TitleBarActivity extends BaseActivity {


    @Bind(R.id.titleview_with_back)
    TitleViewWithBack titleviewWithBack;
    @Bind(R.id.titleview_with_close)
    TitleViewWithCloseBtn titleviewWithClose;
    @Bind(R.id.titlebar)
    TitleBar titlebar;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TitleBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar);
        ButterKnife.bind(this);

        /**
         * 标题栏颜色
         * TODO 这个应该写在基类里(前提标题栏颜色一致)
         * TODO 如果不一致，可以把这个变成可控的，根据每个页面自行设置
         * TODO common_titlebar_height 高度根据是否是KITKAT选择不同高度
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        /**
         * titlebar1
         */
        titlebar.setTitle("titlebar");
        titlebar.setBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * titlebar2
         */
        titleviewWithBack.setTitleText("titleviewWithBack");
        titleviewWithBack.setRightBtnText("right");
        titleviewWithBack.setTitleViewListener(new TitleViewListener() {
            @Override
            public boolean onLeftImgClick() {
                return false;
            }

            @Override
            public boolean onRightImgClick() {
                return false;
            }

            @Override
            public boolean onLeftBtnClick() {
                return false;
            }

            @Override
            public boolean onRightBtnClick() {
                ToastUtils.showShort(TitleBarActivity.this, "点击右侧文字");
                return true;
            }
        });

        /**
         * titlebar2 扩展 左侧图片变
         */
        titleviewWithClose.setTitleText("titleviewWithClose");
        titleviewWithClose.setRightBtnText("right");
        titleviewWithClose.setTitleViewListener(new TitleViewListener() {
            @Override
            public boolean onLeftImgClick() {
                ToastUtils.showShort(TitleBarActivity.this, "弹出退出窗口");
                return true;
            }

            @Override
            public boolean onRightImgClick() {
                return false;
            }

            @Override
            public boolean onLeftBtnClick() {
                return false;
            }

            @Override
            public boolean onRightBtnClick() {
                ToastUtils.showShort(TitleBarActivity.this, "点击右侧文字");
                return true;
            }
        });

    }
}
