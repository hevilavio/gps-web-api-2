package com.api.dao;

import com.api.model.Area;
import com.api.model.AreaPosition;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AreaDAOImplTest {

    private AreaDAO areaDAO;

    @Before
    public void init() {
        areaDAO = new AreaDAOImpl();
    }

    @Test
    public void testSave() {
        Area area = new Area();

        area.setCreatedAt(Calendar.getInstance());
        area.setActive(true);

        List<AreaPosition> areaPositions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AreaPosition areaPosition = new AreaPosition();

            areaPosition.setArea(area);
            areaPosition.setLatitude(10.00);
            areaPosition.setLongitude(10.00);

            areaPositions.add(areaPosition);
        }

        area.setPositions(areaPositions);

        areaDAO.save(area);
    }
}