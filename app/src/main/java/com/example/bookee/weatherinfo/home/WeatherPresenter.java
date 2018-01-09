package com.example.bookee.weatherinfo.home;



import android.os.Bundle;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;
import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

import static com.example.bookee.weatherinfo.home.HomeWeatherDetailsActivity.CELSIOUS_FAHRENHEIT_DIFFERENCE;

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
        double temp=extras.getDouble("temp");
        double wind=extras.getDouble("wind");
        int humid=extras.getInt("humid");
        if(name!=null ) {     //TODO smisli kako da proveris da li su ti stigle i sotale vrednosti pored name-a
            temp=(temp-CELSIOUS_FAHRENHEIT_DIFFERENCE);
            String tempString=String.valueOf((int)temp);
            String windString=String.valueOf((int)wind);
            String humidString=String.valueOf((int)humid);
            attachedView.updateWithNewData(name,tempString,windString,humidString);

    } else {

        attachedView.errorHappened();
        }
    }
}
