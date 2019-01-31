package com.jct.davidandyair.android5779_1395_8250.model.entities;

import android.location.Address;

import java.sql.Time;
import java.util.Date;


public class Drive {

    public enum DriveStatus {
        AVAILABLE, IN_PROGRESS, FINISHED
    }

    private DriveStatus status;
    private MyAddress source;
    private MyAddress destination;
    private Date beginning;
    private Date end;
    private String name;
    private String phoneNumber;
    private String eMailAddress;
    private long driverId;
    private String key;

    public Drive()
    {
        this.status = null;
        this.source = null;
        this.destination = null;
        this.beginning = null;
        this.end = null;
        this.name = null;
        this.phoneNumber = null;
        this.eMailAddress = null;
        this.driverId = 0;
    }

    public Drive(DriveStatus status, MyAddress source, MyAddress destination, Time beginning, Time end,
                 String name, String phoneNumber, String eMailAddress, int driverId)
    {
        this.status = status;
        this.source = source;
        this.destination = destination;
        this.beginning = beginning;
        this.end = end;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMailAddress = eMailAddress;
        this.driverId = driverId;
    }
    public boolean compareTo(Drive d){
        if(
                d.status == status&&
                        d.source == source&&
                        d.destination == destination&&
                        d.beginning == beginning&&
                        d.end == end&&
                        d.name.equals(name) &&
                        d.phoneNumber.equals(phoneNumber) &&
                        d.eMailAddress.equals(eMailAddress) &&
                        d.driverId == driverId)
            return true;
        else return false;
    }

    //region sets
    public void setEnd(Date end) {
        this.end = end;
    }
    public void setDestination(MyAddress destination) {
        this.destination = destination;
    }
    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }
    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }
    public void setSource(MyAddress source) {
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
    public void setDriverId(long driverId) { this.driverId = driverId; }
    public void setKey(String key) { this.key = key; }
    //endregion

    //region gets
    public DriveStatus getStatus() {
        return status;
    }
    public Address getDestination() {
        return destination;
    }
    public Address getSource() {
        return source;
    }
    public String getName() {
        return name;
    }
    public String geteMailAddress() {
        return eMailAddress;
    }
    public Date getBeginning() {
        return beginning;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Date getEnd() {
        return end;
    }
    public long getDriverId() {
        return driverId;
    }
    public String getKey() { return key; }
    //endregion

}
