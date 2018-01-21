package com.example.bookee.weatherinfo.search;

import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.data.TemperatureData;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model model;
    private MvpContract.View view;

    Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        model = new Model(repository);
    }
    @Override
    public void bindView(MvpContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(String desiredCity) {
        model.fetchData(desiredCity, new MvpContract.FetchNewCityWeatherInfoCallback() {
            @Override
            public void fetchNewWeather(TemperatureData newTemperatureData) {
                if (view == null) return;
                if (newTemperatureData != null) {
                    view.receiveDataFromPresenter(newTemperatureData);
                } else {
                    view.errorHappened("Pogresno uneto ime grada");
                }
            }
            @Override
            public void error(Throwable t) {
                view.errorHappened(t.toString());
            }
        });
    }
    @Override
    public void unbindView() {
        view = null;
    }
}
