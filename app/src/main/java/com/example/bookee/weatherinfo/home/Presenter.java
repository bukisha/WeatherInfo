package com.example.bookee.weatherinfo.home;


import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.mvp.BaseView;

import static com.example.bookee.weatherinfo.home.DetailsActivity.CELSIOUS_FAHRENHEIT_DIFFERENCE;

class Presenter implements MvpContract.Presenter {

    private MvpContract.View attachedView;
    private MvpContract.Model attachedDataInstance;

    Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);
        //attachedDataInstance.bindPresenter(this);

    }



    @Override
    public void bindDataModel(BaseModel model) {
        this.attachedDataInstance = (MvpContract.Model) model;

    }

    @Override
    public void bindView(BaseView view) {
        this.attachedView = (MvpContract.View) view;
    }


    @Override
    public void errorMessage(String message) {
        this.attachedView.errorHappened(message);
    }


    public void getData() {
        attachedDataInstance.fetchInitialData(new MvpContract.InitialDataFetchCallback() {
            @Override
            public void fetchData(CityForecastInfo info) {
                attachedView.recieveDataFromPresenter(info);
            }

            @Override
            public void error(Throwable t) {
                attachedView.errorHappened(t.toString());
            }
        });
    }



    public void passResultToView(CityForecastInfo body) {
        attachedView.recieveDataFromPresenter(body);
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
            String humidString = String.valueOf((int) humid);
            attachedView.updateWithNewData(name, tempString, windString, humidString);

        } else {

            attachedView.errorHappened("Pogresno uneto ime grada");
        }
    }
}
