package com.xywy.component.uimodules.menu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xywy.component.R;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by shijiazi on 16/2/22.
 * 自定义上下文菜单，仿ios版本
 */
public class CustomContextMenu extends ViewGroup {

    private final Rect mTempRect = new Rect();//anchorview 在父窗口中的位置
    private final int[] mTempLocation = new int[2];
    private View mView;
    private int mTextResId = R.layout.custom_contxtmenu_textview;
    private LinearLayout mMenuLayout;
    private int myWidth = 0;
    private int myHeight = 0;
    private WeakReference<View> mViewAnchor;
    private Rect mViewRect = new Rect();
    ;

    private Callback mCallback;

    private List<MenuData> mMenuList;

    private CustomContextMenu(Context context) {
        super(context);
        initView();
    }

    public CustomContextMenu(Context context, View anchor, Callback callback, List<MenuData> data) {
        super(context);
        setViewAnchor(anchor);
        setCallback(callback);
        setMenuData(data);
        initView();

    }

    public static Activity getActivity(@Nullable Context cont) {
        if (cont == null) {
            return null;
        } else if (cont instanceof Activity) {
            return (Activity) cont;
        } else if (cont instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) cont).getBaseContext());
        }
        return null;
    }

    private void setViewAnchor(View view) {
        mViewAnchor = new WeakReference<View>(view);
    }

    private void setCallback(Callback callback) {
        mCallback = callback;
    }

    private void setMenuData(List<MenuData> list) {
        mMenuList = list;
    }

    private void initView() {
        //not clip children to their bounds
        setClipChildren(false);
        //allow children to draw to padding
        setClipToPadding(false);

        LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        mView = LayoutInflater.from(getContext()).inflate(mTextResId, this, false);
        mView.setLayoutParams(params);

        mMenuLayout = (LinearLayout) mView.findViewById(R.id.menuLayout);
        mMenuLayout.setBackgroundResource(R.drawable.reader_note_arrow_up_empty);

        this.addView(mView);

        mMenuLayout.removeAllViews();
        if (mMenuList != null) {
            for (final MenuData data : mMenuList) {
                if (data != null) {
                    TextView textView = new TextView(getContext());

                    textView.setPadding(40, 20, 40, 20);
                    textView.setTextColor(Color.parseColor("#F4F4F4"));

                    textView.setText(data.getText());
                    textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mCallback != null) {
                                mCallback.onClick(data);
                            }
                            hide();
                        }
                    });

                    mMenuLayout.addView(textView);
                }
            }
        }

    }

    public void show() {
        if (getParent() == null) {
            final Activity act = getActivity(getContext());
            LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
            if (act != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) (act.getWindow().getDecorView());
                rootView.addView(this, params);
            }
        }
    }

    public void hide() {
        final Activity act = getActivity(getContext());
        if (act != null) {
            ViewGroup rootView;
            rootView = (ViewGroup) (act.getWindow().getDecorView());
            for (int i = 0; i < rootView.getChildCount(); i++) {
                final View child = rootView.getChildAt(i);
                if (child instanceof CustomContextMenu) {
                    ((CustomContextMenu) child).removeFromParent();
                }
            }
        }
    }

    private void removeFromParent() {
        ViewParent parent = getParent();
        removeCallbacks();

        if (null != parent) {
            ((ViewGroup) parent).removeView(CustomContextMenu.this);
        }
    }

    private void removeCallbacks() {
        mCallback = null;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (null != mView) {
            mView.layout(mView.getLeft(), mView.getTop(), myWidth, myHeight);
        }

        if (changed) {
            if (mViewAnchor != null) {
                View view = mViewAnchor.get();
                if (null != view) {
                    view.getHitRect(mTempRect);
                    view.getLocationOnScreen(mTempLocation);

                    Rect textRect = new Rect();
                    mMenuLayout.getHitRect(textRect);

                    mTempRect.offsetTo(mTempLocation[0], mTempLocation[1]);
                    mViewRect.set(mTempRect);

                    int textWidth = mTempRect.centerX() - textRect.width() / 2;
                    if (textWidth < 0) {
                        textWidth = 0;
                    } else if (mTempRect.centerX() + textRect.width() / 2 > myWidth) {
                        textWidth = myWidth - textRect.width();
                    }

                    mMenuLayout.setTranslationX(textWidth);

                    int textHeight = mTempRect.top - textRect.height();
                    if (textHeight < 0) {
                        textHeight = mTempRect.bottom;
                        mMenuLayout.setBackgroundResource(R.drawable.reader_note_arrow_up_empty);
                    } else {
                        mMenuLayout.setBackgroundResource(R.drawable.reader_note_arrow_down_empty);
                    }

                    mMenuLayout.setTranslationY(textHeight);

                }
            }
            calculatePositions();
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // Record our dimensions if they are known;
        if (widthMode != MeasureSpec.UNSPECIFIED) {
            myWidth = widthSize;
        }

        if (heightMode != MeasureSpec.UNSPECIFIED) {
            myHeight = heightSize;
        }

        if (null != mView) {
            if (mView.getVisibility() != GONE) {
                mView.measure(myWidth, myHeight);
            } else {
                myWidth = 0;
                myHeight = 0;
            }
        }

        setMeasuredDimension(myWidth, myHeight);
    }

    @Override
    public boolean onTouchEvent(@NonNull final MotionEvent event) {
        if (!isShown()) {
            return false;
        }

        final int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {

            hide();
            return true;
        }
        return false;
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks();
        mViewAnchor = null;
        super.onDetachedFromWindow();
    }

    private void calculatePositions() {

    }

    public interface Callback {

        void onClick(MenuData data);

    }
}
