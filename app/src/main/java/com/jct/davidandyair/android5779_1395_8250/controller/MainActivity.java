package com.jct.davidandyair.android5779_1395_8250.controller;

import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


import com.jct.davidandyair.android5779_1395_8250.R;
import com.jct.davidandyair.android5779_1395_8250.model.backend.IBackend;
import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

public class MainActivity extends AppCompatActivity {
    IBackend backend;
    EditText name;
    EditText phone;
    EditText email;
    AutoCompleteTextView destination;
    AutoCompleteTextView source;
    Button button;

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;
    // Define a listener that responds to location updates
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setContentView(R.layout.activity_main);
    }

    private void findViews()
    {
        name = findViewById(R.id.editText);
        phone = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        destination = findViewById(R.id.autoCompleteTextView);
        source = findViewById(R.id.autoCompleteTextView3);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Drive drive = new Drive();

                drive.setName(name.getText().toString());
                drive.setPhoneNumber(phone.getText().toString());
                drive.seteMailAddress(email.getText().toString());
                //drive.setDestination(destination.getText().toString());
                //drive.setDestination(source.getText().toString());
                drive.setStatus(Drive.DriveStatus.AVAILABLE);

                backend.askForNewDrive(drive);
            }
        });
    }
}