package com.example.testret.UI.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testret.API.ClientAPI;
import com.example.testret.Models.DailyForecasts;
import com.example.testret.Models.Temperature;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    public    MutableLiveData<List<Temperature>> liveData= new MutableLiveData<>() ;
    CompositeDisposable disposable= new CompositeDisposable();

    public LiveData<List<Temperature>> getLiveData(){
        if (liveData==null)
            liveData = new MutableLiveData<>();
        return liveData;
    }
    public void getTempS(){

        Single<DailyForecasts> dailyForecastsSingle = ClientAPI.getClientAPI().getTemps()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        dailyForecastsSingle.subscribe(new SingleObserver<DailyForecasts>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull DailyForecasts dailyForecasts) {
                    liveData.setValue(dailyForecasts.getTemperatureList());
                    Log.e("Temps"," "+dailyForecasts.getTemperatureList().get(1).getDate());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Temps",e.getMessage());
            }
        });

    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        disposable.clear();
    }
}
