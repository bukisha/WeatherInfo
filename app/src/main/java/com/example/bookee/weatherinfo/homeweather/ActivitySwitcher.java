package com.example.bookee.weatherinfo.homeweather;


import android.view.View;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class ActivitySwitcher implements BasePresenter {
    BaseView myView;

    public ActivitySwitcher(BaseView v) {
    myView=v;
    }

    @Override
    public void getData() {

    }

    @Override
    public void setText(CityForecastInfo s) {

    }

    @Override
    public void errorMessage() {

    }

    @Override
    public void floatingActionButtonIsClicked() {


        myView.startNewActivity();
    }
}
