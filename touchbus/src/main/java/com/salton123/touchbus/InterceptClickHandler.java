package com.salton123.touchbus;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * 该处理器会把构造函数传入的参数ViewGroup上的点击事件拦截并不作处理
 * <p>使用例子：</p>
 * <p>开播预览页{@link PreviewForShowClickHandler}需要判断是否是控件上的点击，让用户点击该页面控件的时候，{@link CameraClickHandler}不会收到点击事件，从而不会表现出摄像头对焦</p>
 */
public abstract class InterceptClickHandler extends AttachToViewTouchEventHandler<ViewGroup> {
    private static final String TAG = "TouchEventHandler";

    @Override
    public boolean onTouch(ViewGroup v, MotionEvent e, boolean hasBeenIntercepted, boolean insideView) {
        return hasBeenIntercepted
                || insideView
                && e.getAction() == MotionEvent.ACTION_UP
                && performClick(v, e);
    }

    protected boolean performClick(ViewGroup vg, MotionEvent e) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            if (TouchEventHandlerUtil.isOnView(e, v)) {
                if (v.isClickable() || v.isLongClickable()) {
                    Log.i(TAG, "PreviewForShowClickHandler hit v = " + v);
                    return true;
                } else if (v instanceof ViewGroup) {
                    if (performClick((ViewGroup) v, e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
