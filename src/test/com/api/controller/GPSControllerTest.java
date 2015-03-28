package com.api.controller;


import com.api.Utils;
import com.api.UtilsValidator;
import com.api.dao.AreaDAO;
import com.api.dao.AreaDAOImpl;
import com.api.model.Area;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class GPSControllerTest {

    GPSController controller;
    AreaDAO areaDAO;

    @Before
    public void init() {
        controller = new GPSController();
        areaDAO = new AreaDAOImpl();
    }

    @Test
    public void testCreateNewArea() throws Exception {
        Area area = Utils.getArea();

        controller.createNewArea(null, area);

        UtilsValidator.validateArea(area);
    }

    @Test
    public void testCreateNewAreaAndInactivateLast() throws Exception {
        Area area = Utils.getArea();
        controller.createNewArea(null, area);
        Area activeArea = areaDAO.getActiveArea();

        assertEquals(activeArea.getId(), //
                area.getId());

        Area newArea = Utils.getArea();
        controller.createNewArea(null, newArea);
        Area newActiveArea = areaDAO.getActiveArea();

        assertNotEquals(newActiveArea.getId(), //
                area.getId());

        assertEquals(newActiveArea.getId(), //
                newArea.getId());
    }
}