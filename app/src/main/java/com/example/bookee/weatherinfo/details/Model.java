package com.example.bookee.weatherinfo.details;
import android.support.annotation.NonNull;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.WeatherRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Model implements MvpContract.Model {

    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchInitialData(final MvpContract.InitialCityForecastFetchCallback callback) {

        repository.fetchWeatherForCity("belgrade", new WeatherRepository.ForecastCallback() {

                 @Override
                 public void onSucess(CityForecastInfo info) {
                     callback.fetchWeatherInfo(info);
                 }

                 @Override
                 public void onError(Throwable t) {
                    callback.error(t);
                 }
             });
    }
}
