package com.example.bookee.weatherinfo.details;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherRepository;



public class Model implements MvpContract.Model {
    private static final String BELGRADE="Belgrade";
    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchInitialData(final MvpContract.InitialCityForecastFetchCallback callback) {

        repository.fetchWeatherForCity(BELGRADE, new WeatherRepository.ForecastCallback() {

                 @Override
                 public void onSuccess(CityForecastInfo info) {
                     callback.onSuccess(info);
                 }

                 @Override
                 public void onError(Throwable t) {
                    callback.error(t);
                 }
             });
    }
}
