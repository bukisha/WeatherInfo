package com.example.bookee.weatherinfo.findcity;

import com.example.bookee.weatherinfo.*;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.home.DetailsActivity;
import com.example.bookee.weatherinfo.mvp.BasePresenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity implements MvpContract.View {

    private Button getForecast;
    private EditText cityName;
    private Presenter presenter;

    private String name;
    private double temperature;
    private double windSpeed;
    private int humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_city);
        getForecast = findViewById(R.id.get_forecast_button);
        cityName = findViewById(R.id.desired_city_name);
        presenter = new Presenter();
        presenter.bindView(this);

        cityName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    return true;

            }
                return false;
        }
        });

        getForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desiredCity;
                desiredCity = cityName.getText().toString();
                presenter.getData(desiredCity);
            }
        });

    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.presenter = (Presenter) presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }

    @Override
    public void recieveDataFromPresenter(CityForecastInfo info) {
        name = info.getName();
        temperature = info.getMain().getTemp();
        windSpeed = info.getWind().getSpeed();
        humidity = info.getMain().getHumidity();
        startNewActivity();
    }

    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewActivity() {
        Intent i = new Intent(this, DetailsActivity.class);
        Bundle extraData = new Bundle();
        extraData.putString("name", name);
        extraData.putDouble("temp", temperature);
        extraData.putDouble("wind", windSpeed);
        extraData.putInt("humid", humidity);
        i.putExtras(extraData);


        startActivity(i);
    }


}


