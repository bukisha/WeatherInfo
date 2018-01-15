package com.example.bookee.weatherinfo.mvp;


public interface BasePresenter {



    //binding presenter to its view
     void bindView(BaseView view);

    void unbindView();



}
