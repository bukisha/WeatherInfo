package com.example.bookee.weatherinfo.search;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIUS_FAHRENHEIT_DIFFERENCE;

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

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIUS_FAHRENHEIT_DIFFERENCE);
    }
    @Override
    public void getData(String desiredCity) {
        model.fetchData(desiredCity, new MvpContract.FetchNewCityWeatherInfoCallback() {
            @Override
            public void fetchNewWeather(CityForecastInfo info) {
                if (view == null) return;
                if (info != null) {
                    String name = info.getName();
                    String temp = String.valueOf(prepareTempForDisplay(info));
                    String wind = String.valueOf(info.getWind().getSpeed());
                    String humidity = String.valueOf(info.getMain().getHumidity());

                    view.receiveDataFromPresenter(name, temp, wind, humidity);
                } else {
                    view.errorHappened("Porgesno uneto ime grada");
                }
            }
            @Override
            public void error(Throwable t) {
                view.errorHappened("GRESKA" + t.toString());
            }
        });
    }
    @Override
    public void unbindView() {
        view = null;
    }
}
