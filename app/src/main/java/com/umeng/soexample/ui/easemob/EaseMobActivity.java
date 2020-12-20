package com.umeng.soexample.ui.easemob;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.exceptions.HyphenateException;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.EMUserInfo;
import com.umeng.soexample.utils.SpUtils;
import com.umeng.soexample.utils.UserInfoManager;

import java.util.ArrayList;
import java.util.List;

public class EaseMobActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = EaseMobActivity.class.getSimpleName();

    private Button btnLogin;
    RecyclerView recyUserList;
    List<EMUserInfo> userList;
    FriendsAdapter friendsAdapter;
    /**
     * 请输入用户名
     */
    private EditText mEtLogin;
    /**
     * 请输入密码
     */
    private EditText mEtPossword;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ease_mob );
        initView();

        btnLogin = findViewById( R.id.btn_login );
        recyUserList = findViewById( R.id.recy_userList );
        btnLogin.setOnClickListener( this );

        initUserList();

    }

    private void initUserList() {
        userList = UserInfoManager.getInstance().getAllUsers();
        friendsAdapter = new FriendsAdapter( this, userList );
        recyUserList.setLayoutManager( new LinearLayoutManager( this ) );
        recyUserList.setAdapter( friendsAdapter );

        friendsAdapter.setClick( new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent( EaseMobActivity.this, ChatActivity.class );
                intent.putExtra( "touserid", userList.get( pos ).getUid() );
                startActivity( intent );
            }
        } );

        //处理点击条目中的按钮
        friendsAdapter.addItemViewClick( new BaseAdapter.IItemViewClick<EMUserInfo>() {
            @Override
            public void itemViewClick(int viewid, EMUserInfo data) {
                Intent intent = new Intent( EaseMobActivity.this, UserDetailActivity.class );
                intent.putExtra( "username", data.getUid() );
                startActivity( intent );
            }
        } );

        //显示未读
        friendsAdapter.setShowUnReadItem( new BaseAdapter.ShowUnReadItem() {
            @Override
            public void showUnRead(int viewid, Object data) {
                Intent intent = new Intent( EaseMobActivity.this, ShowActivity.class );
                startActivity( intent );

            }
        } );
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        //登录
        String username = mEtLogin.getText().toString();
        String password = mEtPossword.getText().toString();

        Log.i( TAG, "环信login" );
        EMClient.getInstance().login( username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.i( TAG, "登录成功" );
                getFriends();
                //startActivity( new Intent( EaseMobActivity.this,ChatActivity.class ) );
            }

            @Override
            public void onError(int code, String error) {
                Log.i( TAG, "onError:" + error );
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.i( TAG, "status:" + status );
            }
        } );
    }

    /**
     * 获取好友
     */
    private void getFriends() {
        try {
            List<String> friends = EMClient.getInstance().contactManager().getAllContactsFromServer();
            List<EMUserInfo> list = new ArrayList<>();
            userList.clear();


            if (friends != null) {
                for (int i = 0; i < friends.size(); i++) {
                    EMUserInfo emUserInfo = new EMUserInfo();
                    String header = SpUtils.getInstance().getString( friends.get( i ) );
                    emUserInfo.setHeader( header );
                    emUserInfo.setUid( friends.get( i ) );
//                    emUserInfo.setNickname(  );
                    list.add( emUserInfo );


                    //
                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation( list.get( i ).getUid() );
                    if (conversation != null) {
                        int unreadMsgCount = conversation.getUnreadMsgCount();

                        conversation.markAllMessagesAsRead();
                    }


                }
                UserInfoManager.getInstance().addUsers( list );
                recyUserList.post( new Runnable() {
                    @Override
                    public void run() {
                        friendsAdapter.notifyDataSetChanged();
                    }
                } );
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        mEtLogin = (EditText) findViewById( R.id.et_login );
        mEtPossword = (EditText) findViewById( R.id.et_possword );
    }
}