package com.example.testret.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentTemp {

    @SerializedName("dt")
    private double dt;
    @SerializedName("sunrise")
    private double sunrise;
    @SerializedName("sunset")
    private double sunset;
    @SerializedName("temp")
    private float temp;
    @SerializedName("feels_like")
    private float feels_like;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("dew_point")
    private float dew_point;
    @SerializedName("wind_speed")
    private float wind_speed;
    @SerializedName("wind_deg")
    private int wind_deg;
    @SerializedName("weather")
    private List<weather> weather;
    @SerializedName("clouds")
    private int clouds;
    @SerializedName("pop")
    private float pop;
    @SerializedName("uvi")
    private float uvi;

    public CurrentTemp() {
    }

    public float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getDew_point() {
        return dew_point;
    }

    public void setDew_point(float dew_point) {
        this.dew_point = dew_point;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public List<com.example.testret.Models.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.testret.Models.weather> weather) {
        this.weather = weather;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getPop() {
        return pop;
    }

    public void setPop(float pop) {
        this.pop = pop;
    }

    public float getUvi() {
        return uvi;
    }

    public void setUvi(float uvi) {
        this.uvi = uvi;
    }
}
