package com.umeng.soexample.ui.tongpao.FindAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.DiscoverBean;
import com.umeng.soexample.utils.TxtUtils;

import java.util.ArrayList;

public class FindAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<DiscoverBean.DataBean> discoverBeans;

    public FindAdapter(ArrayList<DiscoverBean.DataBean> List, Context context) {
        super( List, context );
        this.context = context;
        this.discoverBeans = List;
    }

    @Override
    protected int getLayout() {
        return R.layout.find_adapter;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        DiscoverBean.DataBean dataBean= (DiscoverBean.DataBean) data;

        ImageView mHotactivityimg = (ImageView) vh.getViewById( R.id.hotactivity_img );
        TextView mTxtTitle = (TextView) vh.getViewById( R.id.txt_title );
        TextView mTxtLocation = (TextView) vh.getViewById( R.id.txt_location );
        TextView mTxtTime = (TextView) vh.getViewById( R.id.txt_time );

        Glide.with( context ).load( dataBean.getCover() ).into( mHotactivityimg );
        TxtUtils.setTextView( mTxtTitle, dataBean.getTitle() );
        TxtUtils.setTextView( mTxtLocation, dataBean.getLocation() );
        TxtUtils.setTextView( mTxtTime, dataBean.getStartTime() );



    }
}
