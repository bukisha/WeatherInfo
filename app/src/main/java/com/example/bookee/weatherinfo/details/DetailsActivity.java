package com.example.bookee.weatherinfo.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.example.bookee.weatherinfo.search.SearchActivity;


public class DetailsActivity extends AppCompatActivity implements MvpContract.View {
    private static final int    REQUEST_NEW_CITY = 2;
    public static final  String NEW_TEMP_KEY     = "newTemp";
    public static final String INITIAL_DATA_KEY ="initialData" ;
    public static final String SELECTED_CITY_KEY = "selectedCityTemp";
    public static final String ERROR_NO_NEW_DATA ="nisu primljeni novi podaci" ;

    private   TemperatureData temperatureToDisplay;

    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private MvpContract.Presenter weatherPresenter;

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


        weatherPresenter = new Presenter(this);

            Intent intent = getIntent();
            Bundle initBundle = intent.getExtras();

            if(initBundle.containsKey(INITIAL_DATA_KEY)) {
                temperatureToDisplay = initBundle.getParcelable(INITIAL_DATA_KEY);
            } else if(initBundle.containsKey(SELECTED_CITY_KEY)) {
                temperatureToDisplay =initBundle.getParcelable(SELECTED_CITY_KEY);
            }

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherPresenter.actionSomethingIsClicked();
            }
        });
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
        weatherPresenter.displayNewData(temperatureToDisplay);
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
        Log.i("DEBUG","onAvtivityRes");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_CITY) {
            Bundle newCityWeather = data.getExtras();
            TemperatureData newTemperature= null;
            if (newCityWeather != null) {
                newTemperature = newCityWeather.getParcelable(NEW_TEMP_KEY);
            }
           temperatureToDisplay =newTemperature;
        } else if(resultCode==RESULT_CANCELED) {
                onResume();
            } else {
            errorHappened(ERROR_NO_NEW_DATA);
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
