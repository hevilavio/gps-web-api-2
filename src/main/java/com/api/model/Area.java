package com.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */
public class Area {

    private List<Position> positionList;

    public Area() {
        positionList = new ArrayList<>();
    }

    // Todas as positions devem estar ordenadas
    public Area(Position... positions) {
        positionList = new ArrayList<>();

        for (Position position : positions) {
            positionList.add(position);
        }
    }

    public boolean add(Position position) {

        if (position == null) {
            throw new IllegalArgumentException();
        }

        return positionList.add(position);
    }

    public List<Position> getPositionList() {
        return positionList;
    }
}
