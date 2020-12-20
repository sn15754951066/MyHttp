package com.umeng.soexample.interfaces.tongpao;


import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.IBasePersenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.tongpao.SquareBean;

/**
 * 同袍广场功能接口锲约类
 */
public interface ISquare {

    interface View extends IBaseView {
        void getSquareReturn(SquareBean squareBean);
    }

    interface Persenter extends IBasePersenter<View> {
        void getSquare();
    }

    interface Model extends IModel {
        void getSquare(Callback callback);
    }


}
