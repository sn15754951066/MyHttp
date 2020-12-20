package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.Callback;
import com.umeng.soexample.interfaces.IBasePersenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.module.data.CityData;
import com.umeng.soexample.module.data.WeatherData;

import java.util.Map;

public interface IHome {

    //home业务下的 v层接口
    interface View extends IBaseView {
        //获取城市数据返回
        void getCityReturn(CityData result);
        //获取天气数据返回
        void getWeatherReturn(WeatherData result);
    }

    //home业务下 P层接口
    interface Persenter extends IBasePersenter<View> {
        void getCity();
        //获取天气数据--> v层的列表选择
        void getWeather(Map<String,String> map);
    }

    //home业务下的model
    interface Model extends IModel {
        void getCity(Callback callback);

        //获取天气数据 --> p层调用m层的接口
        void getWeatcher(Map<String,String> map,Callback callback);
    }

}
