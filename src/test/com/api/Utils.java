package com.api;

import com.api.model.Area;
import com.api.model.AreaPosition;

/**
 * Created by hevilavio on 3/17/15.
 */
public class Utils {

    public static Area getRetangle() {

        return new Area(create(10, 20),
                create(10, 40),
                create(20, 40),
                create(20, 20),
                create(10, 20));
    }

    public static Area getTriangle() {

        return new Area(create(40, 40),
                create(60, 20),
                create(20, 20),
                create(40, 40));
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

    private static AreaPosition create(double x, double y) {
        AreaPosition areaPosition = new AreaPosition();
        areaPosition.setLatitude(x);
        areaPosition.setLongitude(y);
        return areaPosition;
    }
}
