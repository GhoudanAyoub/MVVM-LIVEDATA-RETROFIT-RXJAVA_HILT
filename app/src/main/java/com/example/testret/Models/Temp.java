package com.example.testret.Models;

import com.google.gson.annotations.SerializedName;

public class Temp {
    @SerializedName("day")
    private String day;
    @SerializedName("min")
    private String min;
    @SerializedName("max")
    private String max;
    @SerializedName("night")
    private String night;
    @SerializedName("eve")
    private String eve;
    @SerializedName("morn")
    private String morn;

    public Temp() {
    }

    public Temp(String day, String min, String max, String night, String eve, String morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }
}
