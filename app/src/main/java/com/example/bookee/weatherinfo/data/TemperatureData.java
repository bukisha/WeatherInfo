package com.example.bookee.weatherinfo.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TemperatureData implements Parcelable {
    public static final Parcelable.Creator<TemperatureData> creator = new Creator<TemperatureData>() {
        @Override
        public TemperatureData createFromParcel(Parcel parcel) {
            return new TemperatureData(parcel);
        }
        @Override
        public TemperatureData[] newArray(int i) {
            return new TemperatureData[i];
        }
    };

    private String name;
    private String temp;
    private String windSpeed;
    private String humidity;

    public void setName(String name) {
        this.name = name;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public TemperatureData(String name,String temperature,String windSpeed,String humidity) {
            this.name=name;
            this.temp=temperature;
            this.windSpeed=windSpeed;
            this.humidity=humidity;
    }

    public static final Creator<TemperatureData> CREATOR = new Creator<TemperatureData>() {
        @Override
        public TemperatureData createFromParcel(Parcel in) {
            return new TemperatureData(in);
        }

        @Override
        public TemperatureData[] newArray(int size) {
            return new TemperatureData[size];
        }
    };

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

    //=============================parcelable part ===================================
    protected TemperatureData(Parcel in) {
        name = in.readString();
        temp = in.readString();
        windSpeed = in.readString();
        humidity = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(temp);
        parcel.writeString(windSpeed);
        parcel.writeString(humidity);
    }
}

