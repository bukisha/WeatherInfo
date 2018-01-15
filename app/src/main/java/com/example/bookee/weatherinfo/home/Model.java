package com.example.bookee.weatherinfo.home;

import android.util.Log;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Model implements MvpContract.Model {


    private RetrofitWeatherRepository repository;

    public Model(RetrofitWeatherRepository repository) {
        this.repository = repository;
    }





    @Override
    public void fetchInitialData(final MvpContract.InitialDataFetchCallback callback) {

        Call<CityForecastInfo> call = repository.getApi().getForecast("belgrade", RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
                callback.fetchData(response.body());
            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {

                callback.error("Greska dublje!");
            }
        });
    }


}
