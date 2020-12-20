package com.umeng.soexample.ui.tongpao.HomePersonalPprofile;

import android.content.Context;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.module.data.tongpao.PersonalBean;

import java.util.ArrayList;

public class PersonalAdapter extends BaseAdapter {


    private final Context context;
    private ArrayList<PersonalBean.DataBean> list;

    public PersonalAdapter(ArrayList<PersonalBean.DataBean> List, Context context) {
        super( List, context );
        this.context = context;
        this.list=List;
    }

    @Override
    protected int getLayout() {
        return R.layout.personal_adapter;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        PersonalBean.DataBean dataBean= (PersonalBean.DataBean) data;
        TextView tv_data = (TextView) vh.getViewById( R.id.tv_data );
        TextView tv_xz = (TextView) vh.getViewById( R.id.tv_xz );
        TextView tv_city = (TextView) vh.getViewById( R.id.tv_city );

        tv_city.setText( dataBean.getCity() );
        tv_data.setText( dataBean.getBirthday() );
        tv_xz.setText(  dataBean.getSignature());


    }
}
