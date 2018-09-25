package com.stanly.umenghelper

import android.content.Context

/**
 * @ProjectName:    UmengShare
 * @PackageName:     com.stanly.umenghelper
 * @Author          StanlyFang
 * @Version         V1.0
 * @Date           2018/9/25 11:36
 * @Description:
 * @Update :
 */
object Convert {
    fun dip2px(context: Context, dip: Int): Int {
        val scale: Float = context.resources.displayMetrics.density
        //+0.5是为了向上取整
        return (dip * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, px: Int): Int {
        val scale: Float = context.resources.displayMetrics.density
        //+0.5是为了向上取整
        return (px / scale + 0.5f).toInt()
    }
}