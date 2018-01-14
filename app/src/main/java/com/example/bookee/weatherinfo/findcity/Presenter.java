package com.example.bookee.weatherinfo.findcity;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class Presenter implements MvpContract.Presenter {
    private MvpContract.Model attachedDataInstance;
    private MvpContract.View attachedView;

    public Presenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);
        attachedDataInstance.bindPresenter(this);
    }



    @Override
    public void bindDataModel(BaseModel model) {
        this.attachedDataInstance= (MvpContract.Model) model;
    }

    @Override
    public void bindView(BaseView view) {
       this.attachedView= (MvpContract.View) view;
    }

    @Override
    public void errorMessage(String s) {
        //todo sta ce se desiti ako je sistem vec uradio onDestroy() i metoda unbindView() je pozvana neposredno pre ove metode?
        attachedView.errorHappened(s);
    }

    @Override
    public void passResultToView(CityForecastInfo body) {
        if(body!=null) {
            attachedView.recieveDataFromPresenter(body);
        } else {

            attachedView.errorHappened("Pogresno ime grada");
        }
    }

    @Override
    public void unbindView() {
        attachedView=null;
    }


    public void getData(String desiredCity) {

//        attachedDataInstance.getData(desiredCity);
        //todo opet o imanovanju: cim pogledas ovaj poziv 100% je jasno sta dovlcis i kako.
        attachedDataInstance.fetchForecastInfoOfCity(desiredCity, new MvpContract.CityForecastCallback() {
            @Override
            public void data(CityForecastInfo cityForecastInfo) {
                attachedView.recieveDataFromPresenter(cityForecastInfo);
            }

            @Override
            public void error(Throwable throwable) {
                attachedView.errorHappened("Pogresno ime grada");
            }
        });
    }
}
