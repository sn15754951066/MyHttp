package com.umeng.soexample.ui.easemob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.umeng.soexample.R;
import com.umeng.soexample.app.Constants;
import com.umeng.soexample.module.data.EMUserInfo;
import com.umeng.soexample.utils.SpUtils;
import com.umeng.soexample.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mCallbackIv;
    private TextView mTitleTxt;
    private RecyclerView mChatRecy;
    private EditText mWordInput;
    private Button mSendBtn;
    private String touserids;
    private String selfid;
    private List<EMMessage> msgsList;
    private ImageView mPhontIv;

    //适配器
    private ChatAdapter chatAdapter;
    //text文本
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );
        initView();
        //nikename接收值
        initData();
        initMsgListent();
    }


    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra( "touserid" )) {
                //获得好友的用户名
                touserids = intent.getStringExtra( "touserid" );
                mTitleTxt.setText( touserids );
            }
        }


    }

    private void initView() {
        mCallbackIv = (ImageView) findViewById( R.id.iv_callback );
        mTitleTxt = (TextView) findViewById( R.id.txt_title );
        mChatRecy = (RecyclerView) findViewById( R.id.recy_chat );
        mWordInput = (EditText) findViewById( R.id.input_word );
        mSendBtn = (Button) findViewById( R.id.btn_send );
        mSendBtn.setOnClickListener( this );

        //获取你自己id
        selfid = EMClient.getInstance().getCurrentUser();
        //适配器
        msgsList = new ArrayList<>();
        mChatRecy.setLayoutManager( new LinearLayoutManager( this ) );
        chatAdapter = new ChatAdapter( this, msgsList );
        mChatRecy.setAdapter( chatAdapter );


        //返回键
        mCallbackIv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ChatActivity.this, EaseMobActivity.class );
                intent.putExtra( "text", message );
                startActivity( intent );
                finish();

            }
        } );


        mPhontIv = (ImageView) findViewById( R.id.iv_phont );
        mPhontIv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
                intent.setType( "image/*" );
                startActivityForResult( intent, 100 );
            }
        } );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                //发送
                sendMsg();
                break;

            default:
                break;
        }
    }

    /**
     * 发送消息
     */
    private void sendMsg() {
        //获得控件上的内容
        String content = mWordInput.getText().toString();
        if (TextUtils.isEmpty( content )) {
            Toast.makeText( this, "请输入消息内容", Toast.LENGTH_SHORT ).show();
            return;
        }

        //创建一条文本消息，content为消息文字内容，touserids为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage( content, touserids );

        //如果是群聊，设置chattype，默认是单聊
        message.setChatType( EMMessage.ChatType.ChatRoom );
        //发送消息
        EMClient.getInstance().chatManager().sendMessage( message );

        //文本添加到集合中
        msgsList.add( message );
        chatAdapter.notifyDataSetChanged();
        //发送完之后清空
        mWordInput.setText( "" );

    }

    private void initMsgListent() {
        EMClient.getInstance().chatManager().addMessageListener( msgListener );
    }


    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {

            //收到消息
            msgsList.addAll( messages );

            //刷新适配器
            mChatRecy.post( new Runnable() {
                @Override
                public void run() {
                    chatAdapter.notifyDataSetChanged();
                }
            } );



//            if (touserids.equals( messages.get( 0 ).getFrom() )) {
//                //好友
//                messages.get( 0 ).getBody();
//                //EMMessageBody messageBody;
//                if (messages.get( 0 ).getType() == EMMessage.Type.TXT) {
//                    EMTextMessageBody textMessageBody = (EMTextMessageBody) messages.get( 0 ).getBody();
//                    message = textMessageBody.getMessage();
//                } else if (messages.get( 0 ).getType() == EMMessage.Type.LOCATION) {
//                    //定位销
//                }
//
//            } else if (selfid.equals( messages.get( 0 ).getFrom() )) {
//                //自己
//            }


        }


        public void setCount(){}

        /**
         * 接收透传消息--头像更新
         * @param messages
         */
        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            for (EMMessage item: messages ) {
                if(item.getType()==EMMessage.Type.CMD){
                    EMCmdMessageBody messageBody=(EMCmdMessageBody) item.getBody();
                    if(Constants.ACTION_UPDATEHEADER.equals( messageBody.action() )){
                        //刷新界面更新新用户头像
                        String action = messageBody.action();
                        if(!TextUtils.isEmpty( action )){
                            String uid = item.getFrom();
                            SpUtils.getInstance().setValue(uid,action);
                            EMUserInfo userInfoByUid = UserInfoManager.getInstance().getUserInfoByUid( uid );
                            if(userInfoByUid!=null){
                                userInfoByUid.setHeader( action );
                            }

                        }else if(Constants.ACTION_UPDATENICKNAME.equals( messageBody.action() )){
                            //刷新界面新用户昵称



                        }
                    }

                }


            }

        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();
            mPhontIv.setImageURI( data1 );

            EMMessage msg = EMMessage.createImageSendMessage( data1, false, touserids );
            /*EMImageMessageBody body = new EMImageMessageBody(uri);
             msg.addBody(body);*/
            //如果是群聊，设置chattype，默认是单聊
            msg.setChatType( EMMessage.ChatType.GroupChat );
            EMClient.getInstance().chatManager().sendMessage( msg );

            msgsList.add( msg );
            chatAdapter.notifyDataSetChanged();
        }

    }
}