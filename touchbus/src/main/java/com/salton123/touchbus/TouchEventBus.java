package com.salton123.touchbus;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * 开播端直播间的触摸消息总线，负责把消息按顺序分发给TouchEventHandler
 */
public class TouchEventBus {
    private static final String TAG = "TouchEventHandler";
    private static TouchEventBus mBus = new TouchEventBus();
    private TouchEventHandlerContainer mContainer = new TouchEventHandlerContainer();

    private static TouchEventBus instance() {
        return mBus;
    }

    static <VIEW> void dispatchTouchEvent(MotionEvent e, View parentView) {
        List<TouchEventHandler> orderList = instance().mContainer.getOrderTouchEventHandler();
        e.offsetLocation(parentView.getScrollX() + e.getRawX() - e.getX(), parentView.getScrollY() + e.getRawY() - e.getY());
        boolean intercepted = false;
        for (TouchEventHandler h : orderList) {
            boolean intercepteChild = intercepted;
            MotionEvent copyEvent = MotionEvent.obtain(e);
            TouchViewHolder<VIEW> vh = h.getViewHolder();
            for (VIEW v : vh.getView()) {
                intercepteChild = h.onTouch(v, copyEvent, intercepted) || intercepteChild;
            }
            intercepted = intercepteChild;
            copyEvent.recycle();
        }
    }

    public static <VIEW> TouchViewHolder<VIEW> of(Class<? extends TouchEventHandler<VIEW, TouchViewHolder<VIEW>>> cls) {
        TouchEventHandler handler = instance().mContainer.getHandler(cls);
        if (handler == null) {
            try {
                handler = cls.newInstance();
                instance().mContainer.put(handler);
            } catch (InstantiationException e) {
                Log.e(TAG, "ex", e);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "ex", e);
            }
        }
        return handler.getViewHolder();
    }
}
