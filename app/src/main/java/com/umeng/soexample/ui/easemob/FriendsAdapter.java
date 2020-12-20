package com.umeng.soexample.ui.easemob;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.EMUserInfo;

import java.util.List;

import butterknife.BindView;

public class FriendsAdapter extends BaseAdapter {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.btn_replace_icn)
    Button btnReplaceIcn;

    public FriendsAdapter(Context context, List<EMUserInfo> list) {
        super( list, context );
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_friend_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {

        EMUserInfo emUserInfo= (EMUserInfo) data;

        TextView txtUserName = (TextView) vh.getViewById( R.id.txt_username );
        txtUserName.setText( emUserInfo.getUid());

        Button btnOpenDetail = (Button) vh.getViewById( R.id.btn_replace_icn );
        btnOpenDetail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iItemViewClick != null) {
                    iItemViewClick.itemViewClick( view.getId(), data );
                }
            }
        } );

        TextView messageItemHint = (TextView) vh.getViewById( R.id.message_item_hint );
        if(emUserInfo.getUnread()>0){
            messageItemHint.setVisibility( View.VISIBLE );
            messageItemHint.setText( String.valueOf( emUserInfo.getUnread() ) );

        }else{
            messageItemHint.setVisibility( View.GONE );
        }

        TextView txtShowUnread = (TextView) vh.getViewById( R.id.txt_show_unread );
        txtShowUnread.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showUnReadItem!=null){
                    showUnReadItem.showUnRead( view.getId(),data );
                }
            }
        } );


    }

}

