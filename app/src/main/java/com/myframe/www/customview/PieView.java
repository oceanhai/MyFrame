package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import www.wuhai.common.utils.L;

/**
 * 饼图
 */
public class PieView extends View {
    private static final String TAG = "PieView";

    private Paint mPaint = new Paint();

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private List<PieData> mData;
    private float mStartAngle = 0;
    private int mWidth, mHeight;

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.e(TAG, "onDraw执行了一次");
        if(mData == null){
            return;
        }
        float currentStarAngle = mStartAngle;
        //translate是坐标系的移动，可以为图形绘制选择一个合适的坐标系。 请注意，位移是基于当前位置移动，
        // 而不是每次基于屏幕左上角的(0,0)点移动
        canvas.translate(mWidth/2, mHeight/2);
        float r = (float) (Math.min(mWidth, mHeight)/2 *0.8);
        RectF rectf = new RectF(-r,-r,r,r);
        for(int x=0;x<mData.size();x++){
            PieData data = mData.get(x);
            mPaint.setColor(data.getColor());
            canvas.drawArc(rectf,currentStarAngle, data.getAngle(), true, mPaint);
            currentStarAngle+=data.getAngle();
        }

    }

    /**
     * 设置起始角度
     * @param startAngle
     */
    public void setStartAngle(float startAngle){
        this.mStartAngle = startAngle;
    }

    public void setDatas(List<PieData> data){
        this.mData = data;
        initData(mData);
        invalidate();
    }

    private void initData(List<PieData> mData) {
        if(mData == null || mData.size() == 0){
            return;
        }

        float sumValue = 0;
        for(int x=0;x<mData.size();x++){
            PieData data = mData.get(x);
            sumValue += data.getValue();
            int j = x % mColors.length;
            data.setColor(mColors[j]);
        }

        for(int y=0;y<mData.size();y++){
            PieData data = mData.get(y);
            float percentage = data.getValue()/sumValue;
            float angle = percentage * 360;
            data.setAngle(angle);
            data.setPercentage(percentage);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.e(TAG,"onMeasure执行了一次");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        L.e(TAG, "onLayout执行了一次");
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        L.e(TAG, "layout执行了一次");
    }
}
