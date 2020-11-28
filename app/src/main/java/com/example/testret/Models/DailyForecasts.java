package com.example.testret.Models;

import com.example.testret.Models.Temperature;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyForecasts {

    @SerializedName("DailyForecasts")
    private List<Temperature> temperatureList = null;

    public DailyForecasts() { }

    public DailyForecasts(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public List<Temperature> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Temperature> temperatureList) {
        this.temperatureList = temperatureList;
    }
}
