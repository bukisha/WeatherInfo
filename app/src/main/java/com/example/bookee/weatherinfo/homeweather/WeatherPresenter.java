package com.example.bookee.weatherinfo.homeweather;



import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherData;
import com.example.bookee.weatherinfo.mvp.BaseData;
import com.example.bookee.weatherinfo.mvp.BasePresenter;
import com.example.bookee.weatherinfo.mvp.BaseView;

class WeatherPresenter implements BasePresenter {
    private BaseView myView;
    private BaseData myDataInstance;

    WeatherPresenter(BaseView view) {
    this.myView=view;
    this.myDataInstance= (BaseData) new WeatherData(this);
    }

    @Override
    public void getData() {

          myDataInstance.getData();

    }

    @Override
    public void setText(CityForecastInfo s) {
        myView.setText(s);
    }

    @Override
    public void errorMessage() {
        myView.errorHappened();
    }

    @Override
    public void floatingActionButtonIsClicked() {

    }


}
