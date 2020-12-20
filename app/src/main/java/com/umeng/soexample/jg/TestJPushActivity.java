package com.umeng.soexample.jg;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.umeng.soexample.R;

import cn.jpush.android.api.JPushInterface;


public class TestJPushActivity extends AppCompatActivity {

    //private MessageReceiver mMessageReceiver;
    //广播的发送和注册接收处理的标记
    public static final String MESSAGE_RECEIVED_ACTION = "MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public static boolean isForeground = false;

    Button btnInit,btnNotification,btnNotificatonTask,btnSendBroadCast;

    //广播定义
    MessageReceiver messageReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_test_j_push);
        registerBroadCast();
        btnInit = findViewById( R.id.btn_init);
        btnNotification = findViewById( R.id.btn_notification);
        btnNotificatonTask = findViewById( R.id.btn_notification_task);
        btnSendBroadCast = findViewById( R.id.btn_sendBroadCast);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rid = JPushInterface.getRegistrationID(getApplicationContext());
                Log.i("TAG",rid);
            }
        });

        //通知
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification("通知测试","自定义通知");
            }
        });

        btnNotificatonTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationTask("通知Task","带任务的通知");
            }
        });

        btnSendBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(TestJPushActivity.MESSAGE_RECEIVED_ACTION);
                msgIntent.putExtra(TestJPushActivity.KEY_MESSAGE, "模拟发送的消息");
                LocalBroadcastManager.getInstance(TestJPushActivity.this).sendBroadcast(msgIntent);
            }
        });
    }

    /**
     * 注册接收jpush消息的广播
     */
    private void registerBroadCast(){
        messageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        //本地广播的注册
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, filter);
    }

    private void sendNotification(String title,String content){
        Notification notification = new NotificationCompat.Builder(this,"task")
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon( R.mipmap.ic_launcher)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }

    private void sendNotificationTask(String title,String content){
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon( R.mipmap.ic_launcher)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    /**
     * 定义一个广播 用来和MyReceiver接收jpush消息的广播进行通信
     */
    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {

                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!TextUtils.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    //刷新页面
                }
            } catch (Exception e){
            }
        }
    }
}