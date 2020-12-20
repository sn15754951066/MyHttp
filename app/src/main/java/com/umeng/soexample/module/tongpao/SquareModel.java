package com.umeng.soexample.module.tongpao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.tongpao.ISquare;
import com.umeng.soexample.module.data.tongpao.SquareBean;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class SquareModel extends BaseModel implements ISquare.Model {

    @Override
    public void getSquare(Callback callback) {

        CommonSubscriber<SquareBean> commonSubscriber = HttpManager.getInstance().getTongpaoApi()
                .getSquare().compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SquareBean>(callback) {
                    @Override
                    public void onNext(SquareBean squareBean) {
                        callback.success(squareBean);
                    }
                });
        addDisposable(commonSubscriber);
    }
}
