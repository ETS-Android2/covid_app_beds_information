 package com.example.covid_beds_information_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    public static String city = "";
    Button btnHomeHospitalRegistration,btnHomeBeds,btnHomeHospitalUpdation;
    FirebaseAuth firebaseAuth;
    GoogleApiClient gac;
    Location loc;
    public static TextView tvHomeCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHomeBeds = findViewById(R.id.btnHomeBeds);
        btnHomeHospitalRegistration = findViewById(R.id.btnHomeHospitalRegistration);
        btnHomeHospitalUpdation = findViewById(R.id.btnHomeHospitalUpdation);

        firebaseAuth = FirebaseAuth.getInstance();
        tvHomeCity = findViewById(R.id.tvHomeCity);
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        gac = builder.build();

        String a1 = "https://data.covid19india.org/state_district_wise.json";
        Task t1 =new Task();
        t1.execute(a1);

        btnHomeBeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PatientCorner.class);
                startActivity(intent);
            }
        });
        btnHomeHospitalUpdation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HospitalUpdation.class);
                startActivity(intent);
            }
        });
        btnHomeHospitalRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HospitalRegistration.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.menubar == item.getItemId()){
            firebaseAuth.signOut();
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnected(@NonNull Bundle bundle) {
        int res1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int res2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if((res1 == PackageManager.PERMISSION_GRANTED) && (res2 == PackageManager.PERMISSION_GRANTED)){
                task();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            task();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull @NotNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(gac!=null)
            gac.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(gac != null)
            gac.disconnect();
    }
    public void task(){
        loc = LocationServices.FusedLocationApi.getLastLocation(gac);
        if(loc!=null){
            double lat = loc.getLatitude();
            double lon = loc.getLongitude();


            Geocoder geocoder = new Geocoder(HomeActivity.this, Locale.ENGLISH);
            try{
                List<Address> addressList = geocoder.getFromLocation(lat,lon,1);
                Address address = addressList.get(0);
                String msg = address.getSubAdminArea();
                city = msg;
            } catch(IOException e){
                Toast.makeText(this, "issue "+e, Toast.LENGTH_SHORT).show();
            }


        }
    }

}