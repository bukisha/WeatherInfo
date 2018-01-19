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
            public void onSuccess(CityForecastInfo info,long fetchDuration) {
                if (view == null) return;
                if (info != null) {
                    String name = info.getName();
                    String temp = String.valueOf(prepareTempForDisplay(info));
                    String wind = String.valueOf(info.getWind().getSpeed());
                    String humidity = String.valueOf(info.getMain().getHumidity());
                    Bundle initData=new Bundle();
                    initData.putString("name",name);
                    initData.putString("temp",temp);
                    initData.putString("wind",wind);
                    initData.putString("humid",humidity);

                    view.startMainWithInitialData(new TemperatureData (initData),fetchDuration);
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
        return (int) (info.getMain().getTemp() - CELSIUS_FAHRENHEIT_DIFFERENCE);
    }

}

