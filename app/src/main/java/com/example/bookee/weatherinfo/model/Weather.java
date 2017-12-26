package com.example.bookee.weatherinfo.model;


class Weather {

     private int id;
     private String main;
     private String description;
     private int icon;
      // "weather":[{"id":300,"main":"Drizzle","description":"light intensity drizzle","icon":"09d"}],

    public Weather(int id, String main, String description, int icon) {
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

    public int getIcon() {
        return icon;
    }




}
