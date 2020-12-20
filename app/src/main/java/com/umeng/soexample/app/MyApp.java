package com.umeng.soexample.app;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

public class MyApp extends Application {

    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initUM();
        initJPush();
        initMap();

        //初始化环信
        initEaseMob();

    }

    private void initMap() {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize( this );
//自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
//包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType( CoordType.BD09LL );


    }

    //初始化极光
    private void initJPush() {
        JPushInterface.setDebugMode( true );    // 设置开启日志,发布时请关闭日志
        JPushInterface.init( this );            // 初始化 JPush
    }

    {
        PlatformConfig.setWeixin( "wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0" );
        PlatformConfig.setSinaWeibo( "3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com" );
        /*PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");*/

    }

    private void initUM() {
        UMConfigure.init( this, "5fb8b20b690bda19c786a367", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "" );
        MobclickAgent.setPageCollectionMode( MobclickAgent.PageMode.AUTO );

    }

    //初始化环信
    private void initEaseMob() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways( false );
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments( true );
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail( true );
        //初始化
        EMClient.getInstance().init( this, options );
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode( true );
    }


}
