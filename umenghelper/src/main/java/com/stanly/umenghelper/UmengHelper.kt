package com.stanly.umenghelper

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.stanly.umenghelper.enumerate.SharePlatfromEnum
import com.stanly.umenghelper.listener.OnShareBoardClickListener
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.PlatformName
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMWeb

class UmengHelper(context: Context) {

    private var mContext: Context = context

    companion object {
        init {
            PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0")
            //豆瓣RENREN平台目前只能在服务器端配置
            PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com")
            PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf")
            PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba")
            PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO")
            PlatformConfig.setAlipay("2015111700822536")
            PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e")
            PlatformConfig.setPinterest("1439206")
            PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f")
            PlatformConfig.setDing("dingoalmlnohc0wggfedpk")
            PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J")
            PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a")
        }
    }

    /**
     *
     */
    fun doShare(dialog:Dialog,content: UShareContent){

    }


    fun doShareWeb(content: UShareContent) {
        val web = UMWeb(content.url)
        web.title = content.title
        web.description = content.content
//        ShareDialog.Builder(mContext).setPageSize(8).setRowSize(4).addAllPlatForm().setOnShareBoardClickListener(object : OnShareBoardClickListener {
//            override fun onShareBoardClick(platform: SharePlatform) {
//                when (platform.id) {
//                    SharePlatfromEnum.WECHAT -> {
//                        println("11111111111111111111111")
//                        ShareAction(mContext as Activity?).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(object : UMShareListener {
//                            override fun onResult(p0: SHARE_MEDIA?) {
//                                println("开始分享...")
//                            }
//
//                            override fun onCancel(p0: SHARE_MEDIA?) {
//                                println("开始分享...")
//                            }
//
//                            override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
//                                println("开始分享...")
//                            }
//
//                            override fun onStart(p0: SHARE_MEDIA?) {
//                                println("开始分享...")
//                            }
//                        })
//                    }
//                    SharePlatfromEnum.QQ -> TODO()
//                    SharePlatfromEnum.SINA -> TODO()
//                    SharePlatfromEnum.WECHAT_CIRCLE -> TODO()
//                    SharePlatfromEnum.QZONE -> TODO()
//                    SharePlatfromEnum.OPEN_IN_BROWSER -> TODO()
//                    SharePlatfromEnum.COPY_URL -> TODO()
//                    SharePlatfromEnum.COPY_TEXT -> TODO()
//                }
//            }
//
//        }).setOnDismissListener(object : ShareDialog.OnDismissListener {
//            override fun onDismiss(view: View) {
//
//            }
//
//        }).create().show()

        //        ShareAction(activity).withMedia(web)
    }


}
