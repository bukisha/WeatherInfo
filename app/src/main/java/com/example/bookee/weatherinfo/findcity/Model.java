package com.example.bookee.weatherinfo.findcity;


import android.util.Log;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements MvpContract.Model {

   // private MvpContract.Presenter presenter;
    private RetrofitWeatherRepository repository;

    public Model(RetrofitWeatherRepository repository) {
        this.repository=  repository;

    }

    @Override
   public void fetchData(final String city, final MvpContract.FetchNewDataCallback callback) {
        Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
                callback.fetchNewData(city,response.body());
            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                callback.error(t.toString());
            }
        });

    }

//    @Override
//    public void bindPresenter(BasePresenter presenter) {
//        this.presenter= (MvpContract.Presenter) presenter;
//        repository.bindPresenter(presenter);
//    }

//    @Override
//    public void fetchData(String city) {
//
//
//        Call<CityForecastInfo> call=repository.getApi().getForecast(city, RetrofitCreator.getApiKey());
//
//        call.enqueue(new Callback<CityForecastInfo>() {
//            @Override
//            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
//               // Log.i("city name ",response.body().getName());
//                presenter.passResultToView(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
//                presenter.errorMessage("Doslo je do greske! ");
//            }
//        });
//
//    }
}