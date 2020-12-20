package com.umeng.soexample.ui.tongpao.SquareAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.SquareBean;

import java.util.ArrayList;

public class AllAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SquareBean.DataBean.DynamicsBean> dynamicsBeans;

    public AllAdapter( Context context, ArrayList<SquareBean.DataBean.DynamicsBean> dynamicsBeans) {
        super( dynamicsBeans, context );
        this.context = context;
        this.dynamicsBeans = dynamicsBeans;
    }

    @Override
    protected int getLayout() {
        return R.layout.all_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SquareBean.DataBean.DynamicsBean dynamicsBean = (SquareBean.DataBean.DynamicsBean) data;

        ImageView icon = (ImageView) vh.getViewById( R.id.iv_icon );
        TextView createTime = (TextView) vh.getViewById( R.id.tv_data );
        NineGridImageView nv = (NineGridImageView) vh.getViewById( R.id.nv );
        TextView NickName = (TextView) vh.getViewById( R.id.tv_nickName );

        Glide.with( context ).load( dynamicsBean.getHeadUrl() ).apply( new RequestOptions().circleCrop() ).into( icon );
        createTime.setText( dynamicsBean.getCreateTime() );

        TextView content = (TextView) vh.getViewById( R.id.tv_desc );
        content.setText( dynamicsBean.getContent() );

        //检测列表和footer位置
        if(dynamicsBean.getNickName() != null){
            //NickName.setText( dynamicsBean.getNickName() );
        }


        ArrayList<String> images = new ArrayList<>();
        for (SquareBean.DataBean.DynamicsBean.ImagesBean item : dynamicsBean.getImages()) {
            images.add( item.getFilePath() );
        }
        nv.setAdapter( new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                Glide.with( context ).load( o ).apply( RequestOptions.bitmapTransform( new RoundedCorners( 20 ) ) ).into( imageView );
            }
        } );


    }

}
