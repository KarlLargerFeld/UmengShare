package com.android.stanlyfang.umengshare;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.stanlyfang.commentmodule.CommentDialogFragment;
import com.android.stanlyfang.commentmodule.CommentView;
import com.android.stanlyfang.commentmodule.listener.DialogFragmentDataCallback;
import com.umeng.socialize.PlatformConfig;

import org.jetbrains.annotations.NotNull;


/**
 * @ProjectName: UmengShare
 * @PackageName: com.android.stanlyfang.umengshare
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 15:50
 * @Description:
 * @Update :
 */
public class MainActivity extends AppCompatActivity implements CommentView.OnCommentNumClickListener,CommentView.OnPraiseClickListener, DialogFragmentDataCallback {

    static {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");
    }

    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CommentView commentView = findViewById(R.id.commentView);
        commentView.setOnCommentNumClickListener(this);

        commentView.setOnPraiseClickListener(this);



        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DialogFragment dialogFragment = new DialogFragment();
//                dialogFragment.show(getSupportFragmentManager(),"");

                CommentDialogFragment dialogFragment1 = new CommentDialogFragment();
                dialogFragment1.show(getSupportFragmentManager(),"");

//              new ShareDialog.Builder(mContext).addAllPlatForm()
//                      .setOnShareBoardClickListener(new OnShareBoardClickListener() {
//                          @Override
//                          public void onShare(@NotNull SHARE_MEDIA media) {
//                              UMWeb web = new UMWeb("http://www.baidu.com");
//                              new ShareAction((Activity) mContext).setPlatform(media).withMedia(web)
//                                      .share();
//                          }
//
//                          @Override
//                          public void doOther(@NotNull SharePlatfromEnum sharePlatfromEnum) {
//                              switch (sharePlatfromEnum){
//                                  case COPY_URL:
//                                      break;
//                                  case COPY_TEXT:
//                                      break;
//                                  case OPEN_IN_BROWSER:
//                                      break;
//                                  case INTRESTING:
//                                      break;
//                              }
//                          }
//                      }).create().show();
            }
        });
    }

    @Override
    public void OnCommentNumClick() {
        Toast.makeText(mContext, "You Clicked the CommentNum Button", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnPraiseClick(ImageView praiseView) {
//        praiseView.setVisibility(View.GONE);
        Toast.makeText(mContext, "You Clicked the Praise Button", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnDoComment(@NotNull String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }
}
