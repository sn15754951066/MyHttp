package com.umeng.soexample.module.tongpao;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.tongpao.IDiscover;
import com.umeng.soexample.module.data.tongpao.DiscoverBean;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class DiscoverModel extends BaseModel implements IDiscover.Model {

    @Override
    public void getDiscover(Callback callback) {
        CommonSubscriber<DiscoverBean> commonSubscriber = HttpManager.getInstance().getTongpaoApi()
                .getDiscover().compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DiscoverBean>(callback) {
                    @Override
                    public void onNext(DiscoverBean discoverBean) {
                        callback.success(discoverBean);
                    }
                });
        addDisposable(commonSubscriber);

    }
}
