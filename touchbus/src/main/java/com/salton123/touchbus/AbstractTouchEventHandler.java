package com.salton123.touchbus;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本触摸事件处理框架 绑定指定类型的{@link TouchViewHolder} mViewHolder和 {@link TouchEventHandler} mNextHandler
 */
public abstract class AbstractTouchEventHandler<T> implements TouchEventHandler<T, TouchViewHolder<T>> {
    protected List<Class<? extends TouchEventHandler>> mHandlers = new ArrayList<>();
    protected TouchViewHolder<T> mViewHolder = new TouchViewHolder<>();

    public AbstractTouchEventHandler() {
        defineNextHandlers(mHandlers);
    }

    @Override
    public TouchViewHolder<T> getViewHolder() {
        return mViewHolder;
    }

    @Override
    public List<Class<? extends TouchEventHandler>> nextHandler() {
        return mHandlers;
    }

    public abstract void defineNextHandlers(List<Class<? extends TouchEventHandler>> handlers);
}
