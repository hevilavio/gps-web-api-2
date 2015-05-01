package com.api.model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PositionTest {


    @Test
    public void testInCanada() {

        Position position = new Position(1010, 10, 20, Direction.NORTH.getValue(), Direction.WEST.getValue());

        Position gMapsPosition = position.toGoogleMapsPosition();

        assertNotNull(gMapsPosition);
        assertEquals(position.getLatitude(), gMapsPosition.getLatitude(), 0.0005);
        assertEquals(position.getLongitude() * -1, gMapsPosition.getLongitude(), 0.0005);

        assertNoOne(gMapsPosition);
    }

    @Test
    public void testInRussia() {

        Position position = new Position(1010, 10, 20, Direction.NORTH.getValue(), Direction.EAST.getValue());

        Position gMapsPosition = position.toGoogleMapsPosition();

        assertNotNull(gMapsPosition);
        assertEquals(position.getLatitude(), gMapsPosition.getLatitude(), 0.0005);
        assertEquals(position.getLongitude(), gMapsPosition.getLongitude(), 0.0005);

        assertNoOne(gMapsPosition);
    }

    @Test
    public void testInAutralia() {

        Position position = new Position(1010, 10, 20, Direction.SOUTH.getValue(), Direction.EAST.getValue());

        Position gMapsPosition = position.toGoogleMapsPosition();

        assertNotNull(gMapsPosition);
        assertEquals(position.getLatitude() * -1, gMapsPosition.getLatitude(), 0.0005);
        assertEquals(position.getLongitude(), gMapsPosition.getLongitude(), 0.0005);

        assertNoOne(gMapsPosition);
    }


    @Test
    public void testInBrazil() {

        Position position = new Position(1010, 10, 20, Direction.SOUTH.getValue(), Direction.WEST.getValue());

        Position gMapsPosition = position.toGoogleMapsPosition();

        assertNotNull(gMapsPosition);
        assertEquals(position.getLatitude() * -1, gMapsPosition.getLatitude(), 0.0005);
        assertEquals(position.getLongitude() * -1, gMapsPosition.getLongitude(), 0.0005);

        assertNoOne(gMapsPosition);
    }

    private void assertNoOne(Position gMapsPosition) {
        assertEquals(Direction.NO_ONE.getValue(), gMapsPosition.getDirLatitude());
        assertEquals(Direction.NO_ONE.getValue(), gMapsPosition.getDirLongitude());
    }
}