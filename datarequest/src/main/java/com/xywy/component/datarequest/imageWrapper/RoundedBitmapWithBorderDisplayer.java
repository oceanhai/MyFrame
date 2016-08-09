package com.xywy.component.datarequest.imageWrapper;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * Created by shijiazi on 16/1/13.
 */
public class RoundedBitmapWithBorderDisplayer implements BitmapDisplayer {

    protected final int borderThickness;

    protected final int borderColor;

    public RoundedBitmapWithBorderDisplayer() {
        this(0, Color.BLACK);
    }

    public RoundedBitmapWithBorderDisplayer(int borderThickness) {
        this(borderThickness, Color.BLACK);
    }

    public RoundedBitmapWithBorderDisplayer(int borderThickness, int borderColor) {
        this.borderThickness = borderThickness;
        this.borderColor = borderColor;
    }

    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (!(imageAware instanceof ImageViewAware)) {
            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
        }

        imageAware.setImageDrawable(new RoundedDrawable(bitmap, borderThickness, borderColor));
    }

    public static class RoundedDrawable extends Drawable {

        protected final int margin;

        protected final RectF mRect = new RectF(),
                mBitmapRect, mBackgroundRect;
        protected final BitmapShader bitmapShader;
        protected final Paint paint, backPaint;

        public RoundedDrawable(Bitmap bitmap, int margin, int color) {
            this.margin = margin;

            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mBitmapRect = new RectF (margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);
            mBackgroundRect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);

            backPaint = new Paint();
            backPaint.setColor(color);
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            mRect.set(margin, margin, bounds.width() - margin, bounds.height() - margin);

            Matrix shaderMatrix = new Matrix();
            shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.FILL);
            bitmapShader.setLocalMatrix(shaderMatrix);

        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawCircle(mRect.centerX(), mRect.centerY(), mRect.width()/2+margin, backPaint);
            canvas.drawCircle(mRect.centerX(), mRect.centerY(), mRect.width()/2, paint);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }
    }
}
