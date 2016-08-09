package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.myframe.www.base.BaseActivity;
import com.myframe.www.widget.roundprogressbar.RoundProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
* 圆形进度
* @author wuhai
* create at 2016/6/3 15:47
*/
public class RoundProgressBarActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.roundProgressBar1)
    RoundProgressBar roundProgressBar1;
    @Bind(R.id.roundProgressBar2)
    RoundProgressBar roundProgressBar2;
    @Bind(R.id.roundProgressBar3)
    RoundProgressBar roundProgressBar3;
    @Bind(R.id.roundProgressBar4)
    RoundProgressBar roundProgressBar4;
    @Bind(R.id.roundProgressBar5)
    RoundProgressBar roundProgressBar5;

    private int progress = 0;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RoundProgressBarActivity.class);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_progress_bar);
        ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while(progress < 90){
                            progress += 1;

                            System.out.println(progress);

                            roundProgressBar1.setProgress(progress);
                            roundProgressBar2.setProgress(progress);
                            roundProgressBar3.setProgress(progress);
                            roundProgressBar4.setProgress(progress);
                            roundProgressBar5.setProgress(progress);

                            /**
                             * 100毫秒绘制一次
                             */
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
                break;
        }
    }
}
