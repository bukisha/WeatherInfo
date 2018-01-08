package com.example.bookee.weatherinfo.home;



import android.content.Intent;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;
import com.example.bookee.weatherinfo.findcity.FindCityActivity;
import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

class WeatherPresenter implements BasePresenter {

    private BaseView attachedView;
    private BaseData attachedDataInstance;

    WeatherPresenter() {
        this.attachedDataInstance = (BaseData) new WeatherData(this);
    }




    @Override
    public void bindDataModel(BaseData model) {
        this.attachedDataInstance=model;

    }

    @Override
    public void bindView(BaseView view) {
        this.attachedView=view;
    }


    @Override
    public void errorMessage() {
        attachedView.errorHappened();
    }


    public void getData() {
        attachedDataInstance.getData();
    }

    public void passResultToView(CityForecastInfo body) {
       attachedView.recieveDataFromPresenter(body);
    }

    public void ActionSomethingIsClicked() {
        attachedView.startNewActivity();

    }
}
