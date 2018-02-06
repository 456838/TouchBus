package com.salton123.touchbus;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 接收所有触摸事件，并分发给{@link TouchEventBus}，万一这个View收不到事件了，所有依赖于{@link TouchEventBus}的控件全会gg
 */
public class MobileLiveRecieveTouchView extends RelativeLayout {

    public static final String TAG = "TouchEventHandler";
    private Context mContext;
    private boolean mEnable = true;

    public MobileLiveRecieveTouchView(Context context) {
        super(context);
        init(context);
    }

    public MobileLiveRecieveTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MobileLiveRecieveTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MobileLiveRecieveTouchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //MLog.info(TAG, "dispatchTouchEvent x = " + event.getX() + " y = " + event.getY() + " rawX = " + event.getRawX() + " rawY = " + event.getRawY());
        TouchEventBus.dispatchTouchEvent(event, this);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mEnable;
    }
}
