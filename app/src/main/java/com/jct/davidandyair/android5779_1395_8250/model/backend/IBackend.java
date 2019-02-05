package com.jct.davidandyair.android5779_1395_8250.model.backend;

import com.jct.davidandyair.android5779_1395_8250.model.entities.Drive;

public interface IBackend {
    public void askForNewDrive(Drive d , final FireBaseBackend.Action action);
}
