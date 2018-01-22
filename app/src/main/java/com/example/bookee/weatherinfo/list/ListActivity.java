package com.example.bookee.weatherinfo.list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.bookee.weatherinfo.R;
import com.example.bookee.weatherinfo.data.TemperatureData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListActivity extends android.app.ListActivity implements MvpContract.View {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(android.R.id.list);
        displaySearchedCities();
    }

    private void displaySearchedCities() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String cityList=sharedPreferences.getString(String.valueOf(R.string.globalCityListName),"");
        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<TemperatureData>>(){}.getType();
        ArrayList<TemperatureData> listOfCityTemps=gson.fromJson(cityList,type);

        ArrayList<String> listOfCityNames=new ArrayList<>();
        for(int i=0;i<listOfCityTemps.size();i++) {

            listOfCityNames.add(listOfCityTemps.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfCityNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void listItemClicked() {
        //TODO when i am done with my lunch and smoke
    }
}