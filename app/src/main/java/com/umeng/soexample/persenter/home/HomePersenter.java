package com.umeng.soexample.persenter.home;

import android.view.View;

import com.umeng.soexample.base.BasePersenter;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.home.IHome;
import com.umeng.soexample.module.data.CityData;
import com.umeng.soexample.module.home.HomeModel;
import com.umeng.soexample.module.data.WeatherData;

import java.util.Map;

public class HomePersenter extends BasePersenter<IHome.View> implements IHome.Persenter {


    IHome.View view;
    IHome.Model model;



    public HomePersenter(IHome.View view){
        this.view = view;
        this.model = new HomeModel();
    }

    @Override
    public void getCity() {
        this.view.loading(View.VISIBLE);
        this.model.getCity(new Callback() {
            @Override
            public void fail(String msg) {
                if(view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if(view != null){
                    view.loading(View.GONE);
                    view.getCityReturn((CityData) o);
                }
            }
        });
    }

    //获取天气  给V层调用
    @Override
    public void getWeather(Map<String, String> map) {
        view.loading(View.VISIBLE);
        this.model.getWeatcher(map, new Callback() {
            @Override
            public void fail(String msg) {
                if(view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if(view != null){
                    view.loading(View.GONE);
                    view.getWeatherReturn((WeatherData) o);
                }
            }
        });
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
        //释放当前页面还未完成的网络请求
        if(model != null){
            model.clear();
        }
    }
}
