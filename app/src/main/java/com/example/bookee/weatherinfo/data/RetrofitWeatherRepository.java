package com.example.bookee.weatherinfo.data;


import com.example.bookee.weatherinfo.home.mvpContract;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitWeatherRepository implements BaseModel {

    private BasePresenter presenter;
    private WeatherApi api;

    public RetrofitWeatherRepository() {
        this.api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
    }


    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.presenter=presenter;
    }

    public WeatherApi getApi() {

        return  api;
    }
}