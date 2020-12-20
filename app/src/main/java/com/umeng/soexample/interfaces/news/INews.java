package com.umeng.soexample.interfaces.news;

import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IBasePersenter;
import com.umeng.soexample.interfaces.IModel;

/**
 * 新闻接口的契约类
 */
public interface INews {

    interface View extends IBaseView {
        void getNewsReturn(String result);
    }

    interface Persenter extends IBasePersenter{
        void getNews();
    }

    interface Model extends IModel{
        void getNews(Callback callback);
    }


}
