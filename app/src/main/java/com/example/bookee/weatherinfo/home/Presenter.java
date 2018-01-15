package com.example.bookee.weatherinfo.home;


import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BaseView;

import static com.example.bookee.weatherinfo.home.DetailsActivity.CELSIOUS_FAHRENHEIT_DIFFERENCE;

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




    public void getData() {
        attachedDataInstance.fetchInitialData(new MvpContract.InitialDataFetchCallback() {
            @Override
            public void fetchData(CityForecastInfo info) {
                attachedView.recieveDataFromPresenter(info);
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
            double temp = extras.getDouble("temp");
            double wind = extras.getDouble("wind");
            int humid = extras.getInt("humid");
            temp=(temp-CELSIOUS_FAHRENHEIT_DIFFERENCE);
            String tempString = String.valueOf((int) temp);
            String windString = String.valueOf((int) wind);
            String humidString = String.valueOf(humid);
            attachedView.updateWithNewData(name, tempString, windString, humidString);

        } else {

            attachedView.errorHappened("Pogresno uneto ime grada");
        }
    }
}
