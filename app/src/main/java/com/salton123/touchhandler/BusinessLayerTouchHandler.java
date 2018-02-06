package com.salton123.touchhandler;

import android.view.MotionEvent;

import com.salton123.touchbus.AbstractTouchEventHandler;
import com.salton123.touchbus.TouchEventHandler;
import com.salton123.touchview.BusinessTouchView;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/5 11:30
 * ModifyTime: 11:30
 * Description:
 */
public class BusinessLayerTouchHandler extends AbstractTouchEventHandler<BusinessTouchView> {

    @Override
    public boolean onTouch(BusinessTouchView businessTouchView, MotionEvent e, boolean hasBeenIntercepted) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            businessTouchView.onTouch();
        }
        return true;
    }

    @Override
    public void defineNextHandlers(List<Class<? extends TouchEventHandler>> handlers) {

    }
}
