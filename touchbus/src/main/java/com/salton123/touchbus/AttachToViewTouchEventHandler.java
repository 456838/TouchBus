package com.salton123.touchbus;

import android.view.MotionEvent;
import android.view.View;

/**
 * 处理要依附在View上的触摸事件，可以判断事件是否发生在View上，并根据View的位置纠正点击事件的x y坐标
 */
public abstract class AttachToViewTouchEventHandler<VIEW extends View> extends AbstractTouchEventHandler<VIEW> {
    @Override
    public boolean onTouch(VIEW v, MotionEvent e, boolean hasBeenIntercepted) {
        TouchEventHandlerUtil.reviseToView(v, e);
        return onTouch(v, e, hasBeenIntercepted, TouchEventHandlerUtil.isOnView(e, v));
    }

    public abstract boolean onTouch(VIEW v, MotionEvent e, boolean hasBeenIntercepted, boolean insideView);
}
