package com.example.bookee.weatherinfo.details;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.WeatherRepository;
import static com.example.bookee.weatherinfo.utils.Constants.BELGRADE;


public class Model implements MvpContract.Model {

    private WeatherRepository repository;

    Model(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchInitialData(final MvpContract.InitialCityForecastFetchCallback callback) {

        repository.fetchWeatherForCity(BELGRADE, new WeatherRepository.ForecastCallback() {

                 @Override
                 public void onSucess(CityForecastInfo info) {
                     callback.fetchWeatherInfo(info);
                 }

                 @Override
                 public void onError(Throwable t) {
                    callback.error(t);
                 }
             });
    }
}
