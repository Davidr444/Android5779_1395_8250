package com.jct.davidandyair.android5779_1395_8250.model.backend;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

public class FireBaseBackend implements IBackend {
    FirebaseDatabase database;

    @Override
    public void askForNewDrive(Drive d) {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("drives");

        myRef.push().setValue(d);
    }
}
