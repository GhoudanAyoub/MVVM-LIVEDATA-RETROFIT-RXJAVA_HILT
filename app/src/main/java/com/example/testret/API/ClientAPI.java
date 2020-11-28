package com.example.testret.API;

import com.example.testret.Models.DailyForecasts;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClientAPI {

    private static ClientAPI clientAPI;
    private APISettings apiSettings;

    public ClientAPI() {
            String base_url = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiSettings = retrofit.create(APISettings.class);
    }

    public static ClientAPI getClientAPI() {
        if (clientAPI == null){
            clientAPI= new ClientAPI();
        }
        return clientAPI;
    }

    public Single<DailyForecasts> getTemps(){
        return apiSettings.getTemp();
    }
}
