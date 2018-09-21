package com.android.stanlyfang.umengshare.sharetools;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;


import com.android.stanlyfang.umengshare.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ProjectName: MultiFunctionAndroidDemo
 * @PackageName: com.umeng.soexample.share.utils.sharetools
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 11:21
 * @Description:
 * @Update :
 */
public class SharePlatform {

    static final int WECHAT          = 1;
    static final int QQ              = 2;
    static final int SINA            = 3;
    static final int WECHAT_CIRCLE   = 4;
    static final int QZONE           = 5;
    static final int OPEN_IN_BROWSER = 6;
    static final int COPY_URL        = 7;
    static final int COPY_TEXT       = 8;

    @IntDef({WECHAT, QQ, SINA, WECHAT_CIRCLE, QZONE, OPEN_IN_BROWSER, COPY_URL, COPY_TEXT})
    @Retention(RetentionPolicy.RUNTIME)
    @interface PlatfromEnum {
    }

    public SharePlatform(@PlatfromEnum int platform) {
        switch (platform) {
            case WECHAT:
                this.ID = WECHAT;
                this.Name = "微信";
                this.resId = R.mipmap.ic_launcher;
                break;
            case QQ:
                this.ID = QQ;
                this.Name = "QQ";
                this.resId = R.mipmap.ic_launcher;
                break;
            case SINA:
                this.ID = SINA;
                this.Name = "微博";
                this.resId = R.mipmap.ic_launcher;
                break;
            case WECHAT_CIRCLE:
                this.ID = WECHAT;
                this.Name = "朋友圈";
                this.resId = R.mipmap.ic_launcher;
                break;
            case QZONE:
                this.ID = QZONE;
                this.Name = "QQ空间";
                this.resId = R.mipmap.ic_launcher;
                break;
            case COPY_TEXT:
                this.ID = COPY_TEXT;
                this.Name = "赋值内容";
                this.resId = R.mipmap.ic_launcher;
                break;
            case COPY_URL:
                this.ID = COPY_URL;
                this.Name = "复制地址";
                this.resId = R.mipmap.ic_launcher;
                break;
            case OPEN_IN_BROWSER:
                this.ID = OPEN_IN_BROWSER;
                this.Name = "浏览器打开";
                this.resId = R.mipmap.ic_launcher;
                break;
        }
    }


    private int    ID;
    private String Name;
    private int    resId;

    public SharePlatform() {

    }

    public SharePlatform(@PlatfromEnum int ID, String Name, int resId) {
        this.ID = ID;
        this.Name = Name;
        this.resId = resId;
    }


    public int getID() {
        return ID;
    }

    public void setID(@PlatfromEnum int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(@DrawableRes int resId) {
        this.resId = resId;
    }
}
