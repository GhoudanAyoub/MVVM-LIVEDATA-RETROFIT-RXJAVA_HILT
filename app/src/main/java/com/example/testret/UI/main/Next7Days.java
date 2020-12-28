package com.example.testret.UI.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testret.Adapters.PostAdapter;
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

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.hilt.android.AndroidEntryPoint;

@SuppressLint("NonConstantResourceId")
@AndroidEntryPoint
public class Next7Days extends AppCompatActivity {

    @OnClick(R.id.floatingActionButton2)
    public void BackToMain() {
        super.onBackPressed();
    }


    private RecyclerView hourlyRecycle;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PostViewModel postViewModel;
    private PostAdapter postAdapter;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private TextView DailyLocationTxt;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next7_days);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        _View();
        postAdapter = new PostAdapter(getApplicationContext());
        hourlyRecycle.setLayoutManager(new LinearLayoutManager(this));
        hourlyRecycle.setAdapter(postAdapter);
        _GetCurrentUserPermission();
    }

    private void _View() {
        ButterKnife.bind(this, findViewById(android.R.id.content));
        progressDialog = new ProgressDialog(Next7Days.this);
        progressDialog.setMessage("Checking Your Current Weather....");
        hourlyRecycle = findViewById(R.id.hourlyRecycle);
        DailyLocationTxt = findViewById(R.id.DailyLocationTxt);
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
                        try {
                            progressDialog.show();
                        } catch (Exception ignored) {
                        }
                        String MyLocation = UserUtils.getAddressFromLocation(getApplicationContext(), location);
                        DailyLocationTxt.setText(MyLocation);
                        String APP_ID = "ae82f77ec5397be4e9eca95799584087";
                        //Daily Temp
                        postViewModel.getTempS(location.getLatitude(), location.getLongitude(),
                                APP_ID);
                        postViewModel.getLiveData().observe(this, temperatures -> {
                            postAdapter.setList(temperatures);
                            progressDialog.dismiss();
                        });
                    }
                });
    }
}