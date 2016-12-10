
package step.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import step.util.HealthRecordUtil;
import step.view.CustomScrollView.callBackScrollStop;


public class TrendView extends View implements callBackScrollStop {

    public static boolean bEexception = false;
    private final int dp2px_3 = (int) dip2px(getContext(), 3);
    private final int dp2px_5 = (int) dip2px(getContext(), 5);
    /**
     * x轴文案 画笔
     */
    private final Paint LinePaint = new Paint();
    private final Path mPath = new Path();
    //x轴 数据间的间距
    public int itemSpace = 30;
    //x轴的文字，距离屏幕左侧右侧的距离,单位dp
    public int paddingLeftRightSpace = 0;
    //控件x轴上的文字，距离底部的内边距,单位dp
    public int paddingBottom = 20;
    //折现上的点，距离x轴文字的距离，单位dp
    public int paddingBwtweenBottomAndLine = 5;
    //折现上的点和上方的文字的距离，单位dp
    public int paddingBwtweenTextAndLine = 5;
    int linkid = -2;
    /**
     * 控件高度按最大值，等分之后，单位高度
     */
    double temspace = 0;
    //底部字体的宽度(单个字符的宽度)
    float xfont_width = 0;
    //底部字体的高度(单个字符的宽度)
    float xfont_height = 0;
    //在折现上的字体的高度
    float font_height_online = 0;
    //父容器里面的scrollview
    CustomScrollView customScrollView;
    ArrayList<Float> mIntegerIntegerHashMap = new ArrayList<Float>();
    /**
     * 画圆点画笔
     */
    private Paint circlePaint = new Paint();
    /**
     * 步数画笔
     */
    private Paint lineTextPaint = new Paint();
    /**
     * x轴画笔
     */
    private Paint mXAxisPaint = new Paint();
    /**
     * 星期颜色
     */
    private int mTopWePaintColor = 0;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 圆点的半径
     */
    private int radius = (int) dip2px(getContext(), 3);
    /**
     * X轴点间距
     */
    private float xSpace = 0;
    /**
     * 控件高度
     */
    private int src_H = 0;
    /**
     * 控件宽度
     */
    private int src_W = 0;
    /**
     * 底部开始高度
     */
    private float textLow_H = 0;
    /**
     * x轴开始坐标
     */
    private float xAxisStart = 0;
    private int maxValue = 100;
    /**
     * 最低温度
     */
    private int minValue = 0;
    private ArrayList firstPointArray = new ArrayList();
    private int dataSize = 0;
    private ArrayList<String> xAXisdates = new ArrayList<>();
    private ArrayList<String> xAXisSeconddates = new ArrayList<>();
    //是否开启，滑动控件，停止之后，离屏幕中心最近的点变色的功能
    private boolean showHighLightEffect = false;
    //x轴上的文字的间距
    private int betweenText = 4;
    //x轴上的文字和图标中的最下面的一条线的距离
    private int betweenTextAndLine = 5;
    //储存颜色的list（当画多条线的时候，每条线的颜色不一样）
    private ArrayList<Integer> colorList;
    //平行于x轴的线，存放y轴对应的值
    private ArrayList<Float> axisLineList;
    //在折线 和刻度线上的文字是否显示整形，false显示浮点型
    private boolean isShowIntegerText = true;
    private ArrayList<Float> CalibrationYValue = new ArrayList<>();
    private Paint Calibration;
    private ArrayList<ArrayList<Float>> pointList = new ArrayList<>();
    //点击图上的点的回调
    private OnPointClick mOnPointClick;
    //图表画多条线的时候，决定哪些要绘制出来
    private ArrayList<Boolean> showWhichLine = new ArrayList<>();


    public TrendView(Context context) {
        super(context);
        this.mContext = context;
    }

    /**
     * @param context
     * @param plist
     */
    public TrendView(Context context, ArrayList<ArrayList<Float>> plist) {
        super(context);
        this.mContext = context;
        pointList.clear();
        pointList.addAll(plist);
        init();

    }

    public void setOnPointClick(final OnPointClick onPointClick) {
        mOnPointClick = onPointClick;
    }

