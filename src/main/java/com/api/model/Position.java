package com.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by hevilavio on 1/17/15.
 */
@Entity
@Table(name = "gps_position")
public class Position {

    @Id
    @GeneratedValue
    private int id;

    private int gpsId;

    private double latitude;

    private double longitude;

    private Calendar date;

    public int getId() {
        return id;
    }

    public int getGpsId() {
        return gpsId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Calendar getDate() {
        return date;
    }

    public void setGpsId(int gpsId) {
        this.gpsId = gpsId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[latitude=").append(latitude).append(", longitude=").append(longitude).append("]");

        return sb.toString();

    }
}