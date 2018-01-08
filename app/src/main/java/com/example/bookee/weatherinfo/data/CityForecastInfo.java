package com.example.bookee.weatherinfo.data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityForecastInfo {



            private Coord coord=null;
            private List<Weather> weather=null;
            private String base;
            private Main main=null;
            private int visibility;
            private Wind wind=null;
            private Cloud clouds=null;
            private int dt;
            private Sys sys=null;
            private int id;
            private String name;
            private int cod;
//=================================================================================================

            public static class  CityForecastInfoBuilder {
                private Coord coord=null;
                private List<Weather> weather=null;
                private String base;
                private Main main=null;
                private int visibility;
                private Wind wind=null;
                private Cloud clouds=null;
                private int dt;
                private Sys sys=null;
                private int id;
                private String name;
                private int cod;

                public CityForecastInfoBuilder setCoord(Coord coord) {
                    this.coord = coord;
                    return this;
                }

                public CityForecastInfoBuilder setWeather(List<Weather> weather) {
                    this.weather = weather;
                    return this;
                }

                public CityForecastInfoBuilder setBase(String base) {
                    this.base = base;
                    return this;
                }

                public CityForecastInfoBuilder setMain(Main main) {
                    this.main = main;
                    return this;
                }

                public CityForecastInfoBuilder setVisibility(int visibility) {
                    this.visibility = visibility;
                    return this;
                }

                public CityForecastInfoBuilder setWind(Wind wind) {
                    this.wind = wind;
                    return this;
                }

                public CityForecastInfoBuilder setClouds(Cloud clouds) {
                    this.clouds = clouds;
                    return this;
                }

                public CityForecastInfoBuilder setDt(int dt) {
                    this.dt = dt;
                    return this;
                }

                public CityForecastInfoBuilder setSys(Sys sys) {
                    this.sys = sys;
                    return this;
                }

                public CityForecastInfoBuilder setId(int id) {
                    this.id = id;
                    return this;
                }

                public CityForecastInfoBuilder setName(String name) {
                    this.name = name;
                    return this;
                }

                public CityForecastInfoBuilder setCod(int cod) {
                    this.cod = cod;
                    return this;
                }

                CityForecastInfo build() {

                    return new CityForecastInfo(this);
                }
            }

    public CityForecastInfo( CityForecastInfoBuilder builder) {
        this.coord = builder.coord;
        this.weather = builder.weather;
        this.base = builder.base;
        this.main = builder.main;
        this.visibility = builder.visibility;
        this.wind =builder. wind;
        this.clouds = builder.clouds;
        this.dt = builder.dt;
        this.sys = builder.sys;
        this.id = builder.id;
        this.name = builder.name;
        this.cod = builder.cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }


    public static class Cloud {

        private int all;

        public Cloud(int all) {
            this.all = all;
        }

        public int getAll() {
            return all;
        }
    }

    public static class Coord {

        private float lon;

        private float lat;


        public Coord(int lon, int lat) {
            this.lon = lon;
            this.lat = lat;
        }


        public float getLon() {
            return lon;
        }

        public float getLat() {
            return lat;
        }
    }

    public static class Main {

        private float temp;
        private int pressure;
        private int humidity;
        @SerializedName("temp_min")
        private float tempMin;
        @SerializedName("temp_max")
        private float tempMax;
        @SerializedName("sea_level")
        private int seaLevel;
        @SerializedName("gnd_level")
        private int gndLevel;


        public Main(float temp, int pressure, int humidity, float tempMin, float tempMax, int seaLevel, int gndLevel) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.tempMin = tempMin;
            this.tempMax = tempMax;
            this.seaLevel = seaLevel;
            this.gndLevel = gndLevel;
        }

        public float getTemp() {
            return temp;
        }

        public int getPressure() {
            return pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public float getTempMin() {
            return tempMin;
        }

        public float getTempMax() {
            return tempMax;
        }

        public int getSeaLevel() {
            return seaLevel;
        }

        public int getGndLevel() {
            return gndLevel;
        }
    }

    public static class Sys {

        private int type;
        private int id;
        private float message;
        private String country;
        private int sunrise;
        private int sunset;

        public Sys(int type, int id, float message, String country, int sunrise, int sunset) {
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }


        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public float getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public int getSunset() {
            return sunset;
        }
    }

    public static class Weather {

         private int id;
         private String main;
         private String description;
         private String icon;
          // "weather":[{"id":300,"main":"Drizzle","description":"light intensity drizzle","icon":"09d"}],

        public Weather(int id, String main, String description,String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public int getId() {
              return id;
          }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }




    }

    public static class Wind {

      private float speed;
      private float deg;


        public Wind(float speed, float deg) {
            this.speed = speed;
            this.deg = deg;
        }

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }
    }
}