    public void setPointList(final ArrayList<ArrayList<Float>> pointL) {
        pointList.clear();
        pointList.addAll(pointL);
        if (dataSize != 0 && pointList.size() > 0 && dataSize == pointList.get(0).size()) {
            firstPointArray = pointList.get(0);

        } else {
            int size = 0;
            if (pointL.size() > 0) {
                size = pointList.get(0).size();
            }
            Log.e("yu", "x轴的坐标数，和数据的数量不一致 x轴 size:" + dataSize + "   firstPointArray数据 size:" + size);
        }
        judgepenScrollEvent();
        //invalidate();

    }

    @Override
    public void invalidate() {
        //init();
        super.invalidate();

    }

    private void init() {

        setFocusable(true);
        setWillNotDraw(false);

        //x轴上文案的paint
        mXAxisPaint.setTextSize(dip2px(mContext, 10));

        //画线的paint，textsize先写死
        lineTextPaint.setTextSize(32);

        //画圆点的paint
        circlePaint.setTextSize(32);

        xAXisSeconddates.clear();
        xAXisdates.clear();
        //取得系统日期在内的过去31天对应数据（日期，星期）
        HealthRecordUtil.getWeekDate(xAXisSeconddates, xAXisdates);

        if (xAXisdates.size() != 0) {
            xfont_width = getFontWidth(mXAxisPaint, xAXisdates.get(0));
            dataSize = xAXisdates.size();

        }

        judgepenScrollEvent();

        xSpace = dip2px(mContext, itemSpace);

        //当只有一组数据（只需要画一条线的时候），将唯一的一组值传递出来
        if (pointList.size() >= 1) {
            this.firstPointArray.addAll(pointList.get(0));
        }


    }

    private void judgepenScrollEvent() {
        if (pointList.size() == 1) {
            showHighLightEffect = true;
        } else {
            showHighLightEffect = false;
        }
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;

    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        int width = (int) ((dataSize - 1) * xSpace + xfont_width * dataSize + dip2px(getContext(),
                paddingLeftRightSpace) * 2);
        //Log.d("yu", "ommeasure  " + width);
        setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        //		w = dip2px(mContext,60)*9;
        if (bEexception)
            return;

        src_H = h;
        src_W = w;
        xfont_height = getFontHeight(mXAxisPaint, xAXisdates.get(0));

        if (showHighLightEffect) {
            font_height_online = getFontHeight(lineTextPaint, pointList.get(0)
                    .get(0) + "");
        }

        scaleTemspace();

        //为了x轴的坐标其实位置有一定的空隙
        xAxisStart = xAxisStart + dip2px(getContext(), paddingLeftRightSpace);
    }

    //如果重新设置了最大值，需要重新计算
    public void scaleTemspace() {
        //按数据的最大值，将屏幕的绘制高度等分
        double drawHeigjt = src_H - xfont_height * 2 - dip2px(getContext(), paddingBottom) - font_height_online - dip2px
                (getContext(), betweenText) - dip2px(getContext(), betweenTextAndLine) - dip2px(getContext(),
                paddingBwtweenTextAndLine);

        //等分的高度，由余数加商
        temspace = Double.valueOf(drawHeigjt / Double.valueOf(maxValue - minValue));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (bEexception)
            return;
        drawAxisLine(canvas);//x轴 计步器目标线

        drawXAxisText(canvas);//x轴 周几+日期（xx）

        for (int i = 0; i < pointList.size(); i++) {

            //图表画多条线的时候，决定哪些要绘制出来
            if (showWhichLine.size() > 0 && !showWhichLine.get(i)) {
                continue;
            }
            drawLineAndLineText(canvas, pointList.get(i), i);//x轴  点间连线
            drawCircle(canvas, pointList.get(i), i);
        }

    }

    /**
     * 画x轴上的文案
     * 周几+日期（xx）
     *
     * @param
     */

