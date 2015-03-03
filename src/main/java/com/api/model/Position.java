package com.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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

    private double posX;

    private double posY;

    private Calendar date;

    public int getId() {
        return id;
    }

    public int getGpsId() {
        return gpsId;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public Calendar getDate() {
        return date;
    }

    public void setGpsId(int gpsId) {
        this.gpsId = gpsId;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[posX=").append(posX).append(", posY=").append(posY).append("]");

        return sb.toString();

    }
}