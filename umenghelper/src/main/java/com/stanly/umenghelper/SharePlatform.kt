package com.stanly.umenghelper

import android.support.annotation.DrawableRes
import android.support.annotation.IntDef
import com.stanly.umenghelper.enumerate.SharePlatfromEnum

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @ProjectName: MultiFunctionAndroidDemo
 * @PackageName: com.umeng.soexample.share.utils.sharetools
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 11:21
 * @Description:
 * @Update :
 */
class SharePlatform {

    var id: SharePlatfromEnum = SharePlatfromEnum.WECHAT
    var name: String? = null
    var resId: Int = 0

    constructor(ID: SharePlatfromEnum, Name: String, resId: Int) {
        this.id = ID
        this.name = Name
        this.resId = resId
    }

    constructor(platform: SharePlatfromEnum) {
        when (platform) {
            SharePlatfromEnum.WECHAT -> {
                this.id = SharePlatfromEnum.WECHAT
                this.name = "微信"
                this.resId = R.drawable.umeng_socialize_wechat
            }
            SharePlatfromEnum.QQ -> {
                this.id = SharePlatfromEnum.QQ
                this.name = "QQ"
                this.resId = R.drawable.umeng_socialize_qq
            }
            SharePlatfromEnum.SINA -> {
                this.id = SharePlatfromEnum.SINA
                this.name = "微博"
                this.resId = R.drawable.umeng_socialize_sina
            }
            SharePlatfromEnum.WECHAT_CIRCLE -> {
                this.id = SharePlatfromEnum.WECHAT_CIRCLE
                this.name = "朋友圈"
                this.resId = R.drawable.umeng_socialize_wxcircle
            }
            SharePlatfromEnum.QZONE -> {
                this.id = SharePlatfromEnum.QZONE
                this.name = "QQ空间"
                this.resId = R.drawable.umeng_socialize_qzone
            }
            SharePlatfromEnum.COPY_TEXT -> {
                this.id = SharePlatfromEnum.COPY_TEXT
                this.name = "复制内容"
                this.resId = R.drawable.umeng_socialize_copy
            }
            SharePlatfromEnum.COPY_URL -> {
                this.id = SharePlatfromEnum.COPY_URL
                this.name = "复制地址"
                this.resId = R.drawable.umeng_socialize_copyurl
            }
            SharePlatfromEnum.OPEN_IN_BROWSER -> {
                this.id = SharePlatfromEnum.OPEN_IN_BROWSER
                this.name = "浏览器打开"
                this.resId = R.drawable.ic_launcher_round
            }
        }
    }


}
