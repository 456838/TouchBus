package com.salton123.touchbus;

import android.view.MotionEvent;


import java.util.List;

public interface TouchEventHandler<VIEW, HOLDER extends TouchViewHolder<VIEW>> {

    boolean onTouch(VIEW view, MotionEvent e, boolean hasBeenIntercepted);

    List<Class<? extends TouchEventHandler>> nextHandler();

    HOLDER getViewHolder();
}
