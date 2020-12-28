package com.example.testret.API;

import com.example.testret.Models.DailyForecasts;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APISettings {

    @GET("onecall")
    Single<DailyForecasts> getTemp(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String appid
    );
}
