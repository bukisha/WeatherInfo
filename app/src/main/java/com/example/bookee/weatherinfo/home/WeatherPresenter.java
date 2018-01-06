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

    WeatherPresenter(BaseView view) {
    this.attachedView =view;
    this.attachedDataInstance = (BaseData) new WeatherData(this);
    }

    @Override
    public void getData() {

          attachedDataInstance.getData();

    }

    @Override
    public void setText(CityForecastInfo s) {
        attachedView.setText(s);
    }

    @Override
    public void errorMessage() {
        attachedView.errorHappened();
    }

    @Override
    public void floatingActionButtonIsClicked() {
        attachedView.startNewActivity();
    }


}
