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


    private Call<CityForecastInfo> call;
    private BasePresenter presenter;

    public WeatherData(BasePresenter p) {
        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi  myApi = retrofit.create(WeatherApi.class);
         call = myApi.getForecast("Belgrade",WeatherApi.API_KEY);

         this.presenter =p;


    }

    @Override
    public void getData() {

        call.enqueue(new Callback<CityForecastInfo>() {
            @Override
            public void onResponse( Call<CityForecastInfo> call, Response<CityForecastInfo> response) {
                presenter.setText(response.body());


            }

            @Override
            public void onFailure(Call<CityForecastInfo> call, Throwable t) {
                Log.i("GET URL JE ",call.request().toString());
                Log.i("greska je " ,t.toString());
                  presenter.errorMessage();
            }
        });

    }
}




