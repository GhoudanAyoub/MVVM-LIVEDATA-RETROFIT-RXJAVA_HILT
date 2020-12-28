package com.example.testret.Models;

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
    @SerializedName("current")
    private CurrentTemp currentTempList = null;
    @SerializedName("hourly")
    private List<HourlyTemp> hourlyTempList = null;

    public DailyForecasts() {
    }

    public List<HourlyTemp> getHourlyTempList() {
        return hourlyTempList;
    }

    public void setHourlyTempList(List<HourlyTemp> hourlyTempList) {
        this.hourlyTempList = hourlyTempList;
    }

    public CurrentTemp getCurrentTempList() {
        return currentTempList;
    }

    public void setCurrentTempList(CurrentTemp currentTempList) {
        this.currentTempList = currentTempList;
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
