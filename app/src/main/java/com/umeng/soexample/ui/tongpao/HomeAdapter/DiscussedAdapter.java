package com.umeng.soexample.ui.tongpao.HomeAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.DiscussedBean;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class DiscussedAdapter extends BaseAdapter {

    private Context context;
    private List<DiscussedBean.DataBean> list;

    public DiscussedAdapter(List mList, Context context) {
        super( mList, context );
        this.context=context;
        this.list=mList;
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_discussed_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        DiscussedBean.DataBean dataBean = (DiscussedBean.DataBean) data;

        //图片
        ImageView imageIcon = (ImageView) vh.getViewById( R.id.image_icon );
        TextView TxtTag = (TextView) vh.getViewById( R.id.txt_tag );
        TextView tv_title = (TextView) vh.getViewById( R.id.tv_title );
        TextView tv_number  = (TextView) vh.getViewById( R.id.tv_number );


        TxtUtils.setTextView( TxtTag,"活动");
        Glide.with( context ).load(dataBean.getImageUrl() ).into( imageIcon );
        TxtUtils.setTextView( tv_title,"#"+dataBean.getName()+"#");
        TxtUtils.setTextView( tv_number,dataBean.getUseTime()+"人参与");


    }

}
