package com.example.bookee.weatherinfo.findcity;




import android.support.annotation.NonNull;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements MvpContract.Model {


    private RetrofitWeatherRepository repository;

    Model(RetrofitWeatherRepository repository) {
        this.repository=  repository;

    }

    @Override
   public void fetchData(final String city, final MvpContract.FetchNewDataCallback callback) {
        Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
                callback.fetchNewData(city,response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable t) {
                callback.error();

            }
        });

    }


}
