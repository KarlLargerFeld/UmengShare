package com.stanly.umenghelper

import com.stanly.umenghelper.enumerate.ShareCategoryEnum

/**
 * @ProjectName:    UmengShare
 * @PackageName:     com.stanly.umenghelper
 * @Author          StanlyFang
 * @Version         V1.0
 * @Date           2018/9/25 17:16
 * @Description:
 * @Update :
 */
data class UShareContent(var title: String, var content: String, var url: String,var shareCategoryEnum: ShareCategoryEnum)