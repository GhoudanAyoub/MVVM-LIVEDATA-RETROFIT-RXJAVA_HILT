package com.example.testret.Models;

import com.example.testret.Models.Temperature;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyForecasts {

    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("timezone_offset")
    private double timezone_offset;
    @SerializedName("daily")
    private List<Temperature> temperatureList = null;

    public DailyForecasts() { }

    public DailyForecasts(double lat, double lng, String timezone, double timezone_offset, List<Temperature> temperatureList) {
        this.lat = lat;
        this.lng = lng;
        this.timezone = timezone;
        this.timezone_offset = timezone_offset;
        this.temperatureList = temperatureList;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(double timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
