package step;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.example.largeimg.stepapp.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import step.data.StepCountAdapter;
import step.data.StepDBManager;
import step.model.StepDBModel;
import step.util.DateUtils;
import step.util.DensityUtils;
import step.util.HealthRecordUtil;
import step.view.CalibrationView;
import step.view.CustomScrollView;
import step.view.TrendView;
import step.view.TrendView.OnPointClick;

public class StepCountActivity extends BaseActivity implements OnPageChangeListener, OnClickListener, StepListener {


    private final int MSG_CHART = 1;
    private final int MSG_CIRCLE = 2;
    LinearLayout llWeek;
    ViewPager vpStepCount;
    Button btnReceive;
    LinearLayout llCircle;
    LinearLayout llChart;
    TextView mStepCount;
    TextView mStepDistance;
    TextView mConsumptionOfEnergy;
    TextView mMovementTime;
    CustomScrollView mScrollView;
    LinearLayout mTrendviewContainer;
    CalibrationView mCalibrationView;
    private List<StepDBModel> mList;
    private String[] weeks = new String[]{"一", "二", "三", "四", "五", "六", "日"};
    private SparseArray<TextView> textArray;
    private StepCountAdapter stepCountAdapter;
    private int week = 0;
    private TrendView mTrendview;
    private Handler mHandler;
    private OnPointClick mOnPointClick;
    private ArrayList<ArrayList<Float>> pointList;
    //折线图的最大值
    private int MaxValue = 7500;
    private boolean hasReceived = false;

    public static void startActivity(Activity activity) {
        Intent i = new Intent();
        i.setClass(activity, StepCountActivity.class);
        activity.startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        initView();
        initTitle();
        initData();

        initChart();
        initListener();

    }

    private void initView() {
        llWeek = (LinearLayout) findViewById(R.id.ll_week);
        vpStepCount = (ViewPager) findViewById(R.id.vp_step_count);
        btnReceive = (Button) findViewById(R.id.btn_receive);
        llCircle = (LinearLayout) findViewById(R.id.ll_circle);
        llChart = (LinearLayout) findViewById(R.id.ll_chart);
        mStepCount = (TextView) findViewById(R.id.stepCount);
        mStepDistance = (TextView) findViewById(R.id.stepDistance);
        mConsumptionOfEnergy = (TextView) findViewById(R.id.ConsumptionOfEnergy);
        mMovementTime = (TextView) findViewById(R.id.movementTime);
        mScrollView = (CustomScrollView) findViewById(R.id.scrollView);
        mTrendviewContainer = (LinearLayout) findViewById(R.id.trendviewContainer);
        mCalibrationView = (CalibrationView) findViewById(R.id.calibrationView);

    }

    private void initListener() {
        rightCommonImgBtn.setOnClickListener(this);
        leftImgBtn.setOnClickListener(this);
        StepDetector.addStepListener(this);
        if (StepDBManager.getIns()
                .isUploadPoints()) {
            btnReceive.setClickable(false);
            btnReceive.setText("已领取");
            btnReceive.setBackgroundResource(R.drawable.icon_step_receive_selected);
            btnReceive.setOnClickListener(null);
        } else {
            if (StepCountManager.getIns().getStepCount() >= 5000) {
                btnReceive.setClickable(true);
                btnReceive.setText("领取");
                btnReceive.setOnClickListener(this);
                btnReceive.setBackgroundResource(R.drawable.receive_point_btn_selector);
            } else {
                btnReceive.setText("领取");
                btnReceive.setTextColor(getResources().getColor(R.color.step_dark_grey));
                btnReceive.setBackgroundColor(getResources().getColor(R.color.step_color_f2f2f2));
                btnReceive.setOnClickListener(null);
                btnReceive.setClickable(false);
            }

        }
    }

    /**
     * 折线图 点击事件
     *
     * @param index
     */
    private void onPointclick(int index) {
        int count = 0;
        if (pointList.size() > 0) {
            count = pointList.get(0)
                    .get(index)
                    .intValue();
        }

        DecimalFormat c_df = new DecimalFormat("######0");

        //运动步数
        mStepCount.setText(count + "");

        //运动距离
        mStepDistance.setText(StepUtils.getDistance(count) + "KM");

        //运动时间
        double totleTime = StepUtils.getSportMinute(count);
        int hour = (int) (totleTime / 60);
        String minute = c_df.format(totleTime - hour * 60);
        if (hour == 0) {
            mMovementTime.setText(minute + "分钟");
        } else {
            mMovementTime.setText(hour + "小时" + minute + "分钟");
        }

        //消耗卡路里
        mConsumptionOfEnergy.setText(StepUtils.getCalorie(count) + "卡");

    }

