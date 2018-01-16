package com.example.bookee.weatherinfo.findcity;




import android.support.annotation.NonNull;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.WeatherRepository;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

 /*   @Override
    public void fetchData(final String city, final MvpContract.FetchNewCityWeatherInfoCallback callback) {
        Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
                callback.fetchNewWeather(city,response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable t) {
                callback.error(t);

            }
        });

    }*/
}
