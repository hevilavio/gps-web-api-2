package com.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
public class Area {

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
