package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.soexample.jg.TestJPushActivity;
import com.umeng.soexample.ui.easemob.EaseMobActivity;
import com.umeng.soexample.ui.map.MapActivity;
import com.umeng.soexample.ui.tongpao.TongPaoActivity;
import com.umeng.soexample.um.SharedActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnShared, btnTongpao, mBtnJiguang;
    /**
     * 地图
     */
    private Button mBtnMap;
    /**
     * 环信
     */
    private Button mBtnEasemob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();

        btnShared = findViewById( R.id.btn_share );
        btnTongpao = findViewById( R.id.btn_tongpao );
        btnShared.setOnClickListener( this );
        btnTongpao.setOnClickListener( this );
        mBtnJiguang = (Button) findViewById( R.id.btn_jiguang );
        mBtnJiguang.setOnClickListener( this );


       /* Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                Intent intent = new Intent( MainActivity.this, SharedActivity.class );
                startActivity( intent );
                break;
            case R.id.btn_tongpao:
                Intent intent1 = new Intent( MainActivity.this, TongPaoActivity.class );
                startActivity( intent1 );
                break;
            case R.id.btn_jiguang:
                Intent intent2 = new Intent( MainActivity.this, TestJPushActivity.class );
                startActivity( intent2 );
                break;
            case R.id.btn_map:
                Intent intent3 = new Intent( MainActivity.this, MapActivity.class );
                startActivity( intent3 );
                break;
            case R.id.btn_easemob:
                Intent intent4 = new Intent( MainActivity.this, EaseMobActivity.class );
                startActivity( intent4 );
                break;
        }
    }


    private void initView() {
        mBtnMap = (Button) findViewById( R.id.btn_map );
        mBtnMap.setOnClickListener( this );
        mBtnEasemob = (Button) findViewById( R.id.btn_easemob );
        mBtnEasemob.setOnClickListener( this );
    }
}