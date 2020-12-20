package com.umeng.soexample.ui.easemob;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.EMUserInfo;
import com.umeng.soexample.utils.SpUtils;
import com.umeng.soexample.utils.UserInfoManager;

import java.util.List;

public class ChatAdapter extends BaseAdapter {


    private String currentUser;

    public ChatAdapter(Context context, List<EMMessage> msgsList) {
        super( msgsList, context );
        currentUser = EMClient.getInstance().getCurrentUser();

    }

    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage = (EMMessage) getData().get( position );
        if ( currentUser.equals( emMessage.getFrom() )){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 1){
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_item_lt,parent,false);
        }else if(viewType == 0){
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_item_rt,parent,false);
        }
        VH vh = new VH(view);
        vh.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click!=null){
                    click.itemClick( vh.getLayoutPosition() );
                }

            }
        } );
        return vh;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        EMMessage msg = (EMMessage) data;

        TextView txtWord = (TextView) vh.getViewById(R.id.txt_word);
        ImageView imgIcon = (ImageView) vh.getViewById(R.id.img_icon);

        String pathUrl = SpUtils.getInstance().getString(currentUser);
        Glide.with(context).load(pathUrl).into(imgIcon);

        //头像
        ImageView imgHeader = (ImageView) vh.getViewById(R.id.iv_header);
        if(currentUser.equals( msg.getFrom() )){


            /*String header = SpUtils.getInstance().getString(currentUser);
                if(!TextUtils.isEmpty(header)){
                Glide.with(imgHeader).load(header).into(imgHeader);
            }*/
        }else{
            EMUserInfo user = UserInfoManager.getInstance().getUserInfoByUid(msg.getFrom());
            if(user != null){
                if(!TextUtils.isEmpty(user.getHeader())){
                    Glide.with(imgHeader).load(user.getHeader()).into(imgHeader);
                }
            }
        }

        if(msg.getType() == EMMessage.Type.TXT){
            txtWord.setVisibility(View.VISIBLE);
            imgIcon.setVisibility(View.GONE);
            EMTextMessageBody textMessageBody = (EMTextMessageBody) msg.getBody();
            txtWord.setText(textMessageBody.getMessage());




        }else if(msg.getType() == EMMessage.Type.IMAGE){
            txtWord.setVisibility(View.GONE);
            imgIcon.setVisibility(View.VISIBLE);
            EMImageMessageBody imageMessageBody = (EMImageMessageBody) msg.getBody();
            String path = imageMessageBody.getRemoteUrl();
            Glide.with(imgIcon).load(path).into(imgIcon);
        }
    }
}
