package com.example.testret.UI.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Adapters.HourlyAdapter;
import com.example.testret.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private PostViewModel postViewModel;
    private RecyclerView recyclerView;
    private HourlyAdapter hourlyAdapter;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private TextView locationTxt, pressureTxt, windTxt, precipitationTxt, CurrentDay, humidityTxt, sunriseTxt, sunsetTxt, FeelLikeTxt, currentWeatherTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        _view();
        hourlyAdapter = new HourlyAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(hourlyAdapter);
        if (isMapEnabled()) {
            _GetCurrentUserPermission();
        } else {
            buildAlertMessageNoGps();
        }
    }

    public boolean isMapEnabled() {
        return ((LocationManager) this.getSystemService(Context.LOCATION_SERVICE)).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.ActivateGpsRequest))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.okay), (dialog, id) -> startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 333));
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void _view() {
        recyclerView = findViewById(R.id.r1);
        locationTxt = findViewById(R.id.locationTxt);
        pressureTxt = findViewById(R.id.pressureTxt);
        windTxt = findViewById(R.id.windTxt);
        precipitationTxt = findViewById(R.id.precTxt);
        humidityTxt = findViewById(R.id.humidityTxt);
        sunriseTxt = findViewById(R.id.sunriseTxt);
        sunsetTxt = findViewById(R.id.sunsetTxt);
        currentWeatherTxt = findViewById(R.id.currentWeatherTxt);
        FeelLikeTxt = findViewById(R.id.FeelLikeTxt);
        CurrentDay = findViewById(R.id.CurrentDay);
        findViewById(R.id.textView2).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Next7Days.class)));
    }

    private void _GetCurrentUserPermission() {
        PermissionListener dialogPermissionListener =
                DialogOnDeniedPermissionListener.Builder
                        .withContext(getApplicationContext())
                        .withTitle("Gps permission")
                        .withMessage("Gps permission is needed to get your location")
                        .withButtonText(android.R.string.ok)
                        .build();
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        _BuildLocationRequest();
                        _BuildLocationCallBack();
                        _init();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        dialogPermissionListener.onPermissionDenied(permissionDeniedResponse);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }

    private void _init() {
        if (fusedLocationProviderClient == null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
        }
    }

    private void _BuildLocationRequest() {
        if (locationRequest == null) {
            locationRequest = new LocationRequest();
            locationRequest.setSmallestDisplacement(10f);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(3000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
    }

    private void _BuildLocationCallBack() {
        if (locationCallback == null) {
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    Location location = locationResult.getLastLocation();
                    if (location != null) {
                        _FindUserLocation();
                    }
                }
            };
        }
    }

    private void _FindUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnFailureListener(Throwable::printStackTrace)
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        String MyLocation = UserUtils.getAddressFromLocation(getApplicationContext(), location);
                        locationTxt.setText(MyLocation);
                        String APP_ID = "ae82f77ec5397be4e9eca95799584087";

                        //Current Temp
                        postViewModel.getCurrent(location.getLatitude(), location.getLongitude(),
                                APP_ID);
                        postViewModel.getCurrentDailyForecastsMutableLiveData().observe(this, currentTemp -> {
                            try {
                                sunsetTxt.setText(new StringBuilder().append(UserUtils.getDateHHMMFromNumber(currentTemp.getSunset())).append(" pm"));
                                sunriseTxt.setText(new StringBuilder().append(UserUtils.getDateHHMMFromNumber(currentTemp.getSunrise())).append(" am"));
                                currentWeatherTxt.setText(new StringBuilder().append(UserUtils.getDegreeToCelsius(currentTemp.getTemp())).append(" °C"));
                                FeelLikeTxt.setText(new StringBuilder().append("Feels like ").append(UserUtils.getDegreeToCelsius(currentTemp.getFeels_like())).append(" °C"));
                                CurrentDay.setText(new StringBuilder().append(UserUtils.getDateDDMMFromNumber(currentTemp.getDt())));
                                pressureTxt.setText(new StringBuilder().append(currentTemp.getPressure()).append(" hpa"));
                                windTxt.setText(new StringBuilder().append(currentTemp.getWind_speed()).append(" km/h"));
                                precipitationTxt.setText(new StringBuilder().append(currentTemp.getPop()).append(" %"));
                                humidityTxt.setText(new StringBuilder().append(currentTemp.getHumidity()).append(" %"));

                                //*********
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        //Hourly Temp
                        postViewModel.getHourly(location.getLatitude(), location.getLongitude(),
                                APP_ID);
                        postViewModel.getHourlyTempMutableLiveData().observe(this, hourlyTemps -> hourlyAdapter.setList(hourlyTemps));

                    }
                });
    }

}
