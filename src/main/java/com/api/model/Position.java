package com.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.beans.Transient;
import java.util.Calendar;

/**
 * Created by hevilavio on 1/17/15.
 */
@Entity
@Table(name = "gps_device_position")
public class Position {

    @Id
    @GeneratedValue
    private int id;

    private int gpsId;

    private double latitude;

    private double longitude;

    private String dirLatitude; // N or S

    private String dirLongitude;// W or E

    private Calendar date;

    private Position() {
    }

    private Position(int id, int gpsId, double latitude, double longitude, String dirLatitude, String dirLongitude) {
        this(gpsId, latitude, longitude, dirLatitude, dirLongitude);
        this.id = id;
    }
    public Position(int gpsId, double latitude, double longitude, String dirLatitude, String dirLongitude) {

        Direction.assertLatitudeDirection(dirLatitude);
        Direction.assertLongitudeDirection(dirLongitude);

        this.gpsId = gpsId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dirLatitude = dirLatitude;
        this.dirLongitude = dirLongitude;
        this.date = Calendar.getInstance();
    }

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

    public String getDirLatitude() {
        return dirLatitude;
    }

    public String getDirLongitude() {
        return dirLongitude;
    }

    public Calendar getDate() {
        return date;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setGpsId(int gpsId) {
        this.gpsId = gpsId;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setDirLatitude(String dirLatitude) {
        this.dirLatitude = dirLatitude;
    }

    private void setDirLongitude(String dirLongitude) {
        this.dirLongitude = dirLongitude;
    }

    private void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[latitude=").append(latitude)
                .append(", longitude=").append(longitude)
                .append(", dirLatitude=").append(dirLatitude)
                .append(", dirLongitude=").append(dirLongitude)
                .append("]");

        return sb.toString();
    }

    /**
     * Na representacao do Google Maps, latitude e longitude podem ter
     * valores negativos, de acordo com a direcao.
     *
     * */
    @Transient
    public Position toGoogleMapsPosition(){

        boolean latitudeShoudBeNegative = Direction.fromValue(dirLatitude).equals(Direction.SOUTH);
        boolean longitudeShoudBeNegative = Direction.fromValue(dirLongitude).equals(Direction.WEST);

        return new Position(id,
                gpsId,
                latitudeShoudBeNegative ? latitude * -1 : latitude,
                longitudeShoudBeNegative ? longitude * -1 : longitude,
                Direction.NO_ONE.getValue(),
                Direction.NO_ONE.getValue());
    }
}