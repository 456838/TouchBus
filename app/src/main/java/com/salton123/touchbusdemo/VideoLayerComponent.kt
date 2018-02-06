package com.salton123.touchbusdemo

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import com.salton123.base.BaseSupportFragment
import com.salton123.touchbus.TouchEventBus
import com.salton123.touchhandler.BusinessLayerTouchHandler
import com.salton123.touchview.BusinessTouchView
import com.salton123.util.log.MLog
import kotlinx.android.synthetic.main.cp_video_layer.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/5 10:35
 * ModifyTime: 10:35
 * Description:
 */
class VideoLayerComponent : BaseSupportFragment() {


    override fun GetLayout(): Int {
        return R.layout.cp_video_layer
    }

    override fun InitVariable(savedInstanceState: Bundle?) {

    }

    override fun InitViewAndData() {
        videoView.setVideoURI(getVideoPathUriFromRaw("local_video.mp4"))
        videoView.start()
        videoView.setOnPreparedListener({ mp: MediaPlayer -> mp.isLooping = true })
    }

    override fun InitListener() {
    }


    fun getVideoPathUriFromRaw(resNameWithSuffix: String): Uri {
        return Uri.parse(getVideoPathWithoutSuffix(resNameWithSuffix))
    }

    fun getVideoPathWithoutSuffix(resNameWithSuffix: String): String {
        val packageName = context.getPackageName()
        val resName = resNameWithSuffix.substring(0, resNameWithSuffix.lastIndexOf("."))
        val resId = resources.getIdentifier(resName, "raw", packageName)
        MLog.info("aa", "[getVideoPathWithoutSuffix] resName=$resName,id=$resId")
        //        return "file:///android_asset" + "/res/" + resNameWithSuffix;//*R.raw.handle_02*//*;
        return "android.resource://$packageName/$resId"//*R.raw.handle_02*//*;
    }

}