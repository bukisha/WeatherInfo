package com.example.bookee.weatherinfo.home;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class ActivitySwitcherPresenter implements BasePresenter {
    private BaseView attachedView;

    public ActivitySwitcherPresenter(BaseView v) {
    attachedView =v;
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
        attachedView.startNewActivity();
    }
}
