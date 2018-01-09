package com.example.bookee.weatherinfo.findcity;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;
import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class FindCityPresenter implements BasePresenter {
    private BaseData attachedDataInstance;
    private BaseView attachedView;

    public FindCityPresenter() {
        this.attachedDataInstance = new WeatherData(this);
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

    @Override
    public void passResultToView(CityForecastInfo body) {
        attachedView.recieveDataFromPresenter(body);
    }

    @Override
    public void unbindView() {
        attachedView=null;
    }


    public void getData(String desiredCity) {
        attachedDataInstance.getData(desiredCity);
    }
}
