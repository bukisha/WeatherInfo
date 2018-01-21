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

 /*   public void getData() {
        model.fetchInitialData(new MvpContract.InitialCityForecastFetchCallback() {
            @Override
            public void onSuccess(TemperatureData initialTemperatureData) {
                if (view == null) return;
                if(initialTemperatureData!=null) {
                    view.updateWithNewData(initialTemperatureData);
                } else {
                    view.errorHappened("Doslo je do greske");
                }
            }
            @Override
            public void error(Throwable t) {
                view.errorHappened("GRESKA" + t.toString());
            }
        });
    }*/
    @Override
    public void unbindView() {
        view = null;
    }

    public void actionSomethingIsClicked() {
        view.startNewActivity();
    }
    @Override
    public void displayNewData(TemperatureData newTemperature) {
    if (view == null) return;
    if (newTemperature!=null) {
        view.updateWithNewData(newTemperature);
    } else {
        view.errorHappened("Pogresno uneto ime grada");
    }
}






}
