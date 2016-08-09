package com.myframe.www.widget.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.myframe.www.R;
import com.myframe.www.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomviewActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.back_layout)
    ImageView mBackView;//这是背景view，它可以是任何，例如：gridview
    @Bind(R.id.grid)
    GridView mGridView;
    @Bind(R.id.linear)
    CustomView mScrollView;//自定义LinearLayout控件
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int mMoveGap;
    private int mBackHeight;//背景view高度

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);

//        swipeRefreshLayout.setColorSchemeResources(R.color.app_base_color,
//                R.color.app_base_color, R.color.app_base_color);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        SystemClock.sleep(3000);
//
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }).start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });


        mMoveGap = Utils.dip2px(this, 10);//向上向下滑动 阈值

        mBackHeight = getBackHeight();//从新计算出一个 背景view高度 像素px
        ViewGroup.LayoutParams params = mBackView.getLayoutParams();
        params.height = mBackHeight;
        mBackView.setLayoutParams(params);//给背景view重新设置高度

        mGridView.setAdapter(new MyAdapter());
        mGridView.setLongClickable(true);//长按=true
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                if (expanded) {
                    mDistance = 0;
                    expanded = false;//不可延伸
                    mScrollView.smoothScrollTo(0, 0);//长按滑到顶部
                }
                return true;
            }
        });
        mGridView.setOnTouchListener(mGridTouchListener2);
        mGridView.setHorizontalFadingEdgeEnabled(false);//横向边界阴影
        mGridView.setVerticalFadingEdgeEnabled(false);//纵向边界阴影

        mDistance = mBackHeight;//mDistance = 背景view高度（像素px）
        expanded = true;//初始值：true可延伸

        mScrollView.scrollTo(0, -(int) mDistance);//自定义View滑动到背景view下面，即背景view全部展示出来
    }

    private View mCurrentView;//获得
    private boolean dealMove = false;//是否处理（遮挡、显示 backview）
    private boolean expanded = false;
    private boolean mMoveUp = false;//true 向上; false 向下
    /**
     * _y   down 时候记录y坐标；move时候，跟getRawY比较判断向上还是向下滑，最后记录getRawY值
     * lastY down 时候记录y坐标；move时候，getRawY-lastY > mMoveGap确定mMoveUp向上向下，最后记录getRawY值
     */
    private float _y, lastY;
    private float mDistance;//dealMove=true 处理时候，向上=0，向下=mBackHeight

    //TODO 这个里面的一些逻辑 有点不是很明白
    private View.OnTouchListener mGridTouchListener2 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int index = mGridView.pointToPosition((int) event.getX(), (int) event.getY());//根据按下的X,Y坐标获取所点击item的position
            index = index - mGridView.getFirstVisiblePosition();
            mCurrentView = mGridView.getChildAt(index);//根据position获取该item所对应的View（书架长按，获得选中view）

            int code = event.getAction() & MotionEvent.ACTION_MASK;//这句话其实没有意义，任何&FF=任何(直接把下面的code换成event.getAction()效果是一样的)
            if (mScrollView.isScrolling()) {//是否在滚动
                resetItem();
                if (code == MotionEvent.ACTION_UP) {
                    dealMove = false;
                    resetItem();
                    return false;//false，则向上传递给父控件
                }
                return true;
            }

            switch (code) {
                case MotionEvent.ACTION_DOWN:
                    lastY = event.getRawY();//getRawY()是表示相对于屏幕左上角的Y坐标值
                    _y = lastY;
                    dealMove = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!expanded && event.getRawY() < _y) {//不可延伸，并且，向上滑动（原点向下延伸是Y轴正方向）
                        _y = event.getRawY();
                        return false;
                    }
                    _y = event.getRawY();
                    if (Math.abs(event.getRawY() - lastY) > mMoveGap) {//Y轴滑动距离>10dp转换后的像素
                        if (event.getRawY() > lastY)
                            mMoveUp = false;//向下滑
                        else{
//                            swipeRefreshLayout.setEnabled(false);
                            mMoveUp = true;//向上滑
                        }
                        lastY = event.getRawY();
                        if (expanded != mMoveUp) {
                            if (mMoveUp){
//                                swipeRefreshLayout.setEnabled(false);
                                return false;
                            }
//                            swipeRefreshLayout.setEnabled(false);
                            return dealMove;
                        }
                        if (!expanded) {//不可延伸
                            if (moveToTop()){
                                swipeRefreshLayout.setEnabled(true);
                                dealUp();
                            }
                            else{
//                                swipeRefreshLayout.setEnabled(true);
                                return false;
                            }
                        } else{
                            swipeRefreshLayout.setEnabled(false);
                            dealUp();
                        }
                        MotionEvent ev = MotionEvent.obtain(event);
                        ev.setAction(MotionEvent.ACTION_UP);
                        mGridView.dispatchTouchEvent(ev);
                        return true;
                    } else
                        return dealMove;//false
                case MotionEvent.ACTION_UP:
                    dealMove = false;
                    resetItem();
                    break;
            }
            return false;
        }
    };

    private void dealUp() {
        // mScrollView.smoothScrollTo(mDistance, mBackView.getHeight(),
        // mMoveUp);

        if (mMoveUp) {//向上滑，遮挡住mBackView
            mDistance = 0;
            mScrollView.smoothScrollTo(0, (int) mDistance);
        } else {//向下滑，展现mBackView
            mDistance = mBackHeight;
            mScrollView.smoothScrollTo(0, -(int) mDistance);
        }
        expanded = !mMoveUp;
        dealMove = true;
        resetItem();
    }

    private boolean moveToTop() {
        View view = mGridView.getChildAt(0);//函数获取对应的item的View
        int first = mGridView.getFirstVisiblePosition();
        //TODO mBackView显：true,不显:false?
        /**
         * Utils.dip2px(this, 9) 应该是手机titlebar像素高？
         *
         */
        if (first == 0 && view != null
                && view.getTop() < Utils.dip2px(this, 9)
                && view.getTop() >= 0)
            return true;
        return false;
    }

    private void resetItem() {
        if (mCurrentView != null) {
            mCurrentView.setPressed(false);
            mCurrentView.setSelected(false);
        }
    }

    private class MyAdapter extends BaseAdapter {
        private int[] images = {R.drawable.f0, R.drawable.f1, R.drawable.f2,
                R.drawable.f3, R.drawable.f4, R.drawable.f5, R.drawable.f6,
                R.drawable.f7, R.drawable.f8, R.drawable.f9, R.drawable.f10,
                R.drawable.f11};
        private String[] names = {"阿卓", "阿怡", "阿莲", "阿红", "阿大", "阿花", "阿凤",
                "玛丽亚", "阿菲", "阿玲", "阿俪", "阿贝"};

        @Override
        public int getCount() {

            return images.length;
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {

            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(CustomviewActivity.this).inflate(R.layout.item_gridview, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) view.findViewById(R.id.iv);
                holder.tv = (TextView) view.findViewById(R.id.tv);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.iv.setImageResource(images[position]);
            holder.tv.setText(names[position]);
            return view;
        }

    }

    static class ViewHolder {
        private ImageView iv;
        private TextView tv;
    }


    private int getBackHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int w = (width - Utils
                .dip2px(this, 30)) / 3;
        int h = (int) (1.33 * w + Utils.dip2px(this, 40));
        return h;
    }
}