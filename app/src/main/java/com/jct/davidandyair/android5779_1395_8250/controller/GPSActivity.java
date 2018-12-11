package com.jct.davidandyair.android5779_1395_8250.controller;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSActivity extends Activity implements View.OnClickListener {


    private TextView locationTextView;
    private Button getLocationButton;
    private Button stopUpdateButton;
private  Button distanceButton;

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;


    // Define a listener that responds to location updates
    LocationListener locationListener;


    private void findViews() {
        locationTextView = (TextView) findViewById(R.id.locationTextView);

        getLocationButton = (Button) findViewById(R.id.getLocationButton);
        getLocationButton.setOnClickListener(this);

        stopUpdateButton = (Button) findViewById(R.id.stopUpdateButton);
        stopUpdateButton.setOnClickListener(this);

        distanceButton = (Button) findViewById(R.id.distanceButton);
        distanceButton.setOnClickListener(this);



        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //    Toast.makeText(getBaseContext(), location.toString(), Toast.LENGTH_LONG).show();
                locationTextView.setText(getPlace(location));////location.toString());

                // Remove the listener you previously added
                //  locationManager.removeUpdates(locationListener);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }


    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);

        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            stopUpdateButton.setEnabled(true);
            getLocationButton.setEnabled(false);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }





    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
              //  String stateName = addresses.get(0).getAddressLine(1);
              //  String countryName = addresses.get(0).getAddressLine(2);
              //  return stateName + "\n" + cityName + "\n" + countryName;
                return cityName;
            }

            return "no place: \n ("+location.getLongitude()+" , "+location.getLatitude()+")";
        }
     catch(
    IOException e)

    {
        e.printStackTrace();
    }
        return "IOException ...";
}
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                stopUpdateButton.setEnabled(true);
                getLocationButton.setEnabled(false);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onClick(View v) {
        if (v == getLocationButton) {
            getLocation(); // Handle clicks for getLocationButton
        }
        if (v == stopUpdateButton) {
            // Remove the listener you previously added
            locationManager.removeUpdates(locationListener);
            stopUpdateButton.setEnabled(false);
            getLocationButton.setEnabled(true);
        }
        if(v==distanceButton)
        {
            startActivity(new Intent(this,DistanceActivity.class));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        findViews();
    }
}
