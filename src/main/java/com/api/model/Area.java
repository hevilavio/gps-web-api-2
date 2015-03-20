package com.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @Id
    @GeneratedValue
    private int id;

    private Calendar createdAt;

    private boolean active;

    @OneToMany
    @JoinColumn(name = "idArea")
    private List<AreaPosition> areaPositions;

    public Area() {
        areaPositions = new ArrayList<>();
    }

    // Todas as positions devem estar ordenadas
    public Area(AreaPosition... positions) {
        areaPositions = new ArrayList<>();

        for (AreaPosition position : positions) {
            areaPositions.add(position);
        }
    }

    public List<AreaPosition> getAreaPositions() {
        return areaPositions;
    }
}
