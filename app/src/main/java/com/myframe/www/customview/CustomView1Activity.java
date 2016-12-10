package com.myframe.www.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.L;

/**
 * https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B02%5DCanvas_BasicGraphics.md
 */
public class CustomView1Activity extends BaseActivity {

    private static final String TAG = "CustomView";

    @Bind(R.id.view01)
    View view01;
    @Bind(R.id.pieview)
    PieView pieview;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomView1Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view1);
        ButterKnife.bind(this);

        initPieview();

        view01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                L.e(TAG, "getTop()=" + v.getTop() + ",getLeft()=" + v.getLeft() + ",getBottom()=" + v.getBottom() + ",getRight()=" + v.getRight());
                L.e(TAG, "getX()=" + event.getX() + ",getY()=" + event.getY() + ",getRawX()=" + event.getRawX() + ",getRawY()=" + event.getRawY());

                return false;
            }
        });

    }

    private void initPieview() {
        List<PieData> list = new ArrayList<>();
        list.add(new PieData("1",50));
        list.add(new PieData("2",100));
        list.add(new PieData("2",100));
        list.add(new PieData("3",200));

        pieview.setStartAngle(0);
        pieview.setDatas(list);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
