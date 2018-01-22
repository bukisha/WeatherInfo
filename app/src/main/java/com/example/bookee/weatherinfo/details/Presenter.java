package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.list.ListActivity;

class Presenter implements MvpContract.Presenter {

    private MvpContract.View view;
    //private MvpContract.Model model;

    Presenter() {
    }

    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    public void actionSomethingIsClicked() {
        view.startNewActivity();
    }
    @Override
    public void displayNewData(TemperatureData newTemperature) {
        if (view == null) return;
        if (newTemperature != null) {
            view.updateWithNewData(newTemperature);
        } else {
            view.errorHappened("Invalid city name");
        }
    }

    @Override
    public void menuAction(AppCompatActivity detailsActivity, int item, MvpContract.PresenterActivityCallback callback) {
        switch(item) {
            case 0: {
                Intent openList=new  Intent(detailsActivity,ListActivity.class);
                callback.openActivity(openList);
            }
        }
    }
}




