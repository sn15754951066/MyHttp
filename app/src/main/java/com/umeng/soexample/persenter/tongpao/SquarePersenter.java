package com.umeng.soexample.persenter.tongpao;

import android.util.Log;

import com.umeng.soexample.base.BasePersenter;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.tongpao.SquareBean;
import com.umeng.soexample.module.tongpao.SquareModel;

public class SquarePersenter extends BasePersenter<ISquare.View> implements ISquare.Persenter {

    ISquare.View view;
    ISquare.Model model;

    public SquarePersenter(ISquare.View view) {
        this.view = view;
        this.model = new SquareModel();
    }

    @Override
    public void getSquare() {
        this.model.getSquare( new Callback() {
            @Override
            public void fail(String msg) {
                if(view!=null){
                    view.tips( msg );
                    Log.e( "TAG", "fail: "+msg  );
                }
            }

            @Override
            public void success(Object o) {
                if(view!=null){
                    view.getSquareReturn( (SquareBean) o );
                }
            }
        } );

    }
}