    public void drawXAxisText(Canvas canvas) {

        //画x轴的数据
        for (int i = 0; i < xAXisdates.size(); i++) {

            //x轴上文案的paint
            mXAxisPaint.setColor(getmTopWePaintColor());

            float width = getValueEachPoint(i);


            if (linkid == i) {
                mXAxisPaint.setTextSize(dip2px(mContext, 12));
            } else {
                mXAxisPaint.setTextSize(dip2px(mContext, 10));
            }

            canvas.drawText(xAXisdates.get(i), width - getFontWidth(mXAxisPaint, xAXisdates.get(i)) / 2, src_H -
                            dip2px(getContext(), paddingBottom) - xfont_height - dip2px(getContext(), betweenText),
                    mXAxisPaint);
            canvas.drawText(xAXisSeconddates.get(i), width - getFontWidth(mXAxisPaint, xAXisSeconddates.get(i)) / 2, src_H -
                    dip2px(getContext(), paddingBottom), mXAxisPaint);

            //Log.d("yu", (width + "     ----" + "  xAxisStart " + xAxisStart + " " +
            //        "  xSpace:" + xSpace + "  xfont_width" + xfont_width));

        }
    }

    /**
     * 画折线和折现上的数据
     *
     * @param canvas
     * @param point
     * @param index  画第几条线
     */
    private void drawLineAndLineText(Canvas canvas, ArrayList<Float> point, int index) {

        Paint dottedinepaint = new Paint();
        dottedinepaint.setStyle(Style.STROKE);
        dottedinepaint.setStrokeWidth(dip2px(mContext, 1));
        dottedinepaint.setColor(getmGesturePaintColor(index));
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        dottedinepaint.setPathEffect(effects);


        LinePaint.setAntiAlias(true);
        LinePaint.setStyle(Style.STROKE);
        LinePaint.setStrokeWidth(dip2px(mContext, 1));
        LinePaint.setColor(getmGesturePaintColor(index));

        //折现上的文案的颜色
        lineTextPaint.setColor(getmTopWePaintColor());

        float temppreviousY = 0;

        int j = -1;
        for (int i = 0; i < point.size(); i++) {

            float previousX = getValueEachPoint(i);
            float previousY = getDataPointHeigh(point.get(i));

            if (i != point.size()) {

                //如果图标上画多条线，则不现实线上面的文字
                if (showHighLightEffect) {

                    String text = "";
                    if (isShowIntegerText) {
                        text = point.get(i)
                                .intValue() + "";
                    } else {
                        text = point.get(i) + "";
                    }
                    //点上方的文字
                    //canvas.drawText(text, previousX, previousY - dip2px(getContext(), paddingBwtweenTextAndLine),
                    //        lineTextPaint);
                }

                //画折线
                if (i == 0) {
                    mPath.moveTo(previousX, previousY);
                }

                Log.d("yu", "lineto:    " + "previousX " + previousX + "     previousY    " + previousY + "   ------" + i);

                mPath.lineTo(previousX, previousY);
                if (i != 0 && point.get(i) == 0 && point.get(i - 1) == 0) {
                    canvas.drawPath(mPath, dottedinepaint);

                } else {
                    canvas.drawPath(mPath, LinePaint);
                }
                mPath.reset();
                mPath.moveTo(previousX, previousY);

                temppreviousY = previousY;

            }
        }
    }

    /**
     * 获取刻度线上坐标的y值的集合
     *
     * @return
     */
    public ArrayList<Float> getCalibrationYValue() {
        return CalibrationYValue;
    }

    /**
     * 画平行于x轴的刻度线
     */
    private void drawAxisLine(Canvas canvas) {

        if (axisLineList != null && axisLineList.size() > 0) {

            LinePaint.setAntiAlias(true);
            LinePaint.setStyle(Style.STROKE);
            LinePaint.setStrokeWidth(dip2px(mContext, 0.5f));
            LinePaint.setColor(Color.parseColor("#4BFFFFFF"));

            //axisLineList是有多少线的集合，这里只有一条线：5000高
            for (int i = 0; i < axisLineList.size(); i++) {
                float previousX = 0;
                float previousY = getDataPointHeigh(axisLineList.get(i));
                //Log.d("yu"," drawAxisLine            previousX "+previousX+"       previousY "+previousY);

                //新的划线方法
                canvas.save();
                canvas.drawLine(previousX, previousY, src_W, previousY, LinePaint);
                canvas.restore();

                //旧的划线方法
//                mPath.moveTo(previousX, previousY);
//                mPath.lineTo(src_W, previousY);
//                canvas.drawPath(mPath, LinePaint);
//                mPath.reset();
//
//                mPath.moveTo(src_W/2,previousY);
//                mPath.lineTo(src_W, previousY);
//                canvas.drawPath(mPath, LinePaint);
//                mPath.reset();

            }
        }
    }

