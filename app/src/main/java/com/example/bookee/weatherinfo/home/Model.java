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

    private Presenter presenter;
    private RetrofitWeatherRepository repository;

    public Model(RetrofitWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.presenter = (Presenter) presenter;
        repository.bindPresenter(presenter);
    }


    @Override
    public void getData() {
        //todo zasto ti je ovde "Belgrade" hardkodiran?
        //todo kako bi ga prosledio modelu ako ne zelis da ga hardkodiras?
        Call<CityForecastInfo> call = repository.getApi().getForecast("Belgrade", RetrofitCreator.getApiKey());
        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
                Log.i("city name ",response.body().getName());
                presenter.passResultToView(response.body());
            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                presenter.errorMessage("Doslo je do greske 1");
            }
        });

    }


}