    private void initChart() {

        //折线图 点击事件
        mOnPointClick = new OnPointClick() {
            @Override
            public void OnPointClickevent(final int index) {
                onPointclick(index);
            }
        };

        ArrayList colorList = new ArrayList();
        colorList.add(Color.parseColor("#FFFFFF"));

        pointList = new ArrayList<>();

        /**
         * 横线 5000步 目标线高度（※只画一条线）
         */
        final ArrayList<Float> lines = new ArrayList();
        lines.add(5000f);

        mTrendview = new TrendView(StepCountActivity.this, new ArrayList<ArrayList<Float>>());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        mTrendview.setMaxValue(MaxValue);
        mTrendview.setLayoutParams(params);
        mTrendview.setColorList(colorList);
        //左右的空隙
        mTrendview.paddingLeftRightSpace = 16;
        mTrendview.setShowAxisList(lines);

        mTrendviewContainer.addView(mTrendview);//把趋势图 放到容器中
        mTrendview.setVisibility(View.INVISIBLE);
        mTrendview.registListener();
        mTrendview.setOnPointClick(mOnPointClick);//折线 点击事件

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                mTrendview.setVisibility(View.VISIBLE);
                mTrendview.initCalibrationYValue();

                mCalibrationView.setAxisLineValueList(lines);//x轴 数据
                mCalibrationView.setShowAxisList(mTrendview.getCalibrationYValue());//y轴 数据
                mCalibrationView.invalidate();
            }
        }, 100);

        new Thread() {
            @Override
            public void run() {
                ArrayList<StepDBModel> monthData = StepDBManager.getIns()
                        .getMonthData();

                LinkedHashMap<String, Float> map;
                map = HealthRecordUtil.getWeekDateHashMap();
                Set key = map.keySet();
                for (StepDBModel stepDBModel : monthData) {
                    if (key.contains(stepDBModel.getDate())) {
                        map.put(stepDBModel.getDate(), (float) stepDBModel.getStepCount());
                    }
                }
                ArrayList<Float> floats = new ArrayList<Float>();
                floats.addAll(map.values());

                Collections.reverse(floats);

                pointList.clear();
                pointList.add(floats);


                mHandler.sendEmptyMessage(MSG_CHART);
            }
        }.start();

    }

    private void initTitle() {

        titleCommonTv.setText("");
        titleCommonTv.setVisibility(View.VISIBLE);
        leftCommonImgBtn.setVisibility(View.VISIBLE);
        rightCommonImgBtn.setVisibility(View.VISIBLE);
        leftImgBtn.setVisibility(View.VISIBLE);
        rightCommonImgBtn.setImageResource(R.drawable.icon_record);
        leftImgBtn.setImageResource(R.drawable.icon_step_selected);

    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mList = getData();
                mHandler.sendEmptyMessage(MSG_CIRCLE);
            }
        }).start();

        mHandler = new Handler() {
            @Override
            public void handleMessage(final Message msg) {
                switch (msg.what) {
                    case MSG_CHART:
                        setChartData();
                        break;
                    case MSG_CIRCLE:
                        initViewPager();
                        initIndicator();
                        break;
                }

            }
        };
    }

    /**
     * 给折线图设置书记
     */
    private void setChartData() {
        try {
            MaxValue = Collections.max(pointList.get(0))
                    .intValue();
            if (MaxValue <= 5000) {
                MaxValue = 7500;
            } else {
                MaxValue = (int) (MaxValue * 1.1);
            }

            mTrendview.setMaxValue(MaxValue);
            mTrendview.scaleTemspace();
            mTrendview.setPointList(pointList);
            mTrendview.invalidate();

            mTrendview.initCalibrationYValue();
            mCalibrationView.setShowAxisList(mTrendview.getCalibrationYValue());
            mCalibrationView.invalidate();

            onPointclick(pointList.get(0)
                    .size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private List<StepDBModel> getData() {
        StepDBManager manager = StepDBManager.getIns();
        //        stepDBManager.writeStepToDb();
        ArrayList<StepDBModel> monthData = manager.getMonthData();
        LinkedHashMap<String, StepDBModel> map = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long currentTime = System.currentTimeMillis();
        int dateCount = manager.getDateCount();
        for (int i = dateCount; i >= 0; i--) {
            Date d = new Date(currentTime - i * (1000L * 60 * 60 * 24));
            StepDBModel model = new StepDBModel();
            model.setUserid(StepManager.getUID());
            model.setStepCount(0);
            model.setDate(dateFormat.format(d));
            map.put(dateFormat.format(d), model);
        }
        Set key = map.keySet();
        for (StepDBModel stepDBModel : monthData) {
            if (key.contains(stepDBModel.getDate())) {
                map.put(stepDBModel.getDate(), stepDBModel);
            }
        }
        ArrayList<StepDBModel> newMonthDate = new ArrayList<>();
        newMonthDate.addAll(map.values());
        return newMonthDate;
    }

    private void initViewPager() {
        stepCountAdapter = new StepCountAdapter(StepManager.getmContext(), mList, vpStepCount);
        vpStepCount.setAdapter(stepCountAdapter);
        vpStepCount.setCurrentItem(mList.size() - 1);
    }

    private void initIndicator() {
        textArray = new SparseArray<>();
        llWeek.removeAllViews();
        if (mList != null && mList.size() > 0) {
            week = DateUtils.getWeekIndex(mList.get(mList.size() - 1)
                    .getDate());
        }
        for (int i = 0; i < 7; i++) {
            View view = View.inflate(this, R.layout.item_step_count_indicator, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtils.dp2px(30), DensityUtils.dp2px
                    (30));
            if (i != 0) {
                lp.leftMargin = DensityUtils.dp2px(20);
            }
            view.setLayoutParams(lp);
            FrameLayout flBg = (FrameLayout) view.findViewById(R.id.fl_bg);
            TextView tvIndicator = (TextView) view.findViewById(R.id.tv_indicator_week);
            tvIndicator.setText(weeks[i]);
            if (i == week) {
                flBg.setEnabled(true);
                tvIndicator.setEnabled(true);
            } else {
                flBg.setEnabled(false);
                tvIndicator.setEnabled(false);
                if (i > week) {
                    tvIndicator.setTextColor(getResources().getColor(R.color.step_trans_light_grey));
                }
                if (mList.size() - 1 < week && i < week - mList.size() + 1) {
                    tvIndicator.setTextColor(getResources().getColor(R.color.step_trans_light_grey));
                }
            }
            llWeek.addView(view);
            textArray.put(i, tvIndicator);
        }
        vpStepCount.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mList != null && mList.size() > 0) {
            int newPosition = DateUtils.getWeekIndex(mList.get(position)
                    .getDate());
            for (int i = 0; i < 7; i++) {
                llWeek.getChildAt(i)
                        .setEnabled(false);
                textArray.get(i)
                        .setEnabled(false);
                int firstWeek = DateUtils.getWeekIndex(mList.get(0)
                        .getDate());
                int lastWeek = DateUtils.getWeekIndex(mList.get(mList.size() - 1)
                        .getDate());

                if (0 <= newPosition && newPosition <= lastWeek && (mList.size() - 1 - position) < 7 && i > lastWeek
                        || newPosition >= firstWeek && newPosition <= 6 && (position < 7) && i < firstWeek) {
                    textArray.get(i)
                            .setTextColor(getResources().getColor(R.color.step_trans_light_grey));
                } else {
                    textArray.get(i)
                            .setTextColor(getResources().getColor(R.color.step_text_color_999));
                }

                if (i == newPosition) {
                    llWeek.getChildAt(i)
                            .setEnabled(true);
                    textArray.get(i)
                            .setTextColor(getResources().getColor(R.color.step_white));
                    //                    textArray.get(i).setEnabled(true);
                }
            }

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.right_btn) {
            llCircle.setVisibility(View.GONE);
            llChart.setVisibility(View.VISIBLE);
            rightCommonImgBtn.setImageResource(R.drawable.icon_record_selected);
            leftImgBtn.setImageResource(R.drawable.icon_step);

            try {
                pointList.get(0).set(pointList.get(0).size() - 1, (float) StepCountManager.getIns().getStepCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setChartData();


        } else if (i == R.id.left_btn) {
            llChart.setVisibility(View.GONE);
            llCircle.setVisibility(View.VISIBLE);
            rightCommonImgBtn.setImageResource(R.drawable.icon_record);
            leftImgBtn.setImageResource(R.drawable.icon_step_selected);

        }
    }

    @Override
    public void onStep(int stepCount) {
        if (mList != null && mList.size() > 0) {
            mList.get(mList.size() - 1)
                    .setStepCount(stepCount);
            if (stepCountAdapter != null) {
                stepCountAdapter.setData(mList);
                stepCountAdapter.notifyDataSetChanged();
            }
            //当步数实时变动时,改变领取按钮状态,设置标志位,只改变一次.节约性能
            if (!hasReceived && stepCount >= 5000 && !StepDBManager.getIns()
                    .isUploadPoints()) {
                btnReceive.setClickable(true);
                btnReceive.setText("领取");
                btnReceive.setOnClickListener(this);
                btnReceive.setTextColor(getResources().getColor(R.color.step_ThemeColor));
                btnReceive.setBackgroundResource(R.drawable.receive_point_btn_selector);
                hasReceived = true;
            }
        }
    }

    @Override
    public void passValue() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        //日期改变 从新初始化数据
        if (StepCountManager.getIns().dateChanged()) {
            StepCountManager.getIns().clearMemory();
            initData();
            initChart();
//            initListener();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        StepDBManager.getIns().writeStepToDb();//写数据
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (StepDetector.mStepListeners != null) {
            StepDetector.mStepListeners.remove(this);
        }
    }
}
