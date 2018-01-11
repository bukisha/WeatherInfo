package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;


public interface mvpContract  {

   interface View extends BaseView {
       void startNewActivity();
//TODO koje metode presenter za ovaj view moze da poziva
   }

   interface Presenter extends BasePresenter {

       public void passResultToView(CityForecastInfo body);

       void errorMessage();

   }

   interface Model extends BaseModel {

       public void getData(String s);
   }
}
