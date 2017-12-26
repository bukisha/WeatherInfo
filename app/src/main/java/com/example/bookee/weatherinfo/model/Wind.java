package com.example.bookee.weatherinfo.model;

/**
 * Created by WIN10 on 12/26/2017.
 */

public class Wind {

  private float speed;
  private int deg;


    public Wind(float speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }
}
