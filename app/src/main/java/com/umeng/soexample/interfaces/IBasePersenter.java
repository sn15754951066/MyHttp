package com.umeng.soexample.interfaces;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();


}
