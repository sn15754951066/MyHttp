package com.umeng.soexample.ui.news;

import android.os.Bundle;
import android.widget.TextView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.news.INews;
import com.umeng.soexample.persenter.news.NewsPersenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity<NewsPersenter> implements INews.View {

    @BindView(R.id.txt)
    TextView txt;

    @Override
    protected int getLayout() {
        return R.layout.activity_news;
    }

    //找控件
    @Override
    protected void initView() {

    }

    @Override
    protected NewsPersenter createPersenter() {
        return new NewsPersenter(this);
    }

    //读取intent中的数据获取网络请求的触发
    @Override
    protected void initData() {
        persenter.getNews();
    }


    @Override
    public void getNewsReturn(String result) {
        txt.setText(result);
    }

}
