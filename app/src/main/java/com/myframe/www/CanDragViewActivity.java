package com.myframe.www;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.wuhai.common.utils.ToastUtils;

/**
 * 可拖拽的控件
 *
 * @author wuhai
 *         create at 2016/5/19 11:40
 */
public class CanDragViewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.can_drag_view)
    View canDragView;
    @Bind(R.id.can_drag_view1)
    View canDragView1;

    private int screenWidth;
    private int screenHeight;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CanDragViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_drag_view);
        ButterKnife.bind(this);

        screenWidth = (int) getScreenWidth(this);
        screenHeight = (int) getScreenHeight(this);

        //无点击的滑动
        canDragView1.setOnTouchListener(movingEventListener1);

        //有点击的滑动
        canDragView.setOnClickListener(this);
        canDragView.setOnTouchListener(movingEventListener);
    }

    /**
     * @param context
     * @return // 屏幕宽（像素，如：480px）
     */
    public static float getScreenWidth(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @param context
     * @return // 屏幕高（像素，如：800px）
     */
    public static float getScreenHeight(Context context) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private View.OnTouchListener movingEventListener = new View.OnTouchListener() {
        int lastX, lastY;
        boolean clickAction = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    System.out.println("MotionEvent.ACTION_DOWN");
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    clickAction = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    System.out.println("MotionEvent.ACTION_MOVE");
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    // 如果滑动了 则取消点击事件
                    if (Math.abs(dx) > 10 || Math.abs(dy) > 10)
                        clickAction = true;
                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;
                    // 设置不能出界
                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }

                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - v.getWidth();
                    }

                    if (top < 0) {
                        top = 0;
                        bottom = top + v.getHeight();
                    }

                    int height = screenHeight;
                    if (bottom > height - (v.getHeight() / 2)) {
                        bottom = height - (v.getHeight() / 2);
                        top = bottom - v.getHeight();
                    }

                    v.layout(left, top, right, bottom);

                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();

                    break;

                case MotionEvent.ACTION_UP:
                    System.out.println("MotionEvent.ACTION_UP");
                    break;
            }
            return clickAction;
        }
    };

    private View.OnTouchListener movingEventListener1 = new View.OnTouchListener() {
        int lastX, lastY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    System.out.println("MotionEvent.ACTION_DOWN");
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    System.out.println("MotionEvent.ACTION_MOVE");
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;
                    // 设置不能出界
                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }

                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - v.getWidth();
                    }

                    if (top < 0) {
                        top = 0;
                        bottom = top + v.getHeight();
                    }

                    int height = screenHeight;
                    if (bottom > height - (v.getHeight() / 2)) {
                        bottom = height - (v.getHeight() / 2);
                        top = bottom - v.getHeight();
                    }

                    v.layout(left, top, right, bottom);

                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();

                    break;

                case MotionEvent.ACTION_UP:
                    System.out.println("MotionEvent.ACTION_UP");
                    break;
            }
            return true;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.can_drag_view:
                ToastUtils.showShort(this, "我被点了");
                break;
        }
    }
}
