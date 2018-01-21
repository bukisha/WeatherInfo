package com.example.bookee.weatherinfo.splash;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;
import static com.example.bookee.weatherinfo.utils.Constants.CELSIUS_FAHRENHEIT_DIFFERENCE;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    Presenter() {
        WeatherRepository repository = new RetrofitWeatherRepository();
        model = new Model(repository);
    }

    @Override
    public void fetchInitialWeather() {
        model.fetchInitialData(new MvpContract.InitialCityForecastFetchCallback() {
            @Override
            public void onSuccess(TemperatureData initTemp, long fetchDuration) {
                if (view == null) return;
                if (initTemp != null) {
                    view.startMainWithInitialData(initTemp, fetchDuration);
                } else {
                    view.error("Doslo je do greske");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.error("GRESKA" + t.toString());
            }
        });
    }

    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }
}



