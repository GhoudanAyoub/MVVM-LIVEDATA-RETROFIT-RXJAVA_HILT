package com.example.testret.UI.main;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testret.API.Repository;
import com.example.testret.Models.CurrentTemp;
import com.example.testret.Models.HourlyTemp;
import com.example.testret.Models.Temperature;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class PostViewModel extends ViewModel {
    private final Repository repository;

    @ViewModelInject
    public PostViewModel(Repository repository) {
        this.repository = repository;
    }

    private MutableLiveData<List<Temperature>> liveData = new MutableLiveData<>();
    private MutableLiveData<CurrentTemp> dailyForecastsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<HourlyTemp>> hourlyTempMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<HourlyTemp>> getHourlyTempMutableLiveData() {
        if (hourlyTempMutableLiveData == null)
            hourlyTempMutableLiveData = new MutableLiveData<>();
        return hourlyTempMutableLiveData;
    }

    public LiveData<CurrentTemp> getCurrentDailyForecastsMutableLiveData() {
        if (dailyForecastsMutableLiveData == null)
            dailyForecastsMutableLiveData = new MutableLiveData<>();
        return dailyForecastsMutableLiveData;
    }

    public LiveData<List<Temperature>> getLiveData() {
        if (liveData == null)
            liveData = new MutableLiveData<>();
        return liveData;
    }

    public void getTempS(double lat, double lon, String appid) {
        repository.getTempFromRepo(lat, lon, appid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts ->
                                liveData.setValue(dailyForecasts.getTemperatureList())
                        , Throwable::printStackTrace);
    }

    public void getCurrent(double lat, double lon, String appid) {
        repository.getTempFromRepo(lat, lon, appid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts ->
                                dailyForecastsMutableLiveData.setValue(dailyForecasts.getCurrentTempList())
                        , Throwable::printStackTrace);

    }

    public void getHourly(double lat, double lon, String appid) {
        repository.getTempFromRepo(lat, lon, appid)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts ->
                                hourlyTempMutableLiveData.setValue(dailyForecasts.getHourlyTempList())
                        , Throwable::printStackTrace);

    }

}
