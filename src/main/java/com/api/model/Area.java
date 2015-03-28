package com.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
@Entity
@Table(name = "gps_area")
public class Area {

    public Area() {
        positions = new ArrayList<>();
        createdAt = Calendar.getInstance();
        active = true;
    }

    // Todas as positions devem estar ordenadas
    public Area(AreaPosition... positions) {
        this.positions = new ArrayList<>();

        for (AreaPosition position : positions) {
            this.positions.add(position);
        }
    }

    //

    @Id
    @GeneratedValue
    @Column(name = "id_area")
    private int id;

    private Calendar createdAt;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<AreaPosition> positions;

    public int getId() {
        return id;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public List<AreaPosition> getPositions() {
        return positions;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPositions(List<AreaPosition> positions) {
        this.positions = positions;
    }
}
