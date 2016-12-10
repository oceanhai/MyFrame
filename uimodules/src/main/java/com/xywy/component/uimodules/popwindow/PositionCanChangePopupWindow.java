package com.xywy.component.uimodules.popwindow;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.xywy.component.R;

import java.lang.reflect.Field;

/**
 * 跟随点击或者长按位置  弹PopupWindow
 *
 * @author wuhai
 *         create at 2016/2/26 15:55
 */
public class PositionCanChangePopupWindow {

    private Context mContext;
    private View mParent;
    private View mContentView;
    private PopupWindow mWindow;
    private OnClickListener mClickListener;
    private View mDelete;

    public PositionCanChangePopupWindow(Context context, View parent, final OnClickListener onClickListener) {
        mContext = context;
        mContentView = View.inflate(mContext, R.layout.read_floatingwindow2, null);
        mWindow = new PopupWindow(mContentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mWindow.setAnimationStyle(android.R.style.Animation_Toast);
        mParent = parent;
        mClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
                onClickListener.onClick(v);
            }
        };
        mDelete = mContentView.findViewById(R.id.read_fw_delete);
        mDelete.setOnClickListener(mClickListener);
    }

    private void setRelativeLayoutBackGround(boolean topOf) {
        if (topOf) {
            mContentView.setBackgroundResource(R.drawable.reader_note_arrow_down_empty);
        } else {
            mContentView.setBackgroundResource(R.drawable.reader_note_arrow_up_empty);
        }
    }

    /**
     * ※ 值操作要把data或者postion 传过来 根据想法改动回传接口
     * <p/>
     * downX - 控件高度 < 状态栏高度  朝下；反之 朝上
     * downX - 控件宽度/2 < 0  值为0;反之 正常差值
     *
     * @param downX
     * @param downY
     */
    public void showPopupWindow(float downX, float downY) {
        mContentView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int top = (int) (downY - mContentView.getMeasuredHeight());
//		System.out.println("FootprintsPopupWindow::top:"+top+"="+downY+"-"+mContentView.getMeasuredHeight());
        boolean topOf = true;
//		System.out.println("FootprintsPopupWindow::getStatusHeight:"+getStatusHeight(mContext));
        if (top < getStatusHeight(mContext)) {
            topOf = false;
            top = (int) downY;
        }
        setRelativeLayoutBackGround(topOf);
        int left = (int) (downX - mContentView.getMeasuredWidth() / 2);
//		System.out.println("FootprintsPopupWindow::left:"+left);
        mWindow.showAtLocation(mParent, Gravity.NO_GRAVITY, left < 0 ? 0 : left, top);
    }

    public void hide() {
        mWindow.dismiss();
    }

    /**
     * Status bar是launcher主界面上面提示当前状态（电池，网络，蓝牙等等）的一个栏
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    private int getStatusHeight(Context context) {
        int height = 0;
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            height = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }
}
