package com.example.bookee.weatherinfo.mvp;


import android.view.View;

import com.example.bookee.weatherinfo.data.CityForecastInfo;

public interface BasePresenter {

    public void getData();


    void setText(CityForecastInfo s);

    void errorMessage();

    public void floatingActionButtonIsClicked();
}
