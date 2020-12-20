package com.umeng.soexample.api;

import androidx.constraintlayout.helper.widget.Flow;

import com.umeng.soexample.module.data.CityData;
import com.umeng.soexample.module.data.WeatherData;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    String BASE_URL = "https://jisutqybmf.market.alicloudapi.com/weather/";

    @GET("city")
    Flowable<CityData> getCity();

    @GET("query")
    Flowable<WeatherData> queryWeathcer(@QueryMap Map<String,String> map);


}
