package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import static com.example.bookee.weatherinfo.utils.Constants.CELSIOUS_FAHRENHEIT_DIFFERENCE;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model attachedDataInstance;
    private MvpContract.View attachedView;

    Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);
    }

    @Override
    public void bindView(MvpContract.View view) {
        this.attachedView = view;
    }

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
    }

    @Override
    public void getData(String desiredCity) {

        attachedDataInstance.fetchData(desiredCity, new MvpContract.FetchNewCityWeatherInfoCallback() {

            @Override
            public void fetchNewWeather(CityForecastInfo info) {
                if (info != null) {
                    String name = info.getName();
                    String temp = String.valueOf(prepareTempForDisplay(info));
                    String wind = String.valueOf(info.getWind().getSpeed());
                    String humidity = String.valueOf(info.getMain().getHumidity());

                    attachedView.reciveDataFromPresenter(name, temp, wind, humidity);
                } else {
                    attachedView.errorHappened("porgesno uneto ime grada");
                }
            }

            @Override
            public void error(Throwable t) {

                attachedView.errorHappened("GRESKA" + t.toString());
            }
        });
    }

    @Override
    public void unbindView() {
        attachedView = null;
    }
}
