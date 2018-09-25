package com.stanly.umenghelper

import android.app.Activity
import android.content.Context
import com.stanly.umenghelper.enumerate.ShareCategoryEnum
import com.stanly.umenghelper.listener.ShareListener
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareAPI.init
import com.umeng.socialize.media.UMImage
import kotlin.properties.Delegates

class UmengHelper {

    private var mShareAction: ShareAction? = null
    private var mContext: Context

    constructor(context: Context) {
        this.mContext = context
    }

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

    fun setShareAction(content: UShareContent): UmengHelper {
        mShareAction = mShareAction ?: ShareAction(mContext as Activity)
        when (content.shareCategoryEnum) {
            ShareCategoryEnum.TEXT -> {
                mShareAction?.withText(content.content)
            }
            ShareCategoryEnum.LOCAL_IMAGE -> {
            }
            ShareCategoryEnum.NET_IMAGE -> {
            }
            ShareCategoryEnum.TEXT_IMAGE -> TODO()
            ShareCategoryEnum.MUSIC -> TODO()
            ShareCategoryEnum.VIDEO -> TODO()
            ShareCategoryEnum.WEB -> TODO()
            ShareCategoryEnum.MULTI_IMAGE -> TODO()
        }
        return this
    }


}
