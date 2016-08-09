package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myframe.www.view.GuideView;
import com.myframe.www.widget.titlebar2.TitleViewWithBack;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 引导蒙层
 *
 * @author wuhai
 *         create at 2016/7/28 18:37
 */
public class GuideViewActivity extends AppCompatActivity {

    @Bind(R.id.titleview_with_back)
    TitleViewWithBack titleviewWithBack;
    @Bind(R.id.btn_test)
    Button btnTest;
    @Bind(R.id.btn_test2)
    Button btnTest2;
    @Bind(R.id.btn_test0)
    Button btnTest0;

    private GuideView guideView;
    private GuideView guideView3;
    private GuideView guideView2;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GuideViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);
        ButterKnife.bind(this);

        setGuideView();
    }

    private void setGuideView() {

        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.img_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);

        // 使用文字
        TextView tv = new TextView(this);
        tv.setText("欢迎使用");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);

        // 使用文字
        final TextView tv2 = new TextView(this);
        tv2.setText("欢迎使用2");
        tv2.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextSize(30);
        tv2.setGravity(Gravity.CENTER);

        guideView = GuideView.Builder
                .newInstance(this)
                .setTargetView(btnTest0)//设置目标
                .setSharedPreferencesKey("guide1")
                .setCustomGuideView(iv)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.CIRCULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView.showOnce();
                        guideView.hide();

                        guideView2.show();
                    }
                })
                .build();


        guideView2 = GuideView.Builder
                .newInstance(this)
                .setTargetView(btnTest)
                .setSharedPreferencesKey("guide2")
                .setCustomGuideView(tv)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.ELLIPSE)   // 设置椭圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView2.showOnce();
                        guideView2.hide();

                        guideView3.show();
                    }
                })
                .build();


        guideView3 = GuideView.Builder
                .newInstance(this)
                .setTargetView(btnTest2)
                .setSharedPreferencesKey("guide3")
                .setCustomGuideView(tv2)
                .setDirction(GuideView.Direction.LEFT_BOTTOM)
                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置矩形显示区域，
                .setRadius(80)          // 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView3.showOnce();
                        guideView3.hide();
//                        guideView.show();
                    }
                })
                .build();

        /**
         * TODO sp保存是否展示过，也可以通过外部sp做开关，具体需求具体分析
         */
        guideView.show();
        if (guideView.hasShown() && !guideView2.hasShown()) {
            guideView2.show();
        } else if (guideView2.hasShown()) {
            guideView3.show();
        }
    }
}
