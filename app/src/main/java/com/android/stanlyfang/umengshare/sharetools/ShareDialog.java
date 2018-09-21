package com.android.stanlyfang.umengshare.sharetools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.stanlyfang.umengshare.R;
import com.google.android.flexbox.FlexboxLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;

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
    private int mPageSize = 0;

    public  interface  OnDismissListener{
        public void onDismiss(View view);
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
        init(context);
    }

    private void init(Context context) {
        //        View shareLayout = LayoutInflater.from(context).inflate(R.layout.dialog_share_layout,null,false);
        //        setView(R.layout.dialog_share_layout);
        //        setContentView(R.layout.dialog_share_layout);
    }

    @Override
    public void show() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = dm.widthPixels; //设置宽度
        lp.height = dm.heightPixels;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setAttributes(lp);
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
                if(mOnDismissListener!=null){
                    mOnDismissListener.onDismiss(v);
                }

            }
        });
        final List<FlexboxLayout> flexboxLayouts = new ArrayList<>();

        if(mPageSize <=0){
            Log.e("setContentView","Please set pagesize before create method");
            return;
        }
        int p = (mSharePlatforms.size()-1) / mPageSize + 1;
        for (int i = 0; i < p; i++) {
            FlexboxLayout flexboxLayout = (FlexboxLayout) LayoutInflater.from(context).inflate(R.layout.item_share_vp, null, false);
            for (int j = 0; j < mPageSize; j++) {
                int index = p * i + j;
                if(index == mSharePlatforms.size()-1){
                    break;
                }
                addItem(flexboxLayout, index,context);
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

    private void addItem(FlexboxLayout flexboxLayout,int index,Context context) {
        View item_share_item = LayoutInflater.from(context).inflate(R.layout.item_share_platform,null,false);
        ImageView ivPlatfrom = item_share_item.findViewById(R.id.ivPlatfrom);
        TextView tvPlatform = item_share_item.findViewById(R.id.tvPlatfrom);
        SharePlatform sharePlatform = mSharePlatforms.get(index);
        ivPlatfrom.setImageResource(sharePlatform.getResId());
        tvPlatform.setText(sharePlatform.getName());
        flexboxLayout.addView(item_share_item);
    }

    public static class Builder {
        //        UMShareAPI umShareAPI;
        private List<SharePlatform> platformList = new ArrayList<>();
        private Context mContext;
        private OnDismissListener mOnDismissListener;
        private  int pageSize = 0;

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

        public Builder setOnDismissListener(OnDismissListener listener){
            this.mOnDismissListener = listener;
            return this;
        }

        public Builder setPageSize(int pageSize){
            this.pageSize = pageSize;
            return this;
        }


        public ShareDialog create() {
            ShareDialog shareDialog = new ShareDialog(mContext);
            shareDialog.mSharePlatforms = platformList;
            shareDialog.mOnDismissListener = mOnDismissListener;
            shareDialog.mPageSize = pageSize;
            shareDialog.setContentView(mContext);

            return shareDialog;
        }
    }
}
