package com.example.testret.UI.main;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testret.API.Repository;
import com.example.testret.Models.Temperature;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private Repository repository;

    @ViewModelInject
    public PostViewModel(Repository repository) {
        this.repository = repository;
    }

    public   MutableLiveData<List<Temperature>> liveData= new MutableLiveData<>() ;

    public LiveData<List<Temperature>> getLiveData(){
        if (liveData==null)
            liveData = new MutableLiveData<>();
        return liveData;
    }

    @SuppressLint("CheckResult")
    public void getTempS(){
        repository.getTempFromRepo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyForecasts->
                        liveData.setValue(dailyForecasts.getTemperatureList())
                ,Throwable::printStackTrace);
    }
}
