package com.umeng.soexample.um;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener {


    private static String TAG = SharedActivity.class.getSimpleName();

    Button btnWxShared;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        btnLogin = findViewById(R.id.btn_login);
        btnWxShared = findViewById(R.id.btn_wxshared);

        initView();
    }

    private void initView() {
        btnWxShared.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wxshared:
                shareWord();
                break;
        }
    }

    /**
     * 不带面板的文字分享
     */
    private void shareWord(){
        new ShareAction(SharedActivity.this)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withText("hello")//分享内容
                .setCallback(shareListener)//回调监听器
                .share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.i(TAG,"onStart");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Log.i(TAG,"onResult");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.i(TAG,"onError");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.i(TAG,"onCancel");
        }
    };
}