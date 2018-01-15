package com.example.bookee.weatherinfo.data;




public class RetrofitWeatherRepository  {


    private WeatherApi api;

    public RetrofitWeatherRepository() {
        this.api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
    }




    public WeatherApi getApi() {

        return  api;
    }
}