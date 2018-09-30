package com.android.stanlyfang.commentmodule;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean isShowPraise     = true;

    private ImageView mImageCommentNum;
    private ImageView mImagePraise;
    private TextView  mTextCommentContent;
    private Context   mContext;


    OnCommentNumClickListener mOnCommentNumClickListener;
    OnPraiseClickListener     mOnPraiseClickListener;

    public interface OnCommentNumClickListener {
        void OnCommentNumClick();
    }

    public interface OnPraiseClickListener {
        void OnPraiseClick(ImageView praiseView);
    }


    public void setOnCommentNumClickListener(OnCommentNumClickListener listener) {
        this.mOnCommentNumClickListener = listener;
    }

    public void setOnPraiseClickListener(OnPraiseClickListener listener) {
        this.mOnPraiseClickListener = listener;
    }



    public CommentView(Context context) {
        super(context, null);
        //加载视图的布局
    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_comment_1, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommentView);
        isShowCommentNum = typedArray.getBoolean(R.styleable.CommentView_isShowCommentNum, true);
        isShowPraise = typedArray.getBoolean(R.styleable.CommentView_isShowPraise, true);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageCommentNum = findViewById(R.id.ivCommentNum);
        mImagePraise = findViewById(R.id.ivPraise);
        if (!isShowCommentNum) {
            mImageCommentNum.setVisibility(View.GONE);
        }
        if (!isShowPraise) {
            mImagePraise.setVisibility(View.GONE);
        }
        mTextCommentContent = findViewById(R.id.tvCommentContent);
        init();
    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        mTextCommentContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "弹出评论框", Toast.LENGTH_SHORT).show();
                CommentDialogFragment commentDialogFragment = new CommentDialogFragment();

                commentDialogFragment.show(((FragmentActivity) mContext).getSupportFragmentManager(), "");
            }
        });

        if (isShowCommentNum) {
            mImageCommentNum.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCommentNumClickListener != null) {
                        mOnCommentNumClickListener.OnCommentNumClick();
                    } else {
                        throw new ClassCastException(mContext.toString()
                                + " must implement mOnCommentNumClickListener");
                    }
                }
            });
        }

        if (isShowPraise) {
            mImagePraise.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPraiseClickListener != null) {
                        mOnPraiseClickListener.OnPraiseClick(mImagePraise);
                    }else{
                        throw new ClassCastException(mContext.toString()
                                + " must implement mOnPraiseClickListener");
                    }
                }
            });
        }
    }
}
