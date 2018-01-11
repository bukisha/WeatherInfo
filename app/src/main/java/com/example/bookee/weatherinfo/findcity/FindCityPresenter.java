package com.example.bookee.weatherinfo.findcity;


import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.data.BaseModel;
import com.example.bookee.weatherinfo.data.RetrofitWeatherRepository;
import com.example.bookee.weatherinfo.mvp.BaseView;

public class FindCityPresenter implements mvpContract.Presenter {
    private mvpContract.Model attachedDataInstance;
    private mvpContract.View attachedView;

    public FindCityPresenter() {
        RetrofitWeatherRepository repository = new RetrofitWeatherRepository();
        attachedDataInstance = new Model(repository);
        attachedDataInstance.bindPresenter(this);
    }



    @Override
    public void bindDataModel(BaseModel model) {
        this.attachedDataInstance= (mvpContract.Model) model;
    }

    @Override
    public void bindView(BaseView view) {
       this.attachedView= (mvpContract.View) view;
    }

    @Override
    public void errorMessage() {
        attachedView.errorHappened();
    }

    @Override
    public void passResultToView(CityForecastInfo body) {
        attachedView.recieveDataFromPresenter(body);
    }

    @Override
    public void unbindView() {
        attachedView=null;
    }


    public void getData(String desiredCity) {
        attachedDataInstance.getData(desiredCity);
    }
}
