package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.search.SearchActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final int REQUEST_NEW_CITY = 2;

    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private MvpContract.Presenter weatherPresenter;
    private boolean newCityData;//todo zasto imas ovaj flag ovde
    //todo RESPONSE da bih svaki put u onResume mogao da proverim da li startujem app ili u DetailsActivity dolazim iz searcha ili iz listActivity sa novim podacima

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuToListItem: {
                weatherPresenter.menuAction(this,0,new MvpContract.PresenterActivityCallback() {
                    @Override
                    public void openActivity(Intent i) {
                    startActivity(i);
                    //TODO proveri dal zadovoljava spec
                    finish();
                    }
                });
            return true;
            }
        default: return super.onOptionsItemSelected(item);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("DEBUG", "onCREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
       android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);

        weatherPresenter = new Presenter();

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.actionSomethingIsClicked();
            }
        });
        newCityData=false;
    }
    @Override
    protected void onPause() {
        Log.i("DEBUG", "onPAUSE");
        super.onPause();
        weatherPresenter.unbindView();
    }
    @Override
    protected void onResume() {
        Log.i("DEBUG", "onRESUME");
        super.onResume();
        weatherPresenter.bindView(this);

        if (!newCityData) {
            Intent intent=getIntent();
            Bundle initBundle=intent.getExtras();
            TemperatureData initTemperature= null;
            if (initBundle != null) {
               if(initBundle.containsKey("initialData")) {
                initTemperature = initBundle.getParcelable("initialData");
            } else {
               if(initBundle.containsKey("selectedCityTemp")) {
                   initTemperature = initBundle.getParcelable("selectedCityTemp");}
               }
            }
            weatherPresenter.displayNewData(initTemperature);
        }
    }
    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
    @Override
    public void startNewActivity() {
        Intent newCityIntent = new Intent(this, SearchActivity.class);
        startActivityForResult(newCityIntent, REQUEST_NEW_CITY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_CITY) {
            Bundle newCityWeather = data.getExtras();
            newCityData = true;
            TemperatureData newTemperature= null;
            if (newCityWeather != null) {
                newTemperature = newCityWeather.getParcelable("newTemp");
            }
            weatherPresenter.bindView(this);
            weatherPresenter.displayNewData(newTemperature);
        } else {
            if(resultCode==RESULT_CANCELED) {
                finish();
            } else {
                errorHappened("nisu primljeni novi podaci");
            }
        }
    }

    @Override
    public void updateWithNewData(TemperatureData temperatureData) {
        city.setText(temperatureData.getName());
        temperature.setText(temperatureData.getTemp());
        windSpeed.setText(temperatureData.getWindSpeed());
        humidity.setText(temperatureData.getHumidity());
    }
}
