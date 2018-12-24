package com.jct.davidandyair.android5779_1395_8250.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.jct.davidandyair.android5779_1395_8250.R;
import com.jct.davidandyair.android5779_1395_8250.model.backend.FactoryBackend;
import com.jct.davidandyair.android5779_1395_8250.model.backend.IBackend;
import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    IBackend backend;
    EditText name;
    EditText phone;
    EditText email;
    AutoCompleteTextView destination;
    AutoCompleteTextView source;
    Button button;
    FloatingActionButton getMyPlace;

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;
    // Define a listener that responds to location updates
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
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

    private void findViews()
    {
        backend = FactoryBackend.getBackend();


        name = findViewById(R.id.nameT);
        phone = findViewById(R.id.phoneT);
        email = findViewById(R.id.emailT);
        destination = findViewById(R.id.destT);
        source = findViewById(R.id.srcT);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                source.setText(getPlace(location));////location.toString());
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //TODO
            }

            public void onProviderEnabled(String provider) {
                //TODO
            }

            public void onProviderDisabled(String provider) {
                //TODO
            }
        };


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the Values
                String _name = name.getText().toString();
                String _phoneNumber = phone.getText().toString();
                String _email = email.getText().toString();
                //Location _destination = destination.getText();
                //Location _source = source.getText();

                //Check integrity
                Boolean formIsComplete = true;
                if(TextUtils.isEmpty(_name))
                {
                    name.setError(getText(R.string.error_name));
                    formIsComplete = false;
                }
                if(TextUtils.isEmpty(_phoneNumber))
                {
                    phone.setError(getText(R.string.error_phone));
                    formIsComplete = false;
                }
                if(TextUtils.isEmpty(_email))
                {
                    email.setError(getText(R.string.error_email));
                    formIsComplete = false;
                }
             /*   if(TextUtils.isEmpty(_destination))
                {
                    source.setError(getText(R.string.error_source));
                    formIsComplete = false;
                }
                if(TextUtils.isEmpty(_source))
                {
                    destination.setError(getText(R.string.error_destination));
                    formIsComplete = false;
                }*/

                if(!formIsComplete)
                    return;

                Drive drive = new Drive();

                drive.setName(_name);
                drive.setPhoneNumber(_phoneNumber);
                drive.seteMailAddress(_email);
                //drive.setDestination(_destination);
                //drive.setSource(_source);
                drive.setStatus(Drive.DriveStatus.AVAILABLE);

                backend.askForNewDrive(drive);
            }
        });

        getMyPlace = findViewById(R.id.getmyplace);
        getMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Check the SDK version and whether the permission is already granted or not.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);

                } else {
                    // Android version is lesser than 6.0 or the permission is already granted.
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                    getMyPlace.setEnabled(false);
                    Toast.makeText(getBaseContext(), R.string.search_location, Toast.LENGTH_LONG).show();
                }
            }
    });
    }

}