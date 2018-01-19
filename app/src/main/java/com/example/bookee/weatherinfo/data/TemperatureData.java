package com.example.bookee.weatherinfo.data;


import android.os.Bundle;


  public  class TemperatureData {
        private String name;
        private String temp;
        private String windSpeed;
        private String humidity;

        public TemperatureData(Bundle extras) {
            if (!extras.isEmpty()){
                name = extras.getString("name");
                temp = extras.getString("temp");
                windSpeed = extras.getString("wind");
                humidity = extras.getString("humid");
            }else{
                throw new IllegalArgumentException("It looks like passed Bundle argument is empty!");
            }
        }

      public String getHumidity() {
          return humidity;
      }

      public String getWindSpeed() {
          return String.valueOf(windSpeed);
      }

      public String getTemp() {
          return temp;
      }

      public String getName() {
          return name;
      }
  }

