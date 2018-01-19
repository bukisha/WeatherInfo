package com.example.bookee.weatherinfo.details;

import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.WeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIOUS_FAHRENHEIT_DIFFERENCE;

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
        return (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
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

                    view.updateWithNewData(name, temp, wind, humidity);
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

    public void ActionSomethingIsClicked() {//todo capitalisation
        view.startNewActivity();
    }

//todo a sta mislis da umesto da guras 4 argumenta, sve spakujes u jednu klasu. Recimo TemperatureData.
    //Primetices na koliko mesta imas slicnu konstrukciju gde manipulises sa ova 4 podatka.
// I dobijes nesto tipa:
@Override
public void displayNewData(Bundle extras) {
    if (view == null) return;
    if (!extras.isEmpty()) {
        view.updateWithNewData(new TemperatureData(extras));//todo i sada kada pogledas metodu videces da ona ne radi nista drugo osim delegiranja sta ce view da iscrta.
    } else {
        view.errorHappened("Pogresno uneto ime grada");
    }
}


    //todo ovo munes u View ili kao nezavisnu klasu u data package
    class TemperatureData {
        private String name;
        private String temp;
        private String wind;
        private String humidity;

        public TemperatureData(Bundle extras) {
            if (!extras.isEmpty()){
                name = extras.getString("name");
                temp = extras.getString("temp");
                wind = extras.getString("wind");
                humidity = extras.getString("humid");
            }else{
                throw new IllegalArgumentException("It looks like passed Bundle argument is empty!");
            }
        }
    }

//    @Override
//    public void displayNewData(Bundle extras) {
//        if (view == null) return;
//        if (!extras.isEmpty()) {
//            String name = extras.getString("name");
//            String temp = extras.getString("temp");
//            String wind = extras.getString("wind");
//            String humid = extras.getString("humid");
//
//            view.updateWithNewData(name, temp, wind, humid);
//        } else {
//            view.errorHappened("Pogresno uneto ime grada");
//        }
//    }
}
