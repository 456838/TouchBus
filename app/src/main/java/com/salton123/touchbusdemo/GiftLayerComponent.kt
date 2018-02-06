package com.salton123.touchbusdemo

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.touchbus.TouchEventBus
import com.salton123.touchhandler.GiftLayerTouchHandler
import com.salton123.touchview.GiftClickView
import kotlinx.android.synthetic.main.cp_gift_layer.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/5 11:15
 * ModifyTime: 11:15
 * Description:
 */
class GiftLayerComponent : BaseSupportFragment(), GiftClickView {
    override fun onButtonClick() {
        ivClickMe.performClick()
    }

    override fun GetLayout(): Int {
        return R.layout.cp_gift_layer
    }

    override fun InitViewAndData() {
        ivClickMe.setOnClickListener { toast("u win!") }
    }

    override fun InitListener() {

    }

    override fun InitVariable(savedInstanceState: Bundle?) {
        TouchEventBus.of(GiftLayerTouchHandler::class.java).attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        TouchEventBus.of(GiftLayerTouchHandler::class.java).dettach(this)
    }
}