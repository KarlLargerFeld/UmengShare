package com.stanly.umenghelper

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.google.android.flexbox.FlexboxLayout
import com.jude.rollviewpager.RollPagerView
import com.jude.rollviewpager.adapter.StaticPagerAdapter
import com.stanly.umenghelper.enumerate.SharePlatfromEnum
import com.stanly.umenghelper.listener.OnShareBoardClickListener
import com.umeng.socialize.ShareAction
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMWeb

import java.util.ArrayList

import timber.log.Timber

/**
 * @ProjectName: UmengShare
 * @PackageName: com.android.stanlyfang.umengshare.sharetools
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 15:53
 * @Description:
 * @Update :
 */
class ShareDialog : Dialog {

    private var mSharePlatforms: List<SharePlatform>? = null
    private var mOnDismissListener: OnDismissListener? = null
    //设置每页显示几个
    private var mPageSize = 8
    //设置每行显示几个
    private var mRowSize = 4


    internal var mItemViewList: MutableList<View> = ArrayList()

    private var mOnShareBoardClickListener: OnShareBoardClickListener? = null

    interface OnDismissListener {
        fun onDismiss(view: View)
    }


    private constructor(context: Context) : super(context, R.style.dialog) {}

    private constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener) : super(context, R.style.dialog) {
        setCancelable(cancelable)
        setOnCancelListener(cancelListener)
    } //        super(context, cancelable, cancelListener);

    private constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    private fun getDisplayMetrics(): DisplayMetrics {
        val dm = DisplayMetrics()
        window?.windowManager?.defaultDisplay?.getMetrics(dm)
        return dm
    }


    /**
     * 显示Dialog
     */
    override fun show() {
        val dm = getDisplayMetrics()
        window?.setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE)
        val lp = window?.attributes
        lp?.width = dm.widthPixels //设置宽度
        lp?.height = dm.heightPixels
        //        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window?.attributes = lp

        for (i in mItemViewList.indices) {
            val animation = TranslateAnimation(0f, 0f, 500f, 0f)
            animation.duration = (500 + 50 * i).toLong() //持续时间
            animation.interpolator = OvershootInterpolator()
            mItemViewList[i].animation = animation
            animation.start()
        }
        super.show()
    }


    private fun setContentView(context: Context) {
        val shareLayout = LayoutInflater.from(context).inflate(R.layout.dialog_share_layout, null, false)
        val pager = shareLayout.findViewById<RollPagerView>(R.id.rollPagerView)
        val ivClose = shareLayout.findViewById<ImageView>(R.id.ivClose)

        ivClose.setOnClickListener { v ->
            dismiss()
            if (mOnDismissListener != null) {
                mOnDismissListener?.onDismiss(v)
            }
        }
        val flexboxLayouts = ArrayList<FlexboxLayout>()

        if (mPageSize <= 0) {
            Log.e("setContentView", "Please set pagesize before create method")
            return
        }
        val p = (mSharePlatforms!!.size - 1) / mPageSize + 1
        for (i in 0 until p) {
            //            val item_vp: View = LayoutInflater.from(context).inflate(R.layout.item_share_vp, null, false);
            val flexboxLayout = FlexboxLayout(context)
            flexboxLayout.flexDirection = FlexboxLayout.FLEX_DIRECTION_ROW
            flexboxLayout.flexWrap = FlexboxLayout.FLEX_WRAP_WRAP
            flexboxLayout.alignContent = FlexboxLayout.ALIGN_CONTENT_SPACE_AROUND
            val params = FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.MATCH_PARENT, FlexboxLayout.LayoutParams.WRAP_CONTENT)

            flexboxLayout.layoutParams = params

            for (j in 0 until mPageSize) {
                val index = p * i + j
                if (index == mSharePlatforms!!.size - 1) {
                    break
                }
                addItem(flexboxLayout, index, context)
            }
            flexboxLayouts.add(flexboxLayout)
        }
        pager.setAdapter(object : StaticPagerAdapter() {
            override fun getView(container: ViewGroup, position: Int): View {
                return flexboxLayouts[position]
            }

            override fun getCount(): Int {
                return flexboxLayouts.size
            }
        })
        setContentView(shareLayout)
    }

    private fun addItem(flexboxLayout: FlexboxLayout, index: Int, context: Context) {
        val dm = getDisplayMetrics()
        val params = ViewGroup.LayoutParams(dm.widthPixels / mRowSize, ViewGroup.LayoutParams.WRAP_CONTENT)
        val sharePlatform = mSharePlatforms!![index]
        val tvPlatformItem = TextView(context)
        tvPlatformItem.setTextColor(Color.WHITE)
        tvPlatformItem.gravity = Gravity.CENTER_HORIZONTAL

        val imgDrawable: Drawable? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getDrawable(sharePlatform.resId)
        } else {
            context.resources.getDrawable(sharePlatform.resId)
        }
        imgDrawable?.setBounds(0, 0, Convert.dip2px(context, 50), Convert.dip2px(context, 50))
        tvPlatformItem.setCompoundDrawables(null, imgDrawable, null, null)

        tvPlatformItem.text = sharePlatform.name
        tvPlatformItem.tag = sharePlatform

        tvPlatformItem.setOnClickListener { v ->
            if (mOnShareBoardClickListener != null) {
                val platform = v.tag as SharePlatform

                when (platform.id) {
                    SharePlatfromEnum.WECHAT -> {
                        mOnShareBoardClickListener?.onShare(SHARE_MEDIA.WEIXIN)
                    }
                    SharePlatfromEnum.QQ -> {
                        mOnShareBoardClickListener?.onShare(SHARE_MEDIA.QQ)
                    }
                    SharePlatfromEnum.SINA -> {
                        mOnShareBoardClickListener?.onShare(SHARE_MEDIA.SINA)
                    }
                    SharePlatfromEnum.WECHAT_CIRCLE -> {
                        mOnShareBoardClickListener?.onShare(SHARE_MEDIA.WEIXIN_CIRCLE)
                    }
                    SharePlatfromEnum.QZONE -> {
                        mOnShareBoardClickListener?.onShare(SHARE_MEDIA.QZONE)
                    }
                    else -> {
                        mOnShareBoardClickListener?.doOther(platform.id)
//                        SharePlatfromEnum.OPEN_IN_BROWSER -> {
//                            //                        shareAction.platform = SHARE_MEDIA
//                        }
//                        SharePlatfromEnum.COPY_URL -> {
//                            //复制URL
//                        }
//                        SharePlatfromEnum.COPY_TEXT -> {
//                            //复制
//                        }
//                        SharePlatfromEnum.INTRESTING -> {
//
//                        }
                    }
                }



                dismiss()
            } else {
                Timber.e("mOnShareListener is not to be null")
            }
        }
        flexboxLayout.addView(tvPlatformItem, params)
        mItemViewList.add(tvPlatformItem)
    }

    class Builder(private val mContext: Context) {
        //        UMShareAPI umShareAPI;
        private val platformList = ArrayList<SharePlatform>()
        private var mOnDismissListener: OnDismissListener? = null
        private var mOnShareListener: OnShareBoardClickListener? = null
        private var pageSize = 8
        private var rowSize = 4


        fun addPlatform(sharePlatform: SharePlatform): Builder {
            platformList.add(sharePlatform)
            return this
        }

        fun addAllPlatForm(): Builder {
            if (platformList.size > 0) {
                platformList.clear()
            }
            platformList.add(SharePlatform(SharePlatfromEnum.WECHAT))
            platformList.add(SharePlatform(SharePlatfromEnum.WECHAT_CIRCLE))
            platformList.add(SharePlatform(SharePlatfromEnum.QQ))
            platformList.add(SharePlatform(SharePlatfromEnum.QZONE))
            platformList.add(SharePlatform(SharePlatfromEnum.SINA))
            platformList.add(SharePlatform(SharePlatfromEnum.COPY_TEXT))
            platformList.add(SharePlatform(SharePlatfromEnum.COPY_URL))
            platformList.add(SharePlatform(SharePlatfromEnum.OPEN_IN_BROWSER))
            //            platformList.add(new SharePlatform(SharePlatfromEnum.WECHAT));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.WECHAT_CIRCLE));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.QQ));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.QZONE));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.SINA));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.COPY_TEXT));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.COPY_URL));
            //            platformList.add(new SharePlatform(SharePlatfromEnum.OPEN_IN_BROWSER));
            return this
            //            //如果在调用次方法时，platformList不为空，先清空再添加
            //            if (platformList.size() > 0) {
            //                platformList.clear();
            //            }
            //            if (umShareAPI.isInstall(mContext, SHARE_MEDIA.WEIXIN)) {
            //                platformList.add(new SharePlatform(SharePlatform.WEIXIN));
            //            }
            //            if (umShareAPI.isInstall(mContext, SHARE_MEDIA.WEIXIN_CIRCLE)) {
            //                platformList.add(new SharePlatform(SharePlatform.WECHAT_CIRCLE));
            //            }
            //            if (umShareAPI.isInstall(mContext, SHARE_MEDIA.QQ)) {
            //                platformList.add(new SharePlatform(SharePlatform.QQ));
            //            }
            //            if (umShareAPI.isInstall(mContext, SHARE_MEDIA.QZONE)) {
            //                platformList.add(new SharePlatform(SharePlatform.QZONE));
            //            }
            //            if (umShareAPI.isInstall(mContext, SHARE_MEDIA.SINA)) {
            //                platformList.add(new SharePlatform(SharePlatform.SINA));
            //            }
            //            platformList.add(new SharePlatform(SharePlatform.COPY_TEXT));
            //            platformList.add(new SharePlatform(SharePlatform.COPY_URL));
            //            platformList.add(new SharePlatform(SharePlatform.OPEN_IN_BROWSER));
            //            return this;
        }

        private fun getPlatformList(): List<SharePlatform> {
            return platformList
        }

        fun setOnDismissListener(listener: OnDismissListener): Builder {
            this.mOnDismissListener = listener
            return this
        }

        /**
         * 设置每页显示几个
         *
         * @param pageSize
         * @return
         */
        fun setPageSize(pageSize: Int): Builder {
            this.pageSize = pageSize
            return this
        }

        /**
         * 设置每行显示几个
         *
         * @param rowSize
         * @return
         */
        fun setRowSize(rowSize: Int): Builder {
            if (rowSize <= 0) {
                Log.e("ROWSIZE", "rowSize must be greater than 0")
            }
            this.rowSize = rowSize
            return this
        }

        fun setOnShareBoardClickListener(listener: OnShareBoardClickListener): Builder {
            this.mOnShareListener = listener
            return this
        }

        fun setShareContent(web: UMWeb): Builder {

            return this
        }

        fun setShareAction(action: ShareAction): Builder {

            return this
        }


        fun create(): ShareDialog {
            val shareDialog = ShareDialog(mContext)

            if (this.platformList.size <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请先添加分享平台", Toast.LENGTH_SHORT).show()
                }
            } else if (this.pageSize <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请设置每页显示的分享平台个数", Toast.LENGTH_SHORT).show()
                }
            } else if (this.rowSize <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请设置每行显示的分享平台个数", Toast.LENGTH_SHORT).show()
                }
            } else if (this.rowSize > this.pageSize) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "行显示个数不能大于当页总显示个数", Toast.LENGTH_SHORT).show()
                }
            }

            shareDialog.mSharePlatforms = this.platformList
            shareDialog.mOnDismissListener = this.mOnDismissListener
            shareDialog.mPageSize = this.pageSize
            shareDialog.mRowSize = this.rowSize
            shareDialog.mOnShareBoardClickListener = this.mOnShareListener

            shareDialog.setContentView(mContext)

            return shareDialog
        }
    }
}
