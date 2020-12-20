package com.umeng.soexample.persenter.news;

import com.umeng.soexample.base.BasePersenter;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.news.INews;
import com.umeng.soexample.module.news.NewsModel;

public class NewsPersenter extends BasePersenter implements INews.Persenter {

    INews.View view;
    INews.Model model;

    public NewsPersenter(INews.View view){
        this.view = view;
        model = new NewsModel();
    }


    @Override
    public void getNews() {
        this.model.getNews(new Callback() {
            @Override
            public void fail(String msg) {
                if(view != null){
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if(view != null){
                    view.getNewsReturn((String) o);
                }
            }
        });
    }
}
