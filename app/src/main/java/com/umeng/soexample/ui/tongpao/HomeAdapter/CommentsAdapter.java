package com.umeng.soexample.ui.tongpao.HomeAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.RecommendBean;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends BaseAdapter {

    private List<RecommendBean.DataBean.PostDetailBean.LikeDetailsBean> dataBeans;
    private Context context;

    public CommentsAdapter(List List, Context context) {
        super( List, context );
        this.context = context;
        this.dataBeans = (ArrayList<RecommendBean.DataBean.PostDetailBean.LikeDetailsBean>) List;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_commends;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView header_image = (ImageView) vh.getViewById( R.id.iv_header_image );
        TextView header_name = (TextView) vh.getViewById( R.id.tv_header_name );
        TextView header_item = (TextView) vh.getViewById( R.id.tv_header_time );
        TextView commends_people = (TextView) vh.getViewById( R.id.tv_commends_people );

        RecommendBean.DataBean.PostDetailBean.LikeDetailsBean likeDetailsBean= (RecommendBean.DataBean.PostDetailBean.LikeDetailsBean) data;


        Glide.with( context ).load( likeDetailsBean.getHeadUrl() ).apply( new RequestOptions().circleCrop() ).into( header_image );
        header_name.setText( likeDetailsBean.getNickName() );
        header_item.setText( likeDetailsBean.getLikeTime() );
        commends_people.setText( "评论" + likeDetailsBean.getNickName() + pos+1);




    }
}
