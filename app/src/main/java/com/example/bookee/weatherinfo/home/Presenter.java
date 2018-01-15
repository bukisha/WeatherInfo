package com.example.bookee.weatherinfo.home;


import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BaseView;


import static com.example.bookee.weatherinfo.utils.Constants.CELSIOUS_FAHRENHEIT_DIFFERENCE;

class Presenter implements MvpContract.Presenter {

    private MvpContract.View attachedView;
    private MvpContract.Model attachedDataInstance;

    Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);


    }

    @Override
    public void bindView(BaseView view) {
        this.attachedView = (MvpContract.View) view;
    }

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
    }



    public void getData() {
        attachedDataInstance.fetchInitialData(new MvpContract.InitialDataFetchCallback() {
            @Override
            public void fetchData(CityForecastInfo info) {

                String name=info.getName();
                String temp=String.valueOf(prepareTempForDisplay(info));
                String wind=String.valueOf(info.getWind().getSpeed());
                String humidity=String.valueOf(info.getMain().getHumidity());
                attachedView.updateWithNewData(name,temp,wind,humidity);
            }

            @Override
            public void error() {

                attachedView.errorHappened("GRESKA");
            }
        });
    }




    @Override
    public void unbindView() {
        attachedView = null;
    }

    public void ActionSomethingIsClicked() {
        attachedView.startNewActivity();

    }


    public void displayNewData(Bundle extras) {


        if (!extras.isEmpty() ) {
            String name = extras.getString("name");
            String temp = extras.getString("temp");
            String wind = extras.getString("wind");
            String humid = extras.getString("humid");


            attachedView.updateWithNewData(name, temp, wind, humid);

        } else {

            attachedView.errorHappened("Pogresno uneto ime grada");
        }
    }
}
