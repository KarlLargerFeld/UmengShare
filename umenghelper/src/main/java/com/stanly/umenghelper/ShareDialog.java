package com.stanly.umenghelper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import timber.log.Timber;

/**
 * @ProjectName: UmengShare
 * @PackageName: com.android.stanlyfang.umengshare.sharetools
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 15:53
 * @Description:
 * @Update :
 */
public class ShareDialog extends Dialog {

    private List<SharePlatform> mSharePlatforms;
    private OnDismissListener mOnDismissListener;
    //设置每页显示几个
    private int mPageSize = 0;
    //设置每行显示几个
    private int mRowSize = 0;

    List<View> mItemViewList = new ArrayList<>();

    private OnShareListener mOnShareListener;

    public interface OnDismissListener {
        public void onDismiss(View view);
    }

    public interface OnShareListener {
        void onShareBefore(SharePlatform platform);

        void OnShareAfter(SharePlatform platform);
    }

    public ShareDialog(Context context) {
        super(context, R.style.dialog);
    }

    protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        //        super(context, cancelable, cancelListener);
        super(context, R.style.dialog);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);

    }

    protected ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public void show() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = dm.widthPixels; //设置宽度
        lp.height = dm.heightPixels;
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setAttributes(lp);

        for (int i = 0; i < mItemViewList.size(); i++) {
            TranslateAnimation animation = new TranslateAnimation(0, 0, 500, 0);
            animation.setDuration(500 + 50 * i);//持续时间
            animation.setInterpolator(new OvershootInterpolator());
            mItemViewList.get(i).setAnimation(animation);
            animation.start();
        }

//        super.show();
        super.show();
    }


    private void setContentView(Context context) {
        View shareLayout = LayoutInflater.from(context).inflate(R.layout.dialog_share_layout, null, false);
        RollPagerView pager = shareLayout.findViewById(R.id.rollPagerView);
        ImageView ivClose = shareLayout.findViewById(R.id.ivClose);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnDismissListener != null) {
                    mOnDismissListener.onDismiss(v);
                }

            }
        });
        final List<FlexboxLayout> flexboxLayouts = new ArrayList<>();

        if (mPageSize <= 0) {
            Log.e("setContentView", "Please set pagesize before create method");
            return;
        }
        int p = (mSharePlatforms.size() - 1) / mPageSize + 1;
        for (int i = 0; i < p; i++) {
//            View item_vp = LayoutInflater.from(context).inflate(R.layout.item_share_vp, null, false);
            FlexboxLayout flexboxLayout = new FlexboxLayout(context);
            flexboxLayout.setFlexDirection(FlexboxLayout.FLEX_DIRECTION_ROW);
            flexboxLayout.setFlexWrap(FlexboxLayout.FLEX_WRAP_WRAP);
            flexboxLayout.setShowDivider(FlexboxLayout.SHOW_DIVIDER_MIDDLE);
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            flexboxLayout.setLayoutParams(params);

//            FlexboxLayout flexboxLayout = item_vp.findViewById(R.id.flexboxlayout);
            for (int j = 0; j < mPageSize; j++) {
                int index = p * i + j;
                if (index == mSharePlatforms.size() - 1) {
                    break;
                }
                addItem(flexboxLayout, index, context);
            }
            flexboxLayouts.add(flexboxLayout);
        }
        pager.setAdapter(new StaticPagerAdapter() {
            @Override
            public View getView(ViewGroup container, int position) {
                return flexboxLayouts.get(position);
            }

            @Override
            public int getCount() {
                return flexboxLayouts.size();
            }
        });
        setContentView(shareLayout);
    }

    private void addItem(FlexboxLayout flexboxLayout, int index, Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getWindow()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        } else {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dm.widthPixels / mRowSize, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View item_share_item = LayoutInflater.from(context).inflate(R.layout.item_share_platform, null, false);
//        ImageView ivPlatfrom = item_share_item.findViewById(R.id.ivPlatfrom);
//        TextView tvPlatform = item_share_item.findViewById(R.id.tvPlatfrom);

        SharePlatform sharePlatform = mSharePlatforms.get(index);
        TextView tvPlatformItem = new TextView(context);
        tvPlatformItem.setTextColor(Color.WHITE);
        tvPlatformItem.setGravity(Gravity.CENTER_HORIZONTAL);
        Drawable imgDrawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imgDrawable = context.getDrawable(sharePlatform.getResId());
        } else {
            imgDrawable = context.getResources().getDrawable(sharePlatform.getResId());
        }
        if (imgDrawable != null) {
            imgDrawable.setBounds(0, 0, imgDrawable.getMinimumWidth(), imgDrawable.getMinimumHeight());
        }
        tvPlatformItem.setCompoundDrawables(null, imgDrawable, null, null);

        tvPlatformItem.setText(sharePlatform.getName());