    /**
     * 得到刻度线，对应的y值的集合
     */
    public void initCalibrationYValue() {
        //Log.d("yu", " initCalibrationYValue   " + System.currentTimeMillis()+"axisLineList "+axisLineList.size());
        CalibrationYValue.clear();
        if (axisLineList != null && axisLineList.size() > 0) {
            for (int i = 0; i < axisLineList.size(); i++) {
                CalibrationYValue.add(getDataPointHeigh(axisLineList.get(i)));

                //Log.d("yu", " initCalibrationYValue   " + System.currentTimeMillis()+" Y "+ getDataPointHeigh
                // (axisLineList.get(i)));

            }
        }

    }

    /**
     * 画折线上的点
     *
     * @param canvas
     * @param point
     * @param index  画第几条线折线上的点
     */
    private void drawCircle(Canvas canvas, ArrayList<Float> point, int index) {

        for (int i = 0; i < point.size(); i++) {

            circlePaint.setColor(getmGesturePaintColor(index));

            final float previousX = getValueEachPoint(i);
            final float previousY = getDataPointHeigh(point.get(i));
            radius = dp2px_3;
            if (i == 0) {
                if (linkid == 0) {
                    radius = dp2px_5;
                } else {
                }
            }
            if (i != point.size()) {
                if (i == linkid) {
                    radius = dp2px_5;
                } else {
                }
            }

            canvas.drawCircle(previousX, previousY, radius, circlePaint);
            //Log.d("yu","  previousX "+  previousX+"            previousY "+previousY);

        }
    }

    public int getPaddingTop() {
        return dip2px(getContext(), 25);
        //return (int)xfont_height;
    }

