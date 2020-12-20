package com.umeng.soexample.persenter.tongpao;

import com.umeng.soexample.base.BasePersenter;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.tongpao.IDiscover;
import com.umeng.soexample.module.data.tongpao.DiscoverBean;
import com.umeng.soexample.module.tongpao.DiscoverModel;

public class DiscoverPersenter extends BasePersenter<IDiscover.View> implements IDiscover.Persenter{
    IDiscover.View view;
    IDiscover.Model model;

    public DiscoverPersenter(IDiscover.View view) {
        this.view = view;
        this.model=new DiscoverModel();
    }


    @Override
    public void getDiscover() {
        this.model.getDiscover( new Callback() {
            @Override
            public void fail(String msg) {
                if(view!=null){
                    view.tips( msg );
                }
            }

            @Override
            public void success(Object o) {
                if(view!=null){
                    view.getDiscoverReturn( (DiscoverBean) o );
                }
            }
        } );

    }
}
