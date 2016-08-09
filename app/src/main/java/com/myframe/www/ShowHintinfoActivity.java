package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.myframe.www.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
* 错误提示 titlebar背后弹出并缩回
* @author wuhai
* create at 2016/6/6 14:55
*/
public class ShowHintinfoActivity extends BaseActivity {

    @Bind(R.id.tv_errmsg)
    TextView tv_errmsg;
    @Bind(R.id.title_bar)
    TextView titleBar;

    private Timer timer;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ShowHintinfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hintinfo);
        ButterKnife.bind(this);

        timer = new Timer();
    }

    //可行方法
    public void errshow02(View view) {
        showErrMSG("输入的手机号不正确");
    }


    private void showErrMSG(String errmsg) {
        tv_errmsg.setText(errmsg);
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, tv_errmsg.getHeight());
        ta.setDuration(1000);
        ta.setFillAfter(true);
        tv_errmsg.startAnimation(ta);

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }, 2000);//因为动画时间需要1S所以想停留1S 需要设置2S
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //fromY要设置为tv_errmsg.getHeight()，因为再次移动还是已原先初始位置为基准
                    TranslateAnimation ta02 = new TranslateAnimation(0, 0, tv_errmsg.getHeight(), 0);
                    ta02.setDuration(1000);
                    ta02.setFillAfter(true);
                    tv_errmsg.startAnimation(ta02);
                    break;

                default:
                    break;
            }
        }

        ;
    };
}
