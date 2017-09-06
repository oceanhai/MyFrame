package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.myframe.www.picasso.PicassoActivity;

/**
 * Created by wuhai on 2017/9/6 0006 15:10.
 * 描述：inflate 参数 测试
 */
public class InflateActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InflateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflate);

        LinearLayout ll = (LinearLayout) findViewById(R.id.activity_inflate);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //1.1 root不为null，attachToRoot为true
//        layoutInflater.inflate(R.layout.activity_inflate_linearlayout,ll,true);
        //因为当第三个参数为true时，会自动将第一个参数所指定的View添加到第二个参数所指定的View中
        //※下面操作会报错
        //java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent
//        View view = layoutInflater.inflate(R.layout.activity_inflate_linearlayout, ll, true);
//        ll.addView(view);

        //1.2 root不为null，attachToRoot为false
        //结果跟1.1效果一致
//        View view = layoutInflater.inflate(R.layout.activity_inflate_linearlayout,ll,false);
//        ll.addView(view);

        //1.3 root为null
        //当第二个参数为null，第三个参数为false时（即使为true显示效果也是一样的，这里以false为例），
        // 由于在inflate方法中没有将linearlayout添加到某一个容器中，所以我需要手动添加，
        // 另外由于linearlayout并没有处于某一个容器中，所以它的根节点的宽高属性会失效
        View view = layoutInflater.inflate(R.layout.activity_inflate_linearlayout,null,false);
        ll.addView(view);
    }

}
