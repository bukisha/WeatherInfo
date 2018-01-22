package com.example.bookee.weatherinfo.splash;

import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;

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
                view.startMainWithInitialData(initTemp, fetchDuration);
            }
            @Override
            public void onFailure(Throwable t) {
                view.error(t.getMessage());
            }
        });
    }
    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }
}



