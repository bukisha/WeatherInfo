package com.example.bookee.weatherinfo.data;


import com.example.bookee.weatherinfo.mvp.BasePresenter;

public class RetrofitWeatherRepository  {


    private WeatherApi api;

    public RetrofitWeatherRepository() {
        this.api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
    }




    public WeatherApi getApi() {

        return  api;
    }
}