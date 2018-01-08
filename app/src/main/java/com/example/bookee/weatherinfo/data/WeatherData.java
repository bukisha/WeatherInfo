package com.example.bookee.weatherinfo.data;
import android.util.Log;

import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherData implements BaseData {
    private static final String TAG = "WeatherData";
    private Call<CityForecastInfo> call;
    private BasePresenter presenter;
    private  Retrofit attachedRetrofitClient=null;
    private  WeatherApi myApi;

    public Retrofit BuildRetrofit() {

        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return  retrofit;
    }

    public WeatherData(BasePresenter p) {

        this.attachedRetrofitClient=BuildRetrofit();
        this.presenter =p;
    }

    @Override
    public void getData() {

         myApi = attachedRetrofitClient.create(WeatherApi.class);
        call = myApi.getForecast("Belgrade",WeatherApi.API_KEY);

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse( Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
                presenter.passResultToView(response.body());



            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                Log.i("GET URL JE ",call.request().toString());
                Log.i("greska je " ,t.toString());
                  presenter.errorMessage();
            }
        });

    }

    @Override
    public void getData(String s) {
          myApi = attachedRetrofitClient.create(WeatherApi.class);
        call = myApi.getForecast(s,WeatherApi.API_KEY);

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse( Call<CityForecastInfo> call, Response<CityForecastInfo> response) {

                presenter.passResultToView(response.body());


            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                Log.i("GET URL JE ",call.request().toString());
                Log.i("greska je " ,t.toString());
                //todo kada imas exception, onda koristi Log.e tag
                Log.e(TAG, "onFailure: ", t);
                presenter.errorMessage();
            }
        });


    }
}




