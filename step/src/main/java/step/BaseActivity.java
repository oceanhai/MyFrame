package step;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.largeimg.stepapp.R;

import java.util.Stack;


public class BaseActivity extends Activity {

    private static Stack<Activity> activityStack;
    private static Handler mApplicationHandler = new Handler(Looper.getMainLooper());
    protected FrameLayout baseContainer;
    protected View commonTitleView;
    protected RelativeLayout leftCommonImgBtn;
    protected ImageButton rightCommonImgBtn;
    protected Button rightCommonText;
    protected TextView titleCommonTv;
    protected ImageButton leftImgBtn;
    private Toast toast = null;
    private LayoutInflater layoutInflater;

    /**
     * add Activity 添加Activity到栈
     */
    static public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    static public Activity currentActivity() {
        if (activityStack == null) {
            return null;
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    static public void finishActivity() {
        if (activityStack == null) {
            return;
        }
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    static public void finishActivity(Activity activity) {
        if (activityStack == null) {
            return;
        }
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    static public void finishActivity(Class<?> cls) {
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    static public void finishAllActivity() {
        if (activityStack == null) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    static public Activity findActivity(Class<?> cls) {
        Activity act = null;
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    act = activity;
                    break;
                }
            }
        }
        return act;
    }

    /**
     * 退出应用程序
     */
    static public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

    public Handler getHandler() {
        return mApplicationHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        super.setContentView(R.layout.activity_base_xml);
        initBaseView();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //空实现，以避免activity被回收后重新create新的fragment
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishActivity(this);
    }

    /**
     * 将子activity的view 加入到baseactivity 中
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(BaseActivity.this);

        }
        View ChildView = layoutInflater.inflate(layoutResID, null);
        baseContainer.removeAllViews();
        baseContainer.addView(ChildView);
    }

    /**
     * 隐藏公共title
     */
    protected void hideCommonBaseTitle() {
        if (commonTitleView != null) {
            commonTitleView.setVisibility(View.GONE);
        }
    }

    /**
     * 显示公共的title
     */
    protected void showCommonBaseTitle() {

        if (commonTitleView != null) {
            commonTitleView.setVisibility(View.VISIBLE);
        }
    }


    public void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    // 点击空白区域 自动隐藏软键盘
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    private void initBaseView() {
        baseContainer = (FrameLayout) findViewById(R.id.baseContainer);
        commonTitleView = findViewById(R.id.commonTitle);
        leftCommonImgBtn = (RelativeLayout) findViewById(R.id.Lback);
        titleCommonTv = (TextView) findViewById(R.id.title_name);
        rightCommonImgBtn = (ImageButton) findViewById(R.id.right_btn);
        rightCommonText = (Button) findViewById(R.id.right_text);
        leftCommonImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        leftImgBtn = (ImageButton) findViewById(R.id.left_btn);
    }

    /**
     * 获取 打点统计Id
     *
     * @return
     */
    protected String getStatisticalId() {
        return null;
    }


}
