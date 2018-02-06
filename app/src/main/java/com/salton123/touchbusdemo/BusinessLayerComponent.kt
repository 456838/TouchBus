package com.salton123.touchbusdemo

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.touchbus.TouchEventBus
import com.salton123.touchhandler.BusinessLayerTouchHandler
import com.salton123.touchview.BusinessTouchView

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/5 19:25
 * ModifyTime: 19:25
 * Description:
 */

class BusinessLayerComponent : BaseSupportFragment(), BusinessTouchView {
    override fun onTouch() {
        toast("onTouch")
    }

    override fun GetLayout(): Int {
        return R.layout.cp_business_layer
    }

    override fun InitViewAndData() {
    }

    override fun InitListener() {
        TouchEventBus.of(BusinessLayerTouchHandler::class.java).attach(this)
    }

    override fun InitVariable(savedInstanceState: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        TouchEventBus.of(BusinessLayerTouchHandler::class.java).dettach(this)
    }

}