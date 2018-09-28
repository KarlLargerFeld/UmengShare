package com.stanly.umenghelper.listener

import com.stanly.umenghelper.SharePlatform
import com.stanly.umenghelper.enumerate.ShareCategoryEnum
import com.stanly.umenghelper.enumerate.ShareCategoryEnum.*
import com.stanly.umenghelper.enumerate.SharePlatfromEnum
import com.stanly.umenghelper.enumerate.SharePlatfromEnum.*
import com.umeng.socialize.ShareAction
import com.umeng.socialize.ShareContent
import com.umeng.socialize.bean.SHARE_MEDIA

/**
 * @ProjectName:    UmengShare
 * @PackageName:     com.stanly.umenghelper.listener
 * @Author          StanlyFang
 * @Version         V1.0
 * @Date           2018/9/25 12:09
 * @Description:
 * @Update :
 */
interface OnShareBoardClickListener{
    fun onShare(media: SHARE_MEDIA)
    fun doOther(sharePlatfromEnum: SharePlatfromEnum)
}