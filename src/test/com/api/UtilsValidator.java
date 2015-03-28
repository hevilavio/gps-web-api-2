package com.api;

import com.api.model.Area;
import com.api.model.AreaPosition;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hevilavio on 3/28/15.
 */
public class UtilsValidator {
    public static void validateArea(Area area) {

        assertNotNull(area);
        assertNotEquals(0, area.getId());

        List<AreaPosition> positions = area.getPositions();
        for (AreaPosition position : positions) {
            assertNotEquals(0, position.getId());
            assertNotNull(position.getArea());
        }

    }
}
