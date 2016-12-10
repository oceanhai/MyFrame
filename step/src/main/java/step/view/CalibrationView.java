package step.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by yuwentao on 16/5/27.
 */
public class CalibrationView extends View {


    /**
     * 控件高度按最大值，等分之后，单位高度
     */
    double temspace = 0;

    //平行于x轴的刻度线，存放y轴坐标值
    private ArrayList<Float> axisLineList;


    //平行于x轴的刻度线，存放用于展示的值
    private ArrayList<Float> axisLineValueList = new ArrayList<>();


    //在折线 和刻度线上的文字是否显示整形，false显示浮点型
    private boolean isShowIntegerText = true;

    private float font_height_online = 0;

    private Context mContext;

    public CalibrationView(final Context context) {
        super(context);
        this.mContext = context;
    }

    public CalibrationView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxisLine(canvas);
    }

    /**
     * 画平行于x轴的刻度线线
     */
    private void drawAxisLine(Canvas canvas) {

        if (axisLineList != null && axisLineList.size() > 0) {

            for (int i = 0; i < axisLineList.size(); i++) {
                float previousX = 0;
                float previousY = axisLineList.get(i);
                Log.d("yu", "CalibrationView      drawAxisLine            previousX " + previousX + "       previousY" +
                        " " + previousY);

                //画刻度线上的文字
                Paint paint = new Paint();
                paint.setTextSize(15);
                paint.setColor(Color.parseColor("#4BFFFFFF"));
                //int textHigh = getFontWidth(paint, axisLineList.get(i)+"");
                String text = "";
                if (isShowIntegerText) {
                    text = axisLineValueList.get(i)
                            .intValue() + "";
                } else {
                    text = axisLineValueList.get(i) + "";
                }
                canvas.drawText(text, 0, previousY, paint);
            }
        }
    }

    public void setShowIntegerText(boolean b) {
        isShowIntegerText = b;
    }

    /**
     * 设置y坐标值的集合，用于显示平行于x轴的那些刻度线
     */
    public void setShowAxisList(ArrayList list) {
        Log.d("yu", " setShowAxisList   " + System.currentTimeMillis() + "   " + list.size());
        if (axisLineList == null) {
            axisLineList = new ArrayList();
        }
        axisLineList.clear();
        axisLineList.addAll(list);
    }

    /**
     * 折线图 x轴数据
     *
     * @param axisLineValueList
     */
    public void setAxisLineValueList(final ArrayList<Float> axisLineValueList) {
        this.axisLineValueList.clear();
        this.axisLineValueList.addAll(axisLineValueList);
    }

}
