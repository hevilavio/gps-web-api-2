package com.api;

import com.api.model.Area;
import com.api.model.Position;

/**
 * Created by hevilavio on 3/17/15.
 */
public class Utils {

    public static Area getRetangle() {
        Position p1 = new Position();
        p1.setLatitude(10);
        p1.setLongitude(20);

        Position p2 = new Position();
        p2.setLatitude(10);
        p2.setLongitude(40);

        Position p3 = new Position();
        p3.setLatitude(20);
        p3.setLongitude(40);

        Position p4 = new Position();
        p4.setLatitude(20);
        p4.setLongitude(20);

        return new Area(p1, p2, p3, p4, p1);
    }

    public static Area getTriangle() {
        Position p1 = new Position();
        p1.setLatitude(40);
        p1.setLongitude(40);

        Position p2 = new Position();
        p2.setLatitude(60);
        p2.setLongitude(20);

        Position p3 = new Position();
        p3.setLatitude(20);
        p3.setLongitude(20);

        return new Area(p1, p2, p3, p1);
    }

    /**
     * ....    ....
     * .  .    .  .
     * .  ......  .
     * ............
     * *
     */
    public static Area getDisform() {

        return new Area(
                create(10, 10), //
                create(10, 40), //
                create(40, 40), //
                create(40, 20), //
                create(60, 20), //
                create(60, 40), //
                create(80, 40), //
                create(80, 10), //
                create(10, 10));
    }

    private static Position create(double x, double y) {
        Position p = new Position();
        p.setLatitude(x);
        p.setLongitude(y);
        return p;
    }
}
