package com.android.stanlyfang.commentmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @ProjectName: UmengShare
 * @PackageName: com.android.stanlyfang.commentmodule
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/28 16:15
 * @Description:
 * @Update :
 */
public class CommentView extends LinearLayout {

    private boolean isShowCommentNum = true;
    private boolean isShowPraise = true;

    private ImageView mImageCommentNum;
    private ImageView mImagePraise;
    public CommentView(Context context) {
        super(context,null);
        //加载视图的布局
    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_comment_1,this,true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CommentView);
        isShowCommentNum = typedArray.getBoolean(R.styleable.CommentView_isShowCommentNum,true);
        isShowPraise = typedArray.getBoolean(R.styleable.CommentView_isShowPraise,true);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageCommentNum = findViewById(R.id.ivCommentNum);
        mImagePraise = findViewById(R.id.ivPraise);
        if(!isShowCommentNum){
            mImageCommentNum.setVisibility(View.GONE);
        }
        if(!isShowPraise){
            mImagePraise.setVisibility(View.GONE);
        }

    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
