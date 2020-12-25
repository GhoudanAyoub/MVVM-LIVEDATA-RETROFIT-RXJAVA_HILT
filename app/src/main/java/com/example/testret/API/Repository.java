package com.example.testret.API;

import android.annotation.SuppressLint;

import com.example.testret.Models.DailyForecasts;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.http.Query;

public class Repository {
    private APISettings apiSettings;

    @Inject
    public Repository(APISettings apiSettings) {
        this.apiSettings = apiSettings;
    }


    @SuppressLint("CheckResult")
    public Single<DailyForecasts> getTempFromRepo(double lat,
                                                  double lon,
                                                  String appid){
        return apiSettings.getTemp(lat,lon,appid);
    }

}
