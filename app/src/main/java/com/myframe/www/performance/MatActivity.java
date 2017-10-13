package com.myframe.www.performance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * android studio MAT使用
 * 参考http://blog.csdn.net/zxm317122667/article/details/52162764
 */
public class MatActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.add)
    Button add;

    private List<ImageView> list = new ArrayList<>();
//    static MemoryLeak memoryLeak;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MatActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init() {
//        if (memoryLeak == null) {
//            memoryLeak = new MemoryLeak();
//        }
    }

    private void initListener() {
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                for (int i = 0; i < 10000; i++) {
                    ImageView imageView = new ImageView(this);
                    list.add(imageView);
                }

                //无限循环 线程
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        while (true) {
                            try {
                                System.out.println("Thread running!!");
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果是static的置null，也解决不了内存泄漏，只是会把memoryLeak数据清下而已
//        memoryLeak = null;
    }

    class MemoryLeak {
        void doSomeThing() {
            System.out.println("Wheee!!!");
        }
    }
}
