package com.umeng.soexample.module.tongpao;

import com.umeng.soexample.api.TongpaoApi;
import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.tongpao.IRecommend;
import com.umeng.soexample.module.data.tongpao.BannerBean;
import com.umeng.soexample.module.data.tongpao.DiscussedBean;
import com.umeng.soexample.module.data.tongpao.HotUserBean;
import com.umeng.soexample.module.data.tongpao.PersonalBean;
import com.umeng.soexample.module.data.tongpao.RecommendBean;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class RecommendModel extends BaseModel implements IRecommend.Model {

    TongpaoApi api;

    public RecommendModel() {
        api = HttpManager.getInstance().getTongpaoApi();
    }

    @Override
    public void loadRecommend(Callback callback) {
        addDisposable(
                api.getRecommend()
                        .compose( RxUtils.rxScheduler() )
                        .subscribeWith( new CommonSubscriber<RecommendBean>( callback ) {

                            @Override
                            public void onNext(RecommendBean recommendBean) {
                                callback.success( recommendBean );
                            }
                        } )
        );
    }

    @Override
    public void getBean(Callback callback) {
        addDisposable(
                api.getBanner()
                        .compose( RxUtils.rxScheduler() )
                        .subscribeWith( new CommonSubscriber<BannerBean>( callback ) {

                            @Override
                            public void onNext(BannerBean result) {
                                callback.success( result );
                            }
                        } )
        );
    }


    @Override
    public void getDiscuessed(Callback callback) {
        addDisposable(
                api.getDiscussed()
                        .compose( RxUtils.rxScheduler() )
                        .subscribeWith( new CommonSubscriber<DiscussedBean>( callback ) {

                            @Override
                            public void onNext(DiscussedBean result) {
                                callback.success( result );
                            }
                        } )
        );
    }

    @Override
    public void getHotUser(Callback callback) {
        addDisposable(
                api.getHotUser()
                        .compose( RxUtils.rxScheduler() )
                        .subscribeWith( new CommonSubscriber<HotUserBean>( callback ) {

                            @Override
                            public void onNext(HotUserBean result) {
                                callback.success( result );
                            }
                        } )
        );
    }

    @Override
    public void getPersonal(Callback callback) {
        addDisposable(
                api.getPersonal()
                        .compose( RxUtils.rxScheduler() )
                        .subscribeWith( new CommonSubscriber<PersonalBean>( callback ) {

                            @Override
                            public void onNext(PersonalBean result) {
                                callback.success( result );
                            }
                        } )
        );
    }
}
