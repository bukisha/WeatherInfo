package com.example.bookee.weatherinfo.findcity;


import android.support.annotation.NonNull;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitCreator;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements MvpContract.Model {
    //todo Kada ovako postavis arhitekturu, ispada da je model taj koji odredjuje sta ce se i kada slati presenteru.
    //todo A situacija nije takva. Naime, prezenter trazi od modela podatke koji su potrebni da bi se formirao view.
    //todo Znaci, nemas povratnu spregu Model -> Presenter. Jednostavno, neka ti metoda getData(String s) podatke vraca nekim callbackom

    private MvpContract.Presenter     presenter; //todo Sa mojom metodom ces primetiti da ti ovde referenca na prezenter u opste i ne treba. Kao ni bind metoda.
    private RetrofitWeatherRepository repository;


    public Model(RetrofitWeatherRepository repository) {
        this.repository=  repository;

    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.presenter= (MvpContract.Presenter) presenter;
        repository.bindPresenter(presenter);
    }

    @Override
    public void getData(String s) {
        //todo Kada vidis "s" nemas pojma sta ovaj model dobija kao argument. Da li je to grad, api key....? sta?
        //todo takodje, "get" asocira na getter (znaci podaci su vec tu, ti ih uzimas).
        //todo Kada dovlacis podatke, koristi drugi prefix. Recimo "fetch"
        //todo pogledaj metodu ispod
//        Call<CityForecastInfo> call=repository.getApi().getForecast(s, RetrofitCreator.getApiKey());
//        call.enqueue(new Callback<CityForecastInfo>() {
//            @Override
//            public void onResponse(Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
//               // Log.i("city name ",response.body().getName());
//                presenter.passResultToView(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<CityForecastInfo> call, Throwable t) {//todo jednoslovne promenljive su dozvoljene samo kod iteriranje (for petlje). za sve ostalo secem prste na mestu :D
//                presenter.errorMessage("Doslo je do greske! ");//todo ako razmislis o ovome videces da tebi ovde u stvari Model odlucuje sta ce biti ispisano u View. To je zesce krsenje MVP granica
//            }
//        });
    }


    @Override
    public void fetchForecastInfoOfCity(String city, final MvpContract.CityForecastCallback callback) {
        Call<CityForecastInfo> call = repository.getApi().getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
                callback.data(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable throwable) {
                callback.error(throwable);
            }
        });
    }
}
