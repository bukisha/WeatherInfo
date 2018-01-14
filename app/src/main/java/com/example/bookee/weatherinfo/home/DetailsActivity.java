package com.example.bookee.weatherinfo.home;//todo Zasto ovde u "details" domenu koristis "home" pezenter? Pretpostavljam greska

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.CityForecastInfo;
import com.example.bookee.weatherinfo.findcity.SearchActivity;
import com.example.bookee.weatherinfo.mvp.BasePresenter;


public class DetailsActivity extends AppCompatActivity implements MvpContract.View {

    public static final double CELSIOUS_FAHRENHEIT_DIFFERENCE = 273.15;
    private TextView city;
    private TextView temperature;
    private TextView windSpeed;
    private TextView humidity;
    private FloatingActionButton floatingActionButton;

    private Presenter weatherPresenter;
    private Bundle    extras;//todo nema potrebe za field ako se ovaj extras koristi samo lokalno


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        city = findViewById(R.id.current_city);
        temperature = findViewById(R.id.current_temp);
        windSpeed = findViewById(R.id.wind_info);
        humidity = findViewById(R.id.humidity_info);

        weatherPresenter = new Presenter();
        weatherPresenter.bindView(this);

        floatingActionButton = findViewById(R.id.floatingActionButton);
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
        weatherPresenter.unbindView();


    }


    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = null;
        if (getIntent().hasExtra("name")){//todo ako si ovde ispitivao da li postoji "name" bundle onda ga u if body i izvuci
//            extras = getIntent().getBundleExtra("name");//todo ovako
            extras = getIntent().getExtras();
        }

        if (extras != null) {
            Log.i("DEBUG", "pre vadjenja iz extras");
            weatherPresenter.displayNewData(extras);
        } else {
            Log.i("DEBUG", "NE VADIM NSITA IZ extras");
            weatherPresenter.getData();
        }

        //todo ali ovo moze i krace da bude. na primer: pogledaj zakomentarisanu metodu ispod.
        //todo manje linija koda = manje prostora za gresku i kod citljiviji
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (getIntent().getBundleExtra("name") != null){
//            Log.i("DEBUG", "pre vadjenja iz extras");
//            weatherPresenter.displayNewData(getIntent().getBundleExtra("name"));
//        } else {
//            Log.i("DEBUG", "NE VADIM NSITA IZ extras");
//            weatherPresenter.getData();
//        }
//    }

    @Override
    public void errorHappened(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


    @Override
    public void startNewActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    @Override
    public void updateWithNewData(String name, String temp, String wind, String humid) {
        city.setText(name);
        temperature.setText(temp);
        windSpeed.setText(wind);
        humidity.setText(humid);
    }

    @Override
    public void bindPresenter(BasePresenter presenter) {
        this.weatherPresenter = (Presenter) presenter;
    }

    @Override
    public void recieveDataFromPresenter(CityForecastInfo info) {
//        int tempToDisplay;//todo izbegavaj nepotrebne lokalne promenljive
        city.setText(info.getName());
//        tempToDisplay = (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
        int tempToDisplay = (int) (info.getMain().getTemp() - CELSIOUS_FAHRENHEIT_DIFFERENCE);
        temperature.setText(String.valueOf(tempToDisplay));
        windSpeed.setText(String.valueOf((int) info.getWind().getSpeed()));
        humidity.setText(String.valueOf(info.getMain().getHumidity()));
    }
}
