package step.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

/**
 * Created by yuwentao on 16/5/13.
 */
public class CustomScrollView extends HorizontalScrollView {
    public callBackScrollStop mCallBackScrollStop;
    Scroller scroller;
    private long lastScrollUpdate = -1;
    private int lastx = 0;
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(final Message msg) {
            if (lastx == getScrollX()) {
                //Log.d("yu", "stopScrolX           " + getScrollX());
                mCallBackScrollStop.scrollStop();
            } else {
                mHandler.sendEmptyMessage(0);

            }
        }
    };

    public CustomScrollView(final Context context) {
        super(context);
    }

    public CustomScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
    }

    public CustomScrollView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setmCallBackScrollStop(callBackScrollStop callback) {
        mCallBackScrollStop = callback;
    }

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.d("yu",l+"   "+t+" "+oldl+"   "+oldt+  "    "+scroller.isFinished());

        if (Math.abs(l - oldl) >= 2) {

        } else {
            mCallBackScrollStop.scrollStop();
        }

        if (scroller.isFinished()) {

        }
        //
        //if (lastScrollUpdate == -1) {
        //    postDelayed(new ScrollStateHandler(), 100);
        //}
        //
        //lastScrollUpdate = System.currentTimeMillis();
    }


    //@Override
    //public boolean onTouchEvent(final MotionEvent ev) {
    //    super.onTouchEvent(ev);
    //
    //    switch (ev.getAction()) {
    //
    //        case MotionEvent.ACTION_UP:
    //
    //            lastx = getScrollX();
    //            mHandler.sendEmptyMessage(0);
    //            Log.d("yu", "ACTION_UP onTouchEvent  " + getWidth() + "    getscrolly" + getScrollX());
    //
    //            break;
    //    }
    //    return false;
    //}

    public interface callBackScrollStop {
        public void scrollStop();
    }
}
