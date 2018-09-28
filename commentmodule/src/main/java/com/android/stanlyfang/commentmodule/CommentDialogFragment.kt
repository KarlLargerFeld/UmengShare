package com.android.stanlyfang.commentmodule

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window

/**
 * @ProjectName:    UmengShare
 * @PackageName:     com.android.stanlyfang.commentmodule
 * @Author          StanlyFang
 * @Version         V1.0
 * @Date           2018/9/28 11:19
 * @Description:
 * @Update :
 */
class CommentDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        println("onCreateView")
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.layout_comment,container,false)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        println("onCreateDialog")
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.layout_comment,null,false)
//        builder.setView(R.layout.layout_comment)
        builder.setView(View(activity))
        builder.setPositiveButton("确定",null)
        return builder.create()
    }
}