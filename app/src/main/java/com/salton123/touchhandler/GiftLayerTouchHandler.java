package com.salton123.touchhandler;

import android.view.MotionEvent;

import com.salton123.touchbus.AbstractTouchEventHandler;
import com.salton123.touchbus.TouchEventHandler;
import com.salton123.touchview.GiftClickView;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/5 11:30
 * ModifyTime: 11:30
 * Description:
 */
public class GiftLayerTouchHandler extends AbstractTouchEventHandler<GiftClickView> {
    @Override
    public boolean onTouch(GiftClickView giftClickView, MotionEvent e, boolean hasBeenIntercepted) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            giftClickView.onButtonClick();
        }
        return true;
    }

    @Override
    public void defineNextHandlers(List<Class<? extends TouchEventHandler>> handlers) {
        // if u dont c
        // handlers.add(BusinessLayerTouchHandler.class);
    }
}
