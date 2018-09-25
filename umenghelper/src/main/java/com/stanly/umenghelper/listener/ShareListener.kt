package com.stanly.umenghelper.listener

import com.stanly.umenghelper.SharePlatform
import com.stanly.umenghelper.enumerate.ShareCategoryEnum
import com.stanly.umenghelper.enumerate.ShareCategoryEnum.*
import com.stanly.umenghelper.enumerate.SharePlatfromEnum
import com.stanly.umenghelper.enumerate.SharePlatfromEnum.*
import com.umeng.socialize.ShareContent

/**
 * @ProjectName:    UmengShare
 * @PackageName:     com.stanly.umenghelper.listener
 * @Author          StanlyFang
 * @Version         V1.0
 * @Date           2018/9/25 12:09
 * @Description:
 * @Update :
 */
 interface ShareListener {
    fun onShareBefore(platform: SharePlatform)
    fun onShare(platform: SharePlatform)
    fun onShareAfter(platform: SharePlatform)
}

interface OnShareBoardClickListener{
    fun onShareBoardClick(shareCategoryEnum: ShareCategoryEnum,shareContent: ShareContent)
}

abstract class OnDefaultShareListener : ShareListener {
    override fun onShareBefore(platform: SharePlatform) {

    }

    override fun onShareAfter(platform: SharePlatform) {

    }

    override fun onShare(platform: SharePlatform) {


    }
}