package com.example.bookee.weatherinfo.data;


import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//todo Nadji na netu Repository pattern. Njegov cilj nije da on hostuje API. Njegov cilj je da apstrahuje rad sa podacima. Pogledaj kako bi trebalo, recimo.
public class RetrofitWeatherRepository  {
    //todo Ovde izgleda da mozemo da radimo LazyLoading. Time izbacujemo c-tor, smanjujemo broj linija koda i instanciramo api samo ako nam zatreba.
    private WeatherApi api;

    public WeatherApi getApi() {
        if (api == null){
            api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
        }
        return api;
    }


    public void fetchForecastForTheCity(String city, final ForecastCallback callback) {
        Call<CityForecastInfo> call = api.getForecast(city, RetrofitCreator.getApiKey());

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse(@NonNull Call<CityForecastInfo> call, @NonNull Response<CityForecastInfo> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CityForecastInfo> call, @NonNull Throwable throwable) {
                callback.onError(throwable);

            }
        });
    }

    public interface ForecastCallback {
        void onSuccess(CityForecastInfo cityForecastInfo);

        void onError(Throwable throwable);
    }


    /*
    private WeatherApi api;

    public RetrofitWeatherRepository() {
        this.api = RetrofitCreator.createRetrofit().create(WeatherApi.class);
    }

    public WeatherApi getApi() {
        return  api;
    }
     */
}