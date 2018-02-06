package com.salton123.touchbus;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 用于对{@link TouchEventHandler}根据 {@link TouchEventHandler#nextHandler()}来进行排序
 */
class TouchEventHandlerContainer {
    private static final String TAG = "TouchEventHandler";
    private Map<Class<? extends TouchEventHandler>, TouchEventHandler> mHandlers = new HashMap<>();
    private List<TouchEventHandler> mContainer = new ArrayList<>();

    /**
     * 拓扑排序
     */
    void put(TouchEventHandler handler) {
        if (handler == null) {
            return;
        }

        Iterator<Map.Entry<Class<? extends TouchEventHandler>, TouchEventHandler>> it = mHandlers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Class<? extends TouchEventHandler>, TouchEventHandler> entry = it.next();
            if (entry.getValue().getViewHolder().getView().isEmpty()) {
                Log.i(TAG, "remove " + entry.getValue());
                mContainer.remove(entry.getValue());
                it.remove();
            }
        }

        Map<Class<? extends TouchEventHandler>, Integer> handlerCnt = new HashMap<>();
        mHandlers.put(handler.getClass(), handler);
        mContainer.add(handler);
        for (TouchEventHandler h : mContainer) {
            handlerCnt.put(h.getClass(), 0);
        }
        for (TouchEventHandler h : mContainer) {
            List<Class<? extends TouchEventHandler>> clzs = h.nextHandler();
            if (clzs != null) {
                for (Class<? extends TouchEventHandler> cls : clzs) {
                    if (cls != null && mHandlers.containsKey(cls)) {
                        int cnt = handlerCnt.get(cls);
                        handlerCnt.put(cls, cnt + 1);
                    }
                }
            }
            Log.i(TAG, "handlerCnt = " + handlerCnt);
        }
        mContainer = new ArrayList<>();
        while (handlerCnt.size() > 0) {
            Class<? extends TouchEventHandler> hasKey = null;
            List<Class<? extends TouchEventHandler>> nextKey = null;
            for (Map.Entry<Class<? extends TouchEventHandler>, Integer> e : handlerCnt.entrySet()) {
                if (e.getValue() <= 0) {
                    hasKey = e.getKey();
                    TouchEventHandler h = mHandlers.get(hasKey);
                    if (h != null) {
                        mContainer.add(h);
                        nextKey = h.nextHandler();
                    }
                    break;
                }
            }

            if (hasKey != null) {
                handlerCnt.remove(hasKey);
            } else {
                throw new IllegalStateException("TouchHandler的事件分发存在环路，请检查nextHandler");
            }

            if (nextKey != null) {
                for (Class<? extends TouchEventHandler> nk : nextKey) {
                    if (nk != null && mHandlers.containsKey(nk)) {
                        int cnt = handlerCnt.get(nk);
                        handlerCnt.put(nk, cnt - 1);
                    }
                }
            }
        }
        Log.i(TAG, "order list = " + mContainer);
    }

    public void remove(TouchEventHandler handler) {
        if (handler != null) {
            // mContainer.remove(handler);
            mHandlers.remove(handler.getClass());
        }
    }

    List<TouchEventHandler> getOrderTouchEventHandler() {
        return mContainer;
    }

    TouchEventHandler getHandler(Class<? extends TouchEventHandler> cls) {
        return mHandlers.get(cls);
    }
}
