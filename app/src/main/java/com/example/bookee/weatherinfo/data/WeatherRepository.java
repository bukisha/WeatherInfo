package com.example.bookee.weatherinfo.data;

public interface WeatherRepository {

    void fetchWeatherForCity(String cityName,ForecastCallback callback);

    WeatherApi getApi();

    interface ForecastCallback {

        void onSucess(CityForecastInfo info);

        void onError(Throwable t);

    }
}
