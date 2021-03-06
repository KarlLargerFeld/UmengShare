package com.android.stanlyfang.commentmodule

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.android.stanlyfang.commentmodule.listener.DialogFragmentDataCallback
import java.lang.IllegalStateException

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

    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //
    //        println("onCreateView")
    //        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    //        val view = inflater.inflate(R.layout.layout_comment,container,false)
    //        return view
    //    }


    private var inputMethodManager: InputMethodManager? = null

    private lateinit var mTextSend: TextView
    private lateinit var mEditContent: EditText
    private var mOnDoCommentLisener: DialogFragmentDataCallback? = null

    override fun onAttach(context: Context?) {
        if (activity !is DialogFragmentDataCallback) {
            throw IllegalStateException("DialogFragment 所在的 activity 必须实现 DialogFragmentDataCallback 接口")
        }
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        println("onCreateDialog")
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.layout_comment, null, false)
        //        builder.setView(R.layout.layout_comment)
        builder.setView(view)
        mTextSend = view.findViewById(R.id.tvSendComment)
        mEditContent = view.findViewById(R.id.etCommentBox)
        mOnDoCommentLisener = activity as DialogFragmentDataCallback
        mTextSend.setOnClickListener {
            mOnDoCommentLisener?.OnDoComment(mEditContent.text.toString())
        }
        mEditContent.addTextChangedListener(object : TextWatcher {
            private var temp: CharSequence? = null
            override fun afterTextChanged(s: Editable?) {
                if (temp!!.isNotEmpty()) {
                    mTextSend.isEnabled = true
                    mTextSend.isClickable = true
                } else {
                    mTextSend.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                temp = s
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        mEditContent.isFocusable = true
        mEditContent.isFocusableInTouchMode = true
        mEditContent.requestFocus()

        //        mEditContent.viewTreeObserver.addOnGlobalFocusChangeListener(object : ViewTreeObserver.OnGlobalFocusChangeListener {
        //            override fun onGlobalFocusChanged(oldFocus: View?, newFocus: View?) {
        //                inputMethodManager = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //
        //                if (inputMethodManager!!.showSoftInput(mEditContent, 0)) {
        //                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        //                        mEditContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
        //                    } else {
        //                    }
        //                }
        //            }
        //        })

        mEditContent.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (inputMethodManager != null) {
                    if (inputMethodManager!!.showSoftInput(mEditContent, 0)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mEditContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        } else {
                            mEditContent.viewTreeObserver.removeGlobalOnLayoutListener(this)
                        }
                    }
                }
            }
        })


        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        dialog.window.attributes.windowAnimations = R.style.FragmentDialogAnimation
        super.onActivityCreated(savedInstanceState)
    }


    override fun onStart() {
        super.onStart()
        val win = dialog.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)


        val params = win.attributes
        params.width = dm.widthPixels
        params.gravity = Gravity.BOTTOM
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        win.attributes = params

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

