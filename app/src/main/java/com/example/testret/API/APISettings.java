package com.example.testret.API;
import com.example.testret.Models.DailyForecasts;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APISettings {

    @GET("2606235?apikey=gJAkBa36Nt1cYt5LesFNER34ekTp5bUR")
    Single<DailyForecasts> getTemp();
}
