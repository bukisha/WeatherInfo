package com.example.bookee.weatherinfo.splash;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.WeatherRepository;
import static com.example.bookee.weatherinfo.utils.Constants.CELSIOUS_FAHRENHEIT_DIFFERENCE;

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
            public void onSuccess(CityForecastInfo info) {
                if (view == null) return;
                if (info != null) {
                    String name = info.getName();
                    String temp = String.valueOf(prepareTempForDisplay(info));
                    String wind = String.valueOf(info.getWind().getSpeed());
                    String humidity = String.valueOf(info.getMain().getHumidity());

                    view.sendWeatherInfoToMain(name, temp, wind, humidity);
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
        this.view=view;
    }

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
    }

}

