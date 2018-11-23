package com.jct.davidandyair.android5779_1395_8250.model.entities;

import android.location.Location;

import java.sql.Time;

public class Drive {

    public enum DriveStatus {
        AVAILABLE, IN_PROGRESS, FINISHED
    }

    private DriveStatus status;
    private Location source;
    private Location destination;
    private Time beginning;
    private Time end;
    private String name;
    private String phoneNumber;
    private String eMailAddress;

    public Drive(DriveStatus status, Location source, Location destination, Time beginning, Time end,
                 String name, String phoneNumber, String eMailAddress)
    {
        this.status = status;
        this.source = source;
        this.destination = destination;
        this.beginning = beginning;
        this.end = end;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMailAddress = eMailAddress;
    }

    //region sets
    public void setEnd(Time end) {
        this.end = end;
    }
    public void setDestination(Location destination) {
        this.destination = destination;
    }
    public void setBeginning(Time beginning) {
        this.beginning = beginning;
    }
    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }
    public void setSource(Location source) {
        this.source = source;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setStatus(DriveStatus status) {
        this.status = status;
    }
    //endregion
    //region gets
    public DriveStatus getStatus() {
        return status;
    }
    public Location getDestination() {
        return destination;
    }
    public Location getSource() {
        return source;
    }
    public String getName() {
        return name;
    }
    public String geteMailAddress() {
        return eMailAddress;
    }
    public Time getBeginning() {
        return beginning;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Time getEnd() {
        return end;
    }
    //endregion
}
