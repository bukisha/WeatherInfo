package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.search.SearchActivity;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final int REQUEST_NEW_CITY = 2;

    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private MvpContract.Presenter weatherPresenter;
    private boolean newCityData = false;//todo zasto imas ovaj flag ovde?

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("DEBUG", "onCREATE");
        setContentView(R.layout.details_activity);

        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);

        weatherPresenter = new Presenter();

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.ActionSomethingIsClicked();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DEBUG", "onPAUSE");
        weatherPresenter.unbindView();
    }
    @Override
    protected void onResume() {
        super.onResume();
        weatherPresenter.bindView(this);
        Intent intent=getIntent();
        Bundle initialWeatherData=intent.getExtras();
        Log.i("DEBUG", "onRESUME");//todo u glavnom se ovi method logovi stavljajuj da budu prva linija metode. jer ako dodje do crash pre ove linije, onda ne znas da je app ikada usla u onResume()
        if (!newCityData) {
            weatherPresenter.displayNewData(initialWeatherData);
        }
    }

    private void hideInfoFields(boolean hide) {//todo unused
        if(hide) {
            city.setVisibility(View.INVISIBLE);
            temperature.setVisibility(View.INVISIBLE);
            windSpeed.setVisibility(View.INVISIBLE);
            humidity.setVisibility(View.INVISIBLE);
        } else {
            city.setVisibility(View.VISIBLE);
            temperature.setVisibility(View.VISIBLE);
            windSpeed.setVisibility(View.VISIBLE);
            humidity.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
    @Override
    public void startNewActivity() {//todo kasnije cemo pricati i o ovome. Realno, View ne bi trebalo da zna niti startuje activity. To je samo glupi View. Zamisli TextView koji startuje activity. Prosto nema smisla!!! Ali za sada teraj ovako.
        Intent newCityIntent = new Intent(this, SearchActivity.class);
        startActivityForResult(newCityIntent, REQUEST_NEW_CITY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_CITY) {
            Bundle newCityWeather = data.getExtras();
            newCityData = true;
            weatherPresenter.bindView(this);
            weatherPresenter.displayNewData(newCityWeather);
        } else {
            errorHappened("nisu primljeni novi podaci");
        }
    }
    @Override
    public void updateWithNewData(String name, String temp, String wind, String humid) {
        city.setText(name);
        temperature.setText(temp);
        windSpeed.setText(wind);
        humidity.setText(humid);
    }
}
