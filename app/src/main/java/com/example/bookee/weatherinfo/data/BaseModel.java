package com.example.bookee.weatherinfo.data;


import com.example.bookee.weatherinfo.mvp.BasePresenter;
//base interface which every repository should use for comunication with presenter comment more for myself than for anyone else :D

public interface BaseModel {

        public void bindPresenter(BasePresenter presenter);

}
