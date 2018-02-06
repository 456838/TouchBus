package com.salton123.touchbus;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventHandlerUtil {
    private TouchEventHandlerUtil() {
    }

    /**
     * 把触摸事件修正到对应的view上 使{@link MotionEvent#getX()}和{@link MotionEvent#getY()}的相对位置是view的相对位置
     */
    public static void reviseToView(View v, MotionEvent event) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        event.offsetLocation(-location[0], -location[1]);
    }

    public static boolean isOnView(MotionEvent e, View v) {
        Rect r = new Rect();
        v.getGlobalVisibleRect(r);
        return r.left <= e.getRawX() && e.getRawX() <= r.right
                &&
                r.top <= e.getRawY() && e.getRawY() <= r.bottom;
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        float x = x1 - x2;
        float y = y1 - y2;
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 计算MotionEvent事件二点间的距离
     *
     * @return float 二点间的距离
     */
    public static float spacing(MotionEvent event) {
        if (event.getPointerCount() >= 2) {
            return distance(event.getX(0), event.getY(0), event.getX(1), event.getY(1));
        }
        return -1;
    }
}
