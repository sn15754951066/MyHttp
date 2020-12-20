package com.umeng.soexample.base;

import com.umeng.soexample.interfaces.IModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * M层基类 处理网络数据加载效率问题通过被压式
 */
public abstract class BaseModel implements IModel {

    CompositeDisposable disposableSet = new CompositeDisposable();

    /**
     * 把当前的网络请求添加到缓存
     * @param disposable
     */
    @Override
    public void addDisposable(Disposable disposable) {
        disposableSet.add(disposable);
    }

    /**
     * 取消还未进行的网络请求
     */
    @Override
    public void clear() {
        disposableSet.clear();
    }
}
