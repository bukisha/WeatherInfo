package com.example.bookee.weatherinfo.details;

import com.example.bookee.weatherinfo.data.TemperatureData;

class Presenter implements MvpContract.Presenter {

    private MvpContract.View view;
    //private MvpContract.Model model;

    Presenter() {}
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
    if (newTemperature!=null) {
        view.updateWithNewData(newTemperature);
    } else {
        view.errorHappened("Pogresno uneto ime grada");
    }
}






}
