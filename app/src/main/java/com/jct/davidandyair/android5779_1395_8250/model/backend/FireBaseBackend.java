package com.jct.davidandyair.android5779_1395_8250.model.backend;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

import java.util.Calendar;
import java.util.Date;

public class FireBaseBackend implements IBackend {
    FirebaseDatabase database;
    private AsyncTask<Drive, Drive, Drive> asyncTask;

    public interface Action<T>  {
        void onSuccess(T obj);
        void onFailure(Exception exception);
        void onProgress(String status, double percent);
    }

    @Override
    public void askForNewDrive(Drive d , final Action action) {
        asyncTask = new AsyncTask<Drive, Drive, Drive>() {
            @Override
            protected Drive doInBackground(Drive... drives) {
                // Write a message to the database
                database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("drives");

                Date currentDate = Calendar.getInstance().getTime();
                drives[0].setWhenLoadToFIrebase(currentDate);
                String key = myRef.push().getKey();
                drives[0].setKey(key);
                myRef.child(key).setValue(drives[0]).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        action.onSuccess(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        action.onFailure(e);
                    }
                });
                return null;
            }
        };


        asyncTask.execute(d);// using asynctask
    }
}
