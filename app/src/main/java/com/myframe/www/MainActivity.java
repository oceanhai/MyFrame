package com.myframe.www;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.igexin.sdk.PushManager;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.testdb.DBActivity;
import com.myframe.www.testhandler.HandlerActivity;
import com.myframe.www.testokhttp.OkHttpActivity;
import com.myframe.www.teststartactivity.ThreadStartActivity;
import com.myframe.www.teststartactivityforresult.FirstActivity;
import com.myframe.www.testtouchevent.TouchEventActivity;
import com.myframe.www.widget.calendar.CalendarActivity;
import com.myframe.www.widget.customview.CustomviewActivity;
import com.myframe.www.widget.draggridview.DragGridViewActivity;
import com.myframe.www.widget.slidingfinish.SlidingFinishActivity;
import com.myframe.www.widget.stickyheaderlistview.ui.StickyHeaderListView;
import com.myframe.www.widget.swtichbutton.MovableCheckboxActivity;
import com.myframe.www.yxt01.RetailSalerBrandPageActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.btn03)
    Button btn03;
    @Bind(R.id.btn04)
    Button btn04;
    @Bind(R.id.btn05)
    Button btn05;
    @Bind(R.id.btn06)
    Button btn06;
    @Bind(R.id.btn07)
    Button btn07;
    @Bind(R.id.btn08)
    Button btn08;
    @Bind(R.id.btn09)
    Button btn09;
    @Bind(R.id.btn10)
    Button btn10;
    @Bind(R.id.btn11)
    Button btn11;
    @Bind(R.id.btn12)
    Button btn12;
    @Bind(R.id.btn13)
    Button btn13;
    @Bind(R.id.btn14)
    Button btn14;
    @Bind(R.id.btn15)
    Button btn15;
    @Bind(R.id.btn16)
    Button btn16;
    @Bind(R.id.btn17)
    Button btn17;
    @Bind(R.id.btn18)
    Button btn18;
    @Bind(R.id.btn19)
    Button btn19;
    @Bind(R.id.btn20)
    Button btn20;
    @Bind(R.id.btn21)
    Button btn21;
    @Bind(R.id.btn22)
    Button btn22;
    @Bind(R.id.btn23)
    Button btn23;
    @Bind(R.id.btn24)
    Button btn24;
    @Bind(R.id.btn25)
    Button btn25;
    @Bind(R.id.btn26)
    Button btn26;
    @Bind(R.id.btn27)
    Button btn27;
    @Bind(R.id.btn28)
    Button btn28;
    @Bind(R.id.btn29)
    Button btn29;
    @Bind(R.id.btn30)
    Button btn30;
    @Bind(R.id.btn31)
    Button btn31;
    @Bind(R.id.btn32)
    Button btn32;
    @Bind(R.id.btn33)
    Button btn33;
    @Bind(R.id.btn34)
    Button btn34;
    @Bind(R.id.btn35)
    Button btn35;
    @Bind(R.id.btn36)
    Button btn36;
    @Bind(R.id.btn37)
    Button btn37;
    @Bind(R.id.btn38)
    Button btn38;
    @Bind(R.id.btn39)
    Button btn39;
    @Bind(R.id.btn40)
    Button btn40;
    @Bind(R.id.btn41)
    Button btn41;
    @Bind(R.id.btn42)
    Button btn42;
    @Bind(R.id.btn43)
    Button btn43;

    private String parameter;//TODO 这个以后应该是个json串，目前就简单用string代替

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /**
         * 初始化 个推
         */
        PushManager.getInstance().initialize(getApplicationContext());

        pareIntent();
        initListener();
        goToActivity();

    }

    private void initListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
        btn08.setOnClickListener(this);
        btn09.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
        btn24.setOnClickListener(this);
        btn25.setOnClickListener(this);
        btn26.setOnClickListener(this);
        btn27.setOnClickListener(this);
        btn28.setOnClickListener(this);
        btn29.setOnClickListener(this);
        btn30.setOnClickListener(this);
        btn31.setOnClickListener(this);
        btn32.setOnClickListener(this);
        btn33.setOnClickListener(this);
        btn34.setOnClickListener(this);
        btn35.setOnClickListener(this);
        btn36.setOnClickListener(this);
        btn37.setOnClickListener(this);
        btn38.setOnClickListener(this);
        btn39.setOnClickListener(this);
        btn40.setOnClickListener(this);
        btn41.setOnClickListener(this);
        btn42.setOnClickListener(this);
        btn43.setOnClickListener(this);
    }

    private void pareIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            parameter = intent.getStringExtra("parameter");
        }
    }

    private void goToActivity() {
        if (!TextUtils.isEmpty(parameter)) {
            if ("0".equals(parameter)) {
                //TODO 传递参数（复杂的以后自行追加）
                ShowEdittextActivity.startActivity(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn01://可根据选项自动换行的单选/多选/排他选
                intent.setClass(this, AutoWrapActivity.class);
                startActivity(intent);
                break;
            case R.id.btn02://轮播图
                intent.setClass(this, BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn03://Recyclerview
                intent.setClass(this, RecyclerviewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn04://软键盘显隐
                SoftKeyboardActivity.startActivity(this);
                break;
            case R.id.btn05://自定义布局的toast
                ToastUtils.showCenter(this, "关注成功", R.drawable.public_right_white);
                break;
            case R.id.btn06://浮动view
                FloatViewActivity.startActivity(this);
                break;
            case R.id.btn07://侧滑销毁activity
                SlidingFinishActivity.startActivity(this);
                break;
            case R.id.btn08://个推
                ToastUtils.showShort(this, "请在个推后天发送信息");
                break;
            case R.id.btn09://下拉刷新上拉加载
                PullToRefreshActivity.startActivity(this);
                break;
            case R.id.btn10://打印机效果
                PrinterTextviewActivity.startActivity(this);
                break;
            case R.id.btn11://titlebar
                TitleBarActivity.startActivity(this);
                break;
            case R.id.btn12://导航菜单
                NavigationMenuActivity.startActivity(this);
                break;
            case R.id.btn13://有一个输入框的界面
                ShowEdittextActivity.startActivity(this);
                break;
            case R.id.btn14://wheelview
                WheelViewActivity.startActivity(this);
                break;
            case R.id.btn15://ColourImageView
                ColourImageViewActivity.startActivity(this);
                break;
            case R.id.btn16://日历
                CalendarActivity.startActivity(this);
                break;
            case R.id.btn17://webview
                WebViewActivity.startActivity(this);
                break;
            case R.id.btn18://可拖动的控件
                CanDragViewActivity.startActivity(this);
                break;
            case R.id.btn19://StickyHeaderListView
                StickyHeaderListView.startActivity(this);
                break;
            case R.id.btn20://动画
                AnimActivity.startActivity(this);
                break;
            case R.id.btn21://RoundProgressBar
                RoundProgressBarActivity.startActivity(this);
                break;
            case R.id.btn22://DragGridView
                DragGridViewActivity.startActivity(this);
                break;
            case R.id.btn23://Menu
                MenuActivity.startActivity(this);
                break;
            case R.id.btn24://Scrollview_textview
                ScrollviewTextViewActivity.startActivity(this);
                break;
            case R.id.btn25://ListView 侧滑销毁item
                SwipemenulistviewActivity.startActivity(this);
                break;
            case R.id.btn26://当当读书 书架上滑控件
                CustomviewActivity.startActivity(this);
                break;
            case R.id.btn27://错误提示 titlebar背后弹出并缩回
                ShowHintinfoActivity.startActivity(this);
                break;
            case R.id.btn28://展示-收起 textview
                ShowMoreInfoActivity.startActivity(this);
                break;
            case R.id.btn29://自定义view（集合）
                CustomViewActivity.startActivity(this);
                break;
            case R.id.btn30://引导蒙层
                GuideViewActivity.startActivity(this);
                break;
            case R.id.btn31://5.0系统转场动画的实现
//                DetailActivity.startActivity(this);
                break;
            case R.id.btn32://InputWidget
                InputWidgetActivity.startActivity(this);
                break;
            case R.id.btn33://UIActivity
                UIActivity.startActivity(this);
                break;
            case R.id.btn34://InnerClassActivity
                InnerClassActivity.startActivity(this);
                break;
            case R.id.btn35://view 分发机制
                TouchEventActivity.startActivity(this);
                break;
            case R.id.btn36://activity跳转数据返回
                FirstActivity.startActivity(this);
                break;
            case R.id.btn37://非UI线程起activity
                ThreadStartActivity.startActivity(this);
                break;
            case R.id.btn38://switchbutton
                MovableCheckboxActivity.startActivity(this);
                break;
            case R.id.btn39://品牌页
                RetailSalerBrandPageActivity.startActivity(this);
                break;
            case R.id.btn40://数据库测试
                DBActivity.startActivity(this);
                break;
            case R.id.btn41://数据库测试
                OkHttpActivity.startActivity(this);
                break;
            case R.id.btn42://handler测试
                HandlerActivity.startActivity(this);
                break;
        }
    }
}
