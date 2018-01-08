package com.example.bookee.weatherinfo.mvp;


import com.example.bookee.weatherinfo.data.CityForecastInfo;

public interface BasePresenter {


    //binding presenter to its data model
    public void bindDataModel(BaseData model);
    //binding presenter to its view
    public void bindView(BaseView view);



    void errorMessage();


    void passResultToView(CityForecastInfo body);
}
