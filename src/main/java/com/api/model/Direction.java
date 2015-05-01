package com.api.model;

/**
 * Created by hevilavio on 5/1/15.
 */
public enum Direction {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    NO_ONE("X");

    private final String value;

    private Direction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Direction fromValue(String value) {

        for (Direction direction : values()) {
            if (direction.getValue().equals(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid value. value=[" + value + "]");
    }

    public static void assertLatitudeDirection(String value) {
        if(fromValue(value).equals(NO_ONE)){
            return;
        }

        if(!fromValue(value).equals(NORTH)
                && !fromValue(value).equals(SOUTH)){
            throw new IllegalArgumentException("invalid dirLatitude.[" + value + "]");
        }
    }
    public static void assertLongitudeDirection(String value) {
        if(fromValue(value).equals(NO_ONE)){
            return;
        }

        if(!fromValue(value).equals(WEST)
                && !fromValue(value).equals(EAST)){
            throw new IllegalArgumentException("invalid dirLongitude.[" + value + "]");
        }
    }
}