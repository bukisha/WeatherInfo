package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherRepository;

public class Model implements MvpContract.Model {

    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository=  repository;
    }

    @Override
    public void fetchData(String city, final MvpContract.FetchNewCityWeatherInfoCallback callback) {

        repository.fetchWeatherForCity(city, new WeatherRepository.ForecastCallback() {
            @Override
            public void onSucess(CityForecastInfo info) {
                callback.fetchNewWeather(info);
            }

            @Override
            public void onError(Throwable t) {
                callback.error(t);
            }
        });
    }
}
