package com.api.dao;

import com.api.Utils;
import com.api.UtilsValidator;
import com.api.model.Area;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaDAOImplTest {

    private AreaDAO areaDAO;

    @Before
    public void init() {
        areaDAO = new AreaDAOImpl();
    }

    @Test
    public void testCreateNew() {
        Area area = Utils.getArea();

        areaDAO.createNew(area);

        UtilsValidator.validateArea(area);
    }


    @Test
    public void testGet(){
        Area area = Utils.getArea();
        Area area2 = Utils.getArea();

        areaDAO.createNew(area);

        int id = area.getId();
        Area area1Get = areaDAO.get(id);

        assertNotNull(area);
    }

    // TODO - fixme. Este teste nao esta confiante! Mesmo sem rodar o 'areaDAO.update(area);' ele passa
    @Test
    public void testUpdate(){
        Area area = Utils.getArea();

        // salvamos como false
        area.setActive(false);
        areaDAO.createNew(area);
        assertNotEquals(0, area.getId());

        // recuperamos a area salva acima
        Area area1 = areaDAO.get(area.getId());
        assertEquals(false, area1.isActive());

        area1.setActive(true);
        areaDAO.update(area);

        // validamos
        Area area2 = areaDAO.get(area.getId());
        assertEquals(true, area2.isActive());
    }

    @Test
    public void testGetActive(){
        Area area = Utils.getArea();
        areaDAO.createNew(area);

        Area activeArea = areaDAO.getActiveArea();

        assertNotNull(activeArea);
        assertEquals(true, activeArea.isActive());
        assertEquals(3, activeArea.getPositions().size());

    }
}