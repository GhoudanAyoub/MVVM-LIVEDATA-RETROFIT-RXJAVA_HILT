package com.example.testret.UI.main;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testret.API.Repository;
import com.example.testret.Models.DailyForecasts;
import com.example.testret.Models.Temperature;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class PostViewModel extends ViewModel {
    private Repository repository;

    @ViewModelInject
    public PostViewModel(Repository repository) {
        this.repository = repository;
    }

    private MutableLiveData<List<Temperature>> liveData = new MutableLiveData<>();
    private MutableLiveData<DailyForecasts> dailyForecastsMutableLiveData = new MutableLiveData<>();

    public LiveData<DailyForecasts> getDailyForecastsMutableLiveData() {
        if (dailyForecastsMutableLiveData == null)
            dailyForecastsMutableLiveData = new MutableLiveData<>();
        return dailyForecastsMutableLiveData;
    }

    public LiveData<List<Temperature>> getLiveData() {
        if (liveData == null)
            liveData = new MutableLiveData<>();
        return liveData;
    }

    public void getDaily(double lat,
                         double lon,
                         String exclude,
                         String appid,
                         String units) {
        repository.getTempFromRepo(lat, lon, exclude, appid, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts ->
                                dailyForecastsMutableLiveData.setValue(dailyForecasts)
                        , Throwable::printStackTrace);

    }

    public void getTempS(double lat,
                         double lon,
                         String exclude,
                         String appid,
                         String units) {
        repository.getTempFromRepo(lat, lon, exclude, appid, units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts ->
                                liveData.setValue(dailyForecasts.getTemperatureList())
                        , Throwable::printStackTrace);
    }
}
