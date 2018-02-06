package com.salton123.touchbusdemo

import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.base.BaseSupportFragment

/**
 * i define three layers to handle touchEvent
 *  normally we can not touch the GiftLayer
 *  while using touchbus miracles happen
 */
class DemoActivity : BaseSupportActivity() {
    override fun GetLayout(): Int {
        return R.layout.activity_demo
    }

    override fun InitViewAndData() {
        loadRootFragment(R.id.videoLayer, BaseSupportFragment.newInstance(VideoLayerComponent::class.java))
        loadRootFragment(R.id.giftLayer, BaseSupportFragment.newInstance(GiftLayerComponent::class.java))
        loadRootFragment(R.id.businessLayer, BaseSupportFragment.newInstance(BusinessLayerComponent::class.java))
    }

    override fun InitListener() {
    }

    override fun InitVariable(savedInstanceState: Bundle?) {
    }


}
