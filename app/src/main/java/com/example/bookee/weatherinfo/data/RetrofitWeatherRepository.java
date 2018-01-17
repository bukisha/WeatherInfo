package com.example.bookee.weatherinfo.data;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitWeatherRepository implements WeatherRepository{

    private WeatherApi api;

    public WeatherApi getApi() {
        if (api == null) {
            api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
        }
        return api;
    }

    public void  fetchWeatherForCity(String city, final ForecastCallback callback) {
        Call<CityForecastInfo> call = getApi().getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
                callback.onSuccess(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }
}