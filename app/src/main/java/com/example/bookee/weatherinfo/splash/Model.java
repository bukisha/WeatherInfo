package com.example.bookee.weatherinfo.splash;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherRepository;

public class Model implements  MvpContract.Model {
    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository = repository;
    }
    @Override
    public void fetchInitialData(final MvpContract.InitialCityForecastFetchCallback callback) {
        final long startFetchTime=System.currentTimeMillis();
        repository.fetchWeatherForCity("belgrade", new WeatherRepository.ForecastCallback() {

            long fetchingDuration=System.currentTimeMillis()-startFetchTime;
            @Override
            public void onSuccess(CityForecastInfo info) {
               callback.onSuccess(info,fetchingDuration);
            }
            @Override
            public void onError(Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
