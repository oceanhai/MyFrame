package com.myframe.www.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import com.myframe.www.R;

/**
 * Canvas之图片文字
 * 参考
 * https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/%5B04%5DCanvas_PictureText.md
 */
public class CanvasView02 extends View{

    private Paint textPaint ;
    // 1.创建Picture
    private Picture mPicture = new Picture();
    private Context mContext;
    private int mWidth,mHeight;

    public CanvasView02(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new Paint();                // 创建画笔
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小

        mContext = context;
        // 3.在使用前调用(我在构造函数中调用了)
        recording();    // 调用录制
    }

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1.使用Picture提供的draw方法绘制
         */
        //TODO android:hardwareAccelerated="false" 注意要关掉硬件加速
        // 将Picture中的内容绘制在Canvas上
//        mPicture.draw(canvas);

        /**
         * 2.使用Canvas提供的drawPicture方法绘制
         */
////        canvas.drawPicture(mPicture, new RectF(0,0,mPicture.getWidth(),200));//TODO 高 缩放
//        canvas.drawPicture(mPicture, new RectF(0,0,mPicture.getWidth(),mPicture.getHeight()));

        /**
         * 3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
         */
//        PictureDrawable drawable = new PictureDrawable(mPicture);
////        drawable.setBounds(0,0,250,mPicture.getHeight());//TODO 不会缩放
//        drawable.setBounds(0,0,mPicture.getWidth(),mPicture.getHeight());
//        drawable.draw(canvas);

        //------------------------------------------------------------------
        /**
         * (2)drawBitmap
         */
        Bitmap bitmap = null;

        //资源文件(drawable/mipmap/raw):
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.f4);

        //资源文件(assets):
//        try {
//            InputStream inputStream = mContext.getAssets().open("f4.jpg");
//            bitmap = BitmapFactory.decodeStream(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //内存卡文件:
//        bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");

//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());
        //绘制时指定了图片左上角的坐标(距离坐标原点的距离)
//        canvas.drawBitmap(bitmap, 100,100,new Paint());

//        // 将画布坐标系移动到画布中央
//        canvas.translate(mWidth/2, mHeight/2);
//        // 指定图片绘制区域(左上角的四分之一)
//        Rect src = new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight()/2);
//        // 指定图片在屏幕上显示的区域
//        Rect dst = new Rect(0, 0, 200, 400);
//        // 绘制图片
//        canvas.drawBitmap(bitmap, src, dst ,null);

        /**
         * 2.绘制文字
         */
        /**
         *  第一类
         public void drawText (String text, float x, float y, Paint paint)
         public void drawText (String text, int start, int end, float x, float y, Paint paint)
         public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
         public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
         */
        // 文本(要绘制的内容)
//        String str = "ABCDEFGHIJK";
//        char[] chars = str.toCharArray();
//        canvas.drawText(str,100,200,textPaint);
//        canvas.drawText(str,1,3,100,300,textPaint);
//        canvas.drawText(chars,1,3,100,400,textPaint);

        /**
         * 第二类
         public void drawPosText (String text, float[] pos, Paint paint)
         public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
         */
        String str = "SLOOP";

        canvas.drawPosText(str,new float[]{
                100,100,    // 第一个字符位置
                200,200,    // 第二个字符位置
                300,300,    // ...
                400,400,
                500,500
        },textPaint);


        /**
         * 第三类
         public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
         public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
         */
    }
}
