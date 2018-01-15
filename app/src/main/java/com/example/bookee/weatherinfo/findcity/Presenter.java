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
       // attachedDataInstance.bindPresenter(this);
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
        attachedView.errorHappened(s);
    }

   @Override
   public void getData(String desiredCity) {
        attachedDataInstance.fetchData(desiredCity, new MvpContract.FetchNewDataCallback() {


            @Override
            public void fetchNewData(String cityName, CityForecastInfo info) {
                attachedView.recieveDataFromPresenter(info);
            }

            @Override
            public void error(String message) {
                attachedView.errorHappened(message);
            }
        });

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


    public void fetchData(String desiredCity,MvpContract.FetchNewDataCallback callback) {

        attachedDataInstance.fetchData(desiredCity,callback);
    }
}