    //要展示在折现上的点的y值
    private float getDataPointHeigh(float i) {
        if (maxValue < i) {
            i = maxValue;

        }
        if (i < 0) {
            i = 0;
        }
        //return (int) ((maxValue - i) * temspace) + getPaddingTop();
        return (float) ((maxValue - i) * temspace) + (float) font_height_online + dip2px(getContext(),
                paddingBwtweenTextAndLine);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float X = event.getX();
        float Y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                TouchItem(X, Y);
                mIntegerIntegerHashMap.clear();
                break;
            case MotionEvent.ACTION_MOVE:
                float x_move = event.getX();
                float y_move = event.getY();
                if ((Math.abs(X - x_move) > 50) || Math.abs(Y - y_move) > 50) {

                }
                //LinearLayout linearLayout = (LinearLayout) getParent();
                //linearLayout.scrollBy((int)xAxisStart,0);
                break;
            case MotionEvent.ACTION_UP:
                //if (showHighLightEffect) {
                float diffx = event.getRawX() - (getScreenWidth() / 2);
                if (diffx > 0) {
                    getScrollView().smoothScrollBy((int) diffx, 0);
                } else {
                    getScrollView().smoothScrollBy((int) diffx, 0);
                }
                //}

                //Log.d("yu", diffx + "  " + event.getRawX() + "    " + (getScreenWidth() / 2) + "   " +
                //        customScrollView.getScrollX());

                //Log.d("yu", "ACTION_UP onTouchEvent  " + getWidth());

                break;
        }
        return true;

    }

    /**
     * 图表中获取对应的点的x值
     *
     * @param index point的索引
     * @return
     */
    private float getValueEachPoint(int index) {
        //xSpace * index   共有几个间距;   xfont_width  * index 共有几个x轴上面的文字长度;   xfont_width  / 2) 当前索引值下面的x轴文案长度的一半
        return (xAxisStart + xSpace * index + xfont_width * index + xfont_width / 2);
    }

    public void TouchItem(float x, float y) {

        for (int index = 0; index < firstPointArray.size(); index++) {
            float xd = x - getValueEachPoint(index);
            if (Math.abs(xd) < 60) {
                Log.d("点击", "我点了。。   index" + index);
                if (mOnPointClick != null) {
                    mOnPointClick.OnPointClickevent(index);
                }
                //if (!showHighLightEffect) {
                //    return;
                //}
                linkid = index;
                Log.d("点击", "我点了。。linkid" + linkid);
                invalidate();
                return;
            }

        }

    }

    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay()
                .getWidth();
        int height = wm.getDefaultDisplay()
                .getHeight();
        return width;
    }

    /**
     * 获得字体宽w
     *
     * @param paint
     * @param str
     * @return 输入字符串的宽度
     */
    private int getFontWidth(Paint paint, String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        Rect rect = new Rect();
        int length = str.length();
        paint.getTextBounds(str, 0, length, rect);
        return rect.width();
    }

    /**
     * 获得画笔TextSize
     *
     * @param paint
     * @param str
     * @param len
     * @return
     */
    private float getTextSize(Paint paint, String str, float len) {
        float size = paint.getTextSize();
        boolean isout = true;
        while (isout) {
            size--;
            paint.setTextSize(size);
            if (getFontWidth(paint, str) < len) {
                isout = false;
                return size;
            }
        }
        return 0;
    }

    /**
     * 获得字体高度
     *
     * @param paint
     * @return 输入字符串的高度
     */
    private int getFontHeight(Paint paint, String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    /**
     * 获得由dip数值转化而来的px数值
     *
     * @param context 上下文
     * @param dpValue dip值
     * @return px数值
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获得由px数值转化而来的dip数值
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dip数值
     */
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public int getmGesturePaintColor(int index) {

        if (index < colorList.size()) {
            return colorList.get(index);
        }
        return Color.YELLOW;

    }

    public void setColorList(ArrayList list) {
        if (colorList == null) {
            colorList = new ArrayList();
        }
        colorList.clear();
        colorList.addAll(list);
    }

    public int getmTopWePaintColor() {
        if (mTopWePaintColor == 0) {
            return Color.WHITE;
        } else {
            return mTopWePaintColor;
        }

    }

    public void registListener() {
        getScrollView().setmCallBackScrollStop(this);
    }

    private CustomScrollView getScrollView() {
        if (customScrollView == null) {
            customScrollView = (CustomScrollView) getParent().getParent();
        }
        return customScrollView;

    }

    //假如图标绘制多条线的时候，
    public void setShowWhichLine(ArrayList<Boolean> whichShow) {
        showWhichLine.clear();
        if (pointList.size() > 0 && whichShow.size() != pointList.size()) {
            throw new RuntimeException("传入的判定条件，与折现的数量不符 points size" + whichShow.size() + "      pointList size: " + pointList.size());

        }
        showWhichLine.addAll(whichShow);
        //invalidate();

    }

    /**
     * 横向滚动view 滚动结束 回调
     */
    @Override
    public void scrollStop() {
        if (!showHighLightEffect) {
            return;
        }

        mIntegerIntegerHashMap.clear();

        getScrollView().setmCallBackScrollStop(this);

        int x = customScrollView.getScrollX() + (getScreenWidth() / 2);

        for (int index = 0; index < firstPointArray.size(); index++) {
            float xd = x - getValueEachPoint(index);
            //Log.d("yu", "计算的距离。。" + index + "     " + xd);

            mIntegerIntegerHashMap.add(Math.abs(xd));
            int size = mIntegerIntegerHashMap.size();
            if (size >= 3) {
                //Log.d("yu", "left" + mIntegerIntegerHashMap.get(size - 3) + "    mid  " + mIntegerIntegerHashMap.get
                //        (size - 2) + "right " + mIntegerIntegerHashMap.get(size - 1));
                if (mIntegerIntegerHashMap.get(size - 2) < mIntegerIntegerHashMap.get(size - 1) &&
                        mIntegerIntegerHashMap.get(size - 2) < mIntegerIntegerHashMap.get(size - 3)) {
                    linkid = size - 2;
                    //Log.d("yu", "linkid。。" + linkid);
                }

                invalidate();
            }
        }
    }

    /**
     * 设置y坐标值的集合，用于显示平行于x轴的那些线
     */
    public void setShowAxisList(ArrayList list) {
        if (axisLineList == null) {
            axisLineList = new ArrayList();
        }
        axisLineList.addAll(list);

    }


    public void setShowIntegerText(boolean b) {
        isShowIntegerText = b;
    }

    public interface OnPointClick {
        public void OnPointClickevent(int index);
    }
}
