package com.android.stanlyfang.umengshare;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.stanlyfang.umengshare.sharetools.ShareDialog;

/**
 * @ProjectName: UmengShare
 * @PackageName: com.android.stanlyfang.umengshare
 * @Author StanlyFang
 * @Version V1.0
 * @Date 2018/9/21 15:50
 * @Description:
 * @Update :
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ShareDialog.Builder(mContext).addAllPlatForm().setOnDismissListener(new ShareDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        Toast.makeText(mContext, "OnDismiss", Toast.LENGTH_SHORT).show();
                    }
                }).setPageSize(3).create().show();

                //                Dialog dialog = new Dialog(mContext);
                //                dialog.setContentView(R.layout.dialog_share_layout);
                //                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                //                dialog.show();
            }
        });
    }
}
