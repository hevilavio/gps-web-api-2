package com.api.model;

/**
 * Created by hevilavio on 3/20/15.
 */
public class AreaPosition {

    private int id;

    private int idArea;

    private double latitude;

    private double longitude;

    public int getId() {
        return id;
    }

    public int getIdArea() {
        return idArea;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