//        ivPlatfrom.setImageResource(sharePlatform.getResId());
//        tvPlatform.setText(sharePlatform.getName());
//        item_share_item.setTag(sharePlatform);
        tvPlatformItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("On PlatfromItem Click");
                Timber.e("On PlatfromItem Click");
                if (mOnShareListener != null) {
                    SharePlatform platform = (SharePlatform) v.getTag();
                    mOnShareListener.onShareBefore(platform);
                    mOnShareListener.OnShareAfter(platform);
                } else {
                    Timber.e("mOnShareListener is not to be null");
                }
            }
        });
        flexboxLayout.addView(tvPlatformItem, params);
        mItemViewList.add(tvPlatformItem);
    }

    public static class Builder {
        //        UMShareAPI umShareAPI;
        private List<SharePlatform> platformList = new ArrayList<>();
        private Context mContext;
        private OnDismissListener mOnDismissListener;
        private OnShareListener mOnShareListener;
        private int pageSize = 0;
        private int rowSize = 0;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder addPlatform(SharePlatform sharePlatform) {
            platformList.add(sharePlatform);
            return this;
        }

        public Builder addAllPlatForm() {
            if (platformList.size() > 0) {
                platformList.clear();
            }
            platformList.add(new SharePlatform(SharePlatform.WECHAT));
            platformList.add(new SharePlatform(SharePlatform.WECHAT_CIRCLE));
            platformList.add(new SharePlatform(SharePlatform.QQ));
            platformList.add(new SharePlatform(SharePlatform.QZONE));
            platformList.add(new SharePlatform(SharePlatform.SINA));
            platformList.add(new SharePlatform(SharePlatform.COPY_TEXT));
            platformList.add(new SharePlatform(SharePlatform.COPY_URL));
            platformList.add(new SharePlatform(SharePlatform.OPEN_IN_BROWSER));
//            platformList.add(new SharePlatform(SharePlatform.WECHAT));
//            platformList.add(new SharePlatform(SharePlatform.WECHAT_CIRCLE));
//            platformList.add(new SharePlatform(SharePlatform.QQ));
//            platformList.add(new SharePlatform(SharePlatform.QZONE));
//            platformList.add(new SharePlatform(SharePlatform.SINA));
//            platformList.add(new SharePlatform(SharePlatform.COPY_TEXT));
//            platformList.add(new SharePlatform(SharePlatform.COPY_URL));
//            platformList.add(new SharePlatform(SharePlatform.OPEN_IN_BROWSER));
            return this;
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

        private List<SharePlatform> getPlatformList() {
            return platformList;
        }

        public Builder setOnDismissListener(OnDismissListener listener) {
            this.mOnDismissListener = listener;
            return this;
        }

        /**
         * 设置每页显示几个
         *
         * @param pageSize
         * @return
         */
        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * 设置每行显示几个
         *
         * @param rowSize
         * @return
         */
        public Builder setRowSize(int rowSize) {
            if (rowSize <= 0) {
                Log.e("ROWSIZE", "rowSize must be greater than 0");
            }
            this.rowSize = rowSize;
            return this;
        }

        public Builder setOnShareListener(OnShareListener listener) {
            this.mOnShareListener = listener;
            return this;
        }


        public ShareDialog create() {
            ShareDialog shareDialog = new ShareDialog(mContext);

            if (this.platformList.size() <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请先添加分享平台", Toast.LENGTH_SHORT).show();
                }
            } else if (this.pageSize <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请设置每页显示的分享平台个数", Toast.LENGTH_SHORT).show();
                }
            } else if (this.rowSize <= 0) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "请设置每行显示的分享平台个数", Toast.LENGTH_SHORT).show();
                }
            } else if (this.rowSize > this.pageSize) {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(mContext, "行显示个数不能大于当页总显示个数", Toast.LENGTH_SHORT).show();
                }
            }

            shareDialog.mSharePlatforms = this.platformList;
            shareDialog.mOnDismissListener = this.mOnDismissListener;
            shareDialog.mPageSize = this.pageSize;
            shareDialog.mRowSize = this.rowSize;
            shareDialog.mOnShareListener = this.mOnShareListener;
            shareDialog.setContentView(mContext);

            return shareDialog;
        }
    }
}
