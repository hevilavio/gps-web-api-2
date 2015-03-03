package com.api.dao;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.api.model.Position;

/**
 * Este é um teste de integração, ou seja, precisa da database rodando.
 * */
public class PositionDAOImplTest {

    private PositionDAO positionDAO;

    @Before
    public void setUp() throws Exception {
        positionDAO = new PositionDAOImpl();
    }

    @Test
    public void canInsert() {

        Position position = new Position();
        position.setPosX(10);
        position.setPosY(20);
        position.setGpsId(1010);
        position.setDate(Calendar.getInstance());

        positionDAO.insert(position);

        assertNotNull(position);
        assertTrue(position.getId() > 0);
    }

    @Test
    public void canGetLast() {

        Integer gpsId = 1010;
        Calendar calendar = Calendar.getInstance();

        Position position = insertPosition(gpsId, calendar);
        Position last = positionDAO.getLast(gpsId);

        assertEquals(position.getGpsId(), last.getGpsId(), 0.0001);
        assertEquals(position.getDate().getTimeInMillis(), last.getDate().getTimeInMillis());

    }

    @Test
    public void canGetAllPositions() {

        Integer gpsId = new Random().nextInt();
        Calendar calendar = Calendar.getInstance();

        Position position1 = insertPosition(gpsId, calendar);
        Position position2 = insertPosition(gpsId, calendar);
        Position position3 = insertPosition(gpsId, calendar);

        List<Position> allPositions = positionDAO.getAllPositions(gpsId);

        assertNotNull(allPositions);
        assertEquals(("Pode quebrar, caso já existisse o gpsId gerado. gpsId=" + gpsId), 3, allPositions.size());
        assertTrue(allPositions.size() >= 3);

    }

    private Position insertPosition(Integer gpsId, Calendar calendar) {
        Position position = new Position();
        position.setPosX(10);
        position.setPosY(20);
        position.setGpsId(gpsId);
        position.setDate(calendar);

        positionDAO.insert(position);

        assertNotNull("Erro ao adicionar Position de teste", position);
        return position;
    }

}