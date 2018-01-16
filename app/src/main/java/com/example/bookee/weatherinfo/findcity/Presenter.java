package com.example.bookee.weatherinfo.findcity;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;

import static com.example.bookee.weatherinfo.utils.Constants.CELSIOUS_FAHRENHEIT_DIFFERENCE;

//todo empty lines
public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model attachedDataInstance;//todo ako imamo instancu modela, onda se ta instanca zove, recimo attachedModelInstance a ne dataInstance. Takodje, svaka je attached. tako da ne moramo to pisati. Dovoljno je private MvpContract.Model model;
    private MvpContract.View  attachedView;//todo isto kao i gore. private MvpContract.Viewview

    Presenter() {
//        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
//        attachedDataInstance = new Model(repository);

// todo sobzirom da ovu instancu repositoryja koristis samo ovde, onda moze ovako:
        attachedDataInstance = new Model(new RetrofitWeatherRepository());
    }



    @Override
    public void bindView(MvpContract.View view) {
       this.attachedView=  view;
    }

    private int prepareTempForDisplay(CityForecastInfo info) {
        return (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
    }

   @Override
   public void getData(String desiredCity) {
        attachedDataInstance.fetchData(desiredCity, new MvpContract.FetchNewDataCallback() {


            @Override
            public void fetchNewData(String cityName, CityForecastInfo info) {
               if(info!=null) {
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
            public void error() {

                attachedView.errorHappened("GRESKA");
            }
        });

    }



    @Override
    public void unbindView() {
        attachedView=null;
    }



}
