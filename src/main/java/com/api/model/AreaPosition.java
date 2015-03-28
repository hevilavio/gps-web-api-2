package com.api.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by hevilavio on 3/20/15.
 */
@Entity
@Table(name = "gps_area_position")
public class AreaPosition {

    @Id
    @GeneratedValue
    @Column(name = "id_area_position")
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    private double latitude;

    private double longitude;

    public int getId() {
        return id;
    }

    public Area getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @JsonIgnore
    public void setArea(Area area) {
        this.area = area;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("[id=").append(id)
                .append(", latitude=").append(latitude)
                .append(", longitude=").append(longitude);

        return sb.toString();
    }
}
