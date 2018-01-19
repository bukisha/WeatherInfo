package com.example.bookee.weatherinfo.details;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.data.WeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIUS_FAHRENHEIT_DIFFERENCE;

class Presenter implements MvpContract.Presenter {

    private MvpContract.View view;
    private MvpContract.Model model;

    Presenter() {
        WeatherRepository repository = new RetrofitWeatherRepository();
        model = new Model(repository);
    }
    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIUS_FAHRENHEIT_DIFFERENCE);
    }

    public void getData() {
        model.fetchInitialData(new MvpContract.InitialCityForecastFetchCallback() {
            @Override
            public void onSuccess(CityForecastInfo info) {
                if (view == null) return;
                if (info != null) {
                    String name = info.getName();
                    String temp = String.valueOf(prepareTempForDisplay(info));
                    String wind = String.valueOf(info.getWind().getSpeed());
                    String humidity = String.valueOf(info.getMain().getHumidity());

                    Bundle weatherData=new Bundle();
                    weatherData.putString("name",name);
                    weatherData.putString("temp",temp);
                    weatherData.putString("wind",wind);
                    weatherData.putString("humid",humidity);

                    view.updateWithNewData(new TemperatureData(weatherData));
                } else {
                    view.errorHappened("Doslo je do greske");
                }
            }
            @Override
            public void error(Throwable t) {
                view.errorHappened("GRESKA" + t.toString());
            }
        });
    }
    @Override
    public void unbindView() {
        view = null;
    }

    public void actionSomethingIsClicked() {
        view.startNewActivity();
    }
    @Override
    public void displayNewData(Bundle extras) {
    if (view == null) return;
    if (!extras.isEmpty()) {
        view.updateWithNewData(new TemperatureData(extras));
    } else {
        view.errorHappened("Pogresno uneto ime grada");
    }
}






}
