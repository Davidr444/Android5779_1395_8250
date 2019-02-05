package com.jct.davidandyair.android5779_1395_8250.model.backend;

import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

import java.util.Calendar;
import java.util.Date;

public class FireBaseBackend implements IBackend {
    FirebaseDatabase database;
    private AsyncTask<Drive, Drive, Drive> asyncTask = new AsyncTask<Drive, Drive, Drive>() {
        @Override
        protected Drive doInBackground(Drive... drives) {
            // Write a message to the database
            database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("drives");

            Date currentDate = Calendar.getInstance().getTime();
            drives[0].setWhenLoadToFIrebase(currentDate);
            String key = myRef.push().getKey();
            drives[0].setKey(key);
            myRef.child(key).setValue(drives[0]);
            return null;
        }
    };

    @Override
    public void askForNewDrive(Drive d) {
        asyncTask.execute(d);// using asynctask
    }
}
