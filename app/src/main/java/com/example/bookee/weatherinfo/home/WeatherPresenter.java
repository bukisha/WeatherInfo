package com.example.bookee.weatherinfo.home;



import android.content.Intent;
import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;
import com.example.bookee.weatherinfo.findcity.FindCityActivity;
import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

class WeatherPresenter implements BasePresenter {

    private mvpView attachedView;
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
        this.attachedView= (mvpView) view;
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

    @Override
    public void unbindView() {
        attachedView=null;
    }

    public void ActionSomethingIsClicked() {
        attachedView.startNewActivity();

    }


    public void displayNewData(Bundle extras) {
        String name=extras.getString("name");
        Double temp=extras.getDouble("temp");
        Double wind=extras.getDouble("temp");
        Double humid=extras.getDouble("humid");
        if(name!=null && temp!=null && wind!=null && humid!=null) {
            String tempString=String.valueOf(temp);
            String windString=String.valueOf(wind);
            String humidString=String.valueOf(humid);
            attachedView.updateWithNewData(name,tempString,windString,humidString);

    } else {

        attachedView.errorHappened();
        }
    }
}
