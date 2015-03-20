package com.api.model;

import com.api.Utils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CalculatorImplTest {

    private Calculator calculator;
    private Area area;

    @Before
    public void init() {
        calculator = new CalculatorImpl();
    }

    @Test
    public void testCheckIsInsideArea() {
        area = Utils.getRetangle();

        Position p1 = new Position();
        p1.setLatitude(10);
        p1.setLongitude(21);

        Position p2 = new Position();
        p2.setLatitude(10);
        p2.setLongitude(39);

        assertTrue(calculator.isInsideArea(p1, area));
        assertTrue(calculator.isInsideArea(p2, area));
    }

    @Test
    public void testCheckNotIsInsideArea() {
        area = Utils.getRetangle();

        Position p1 = new Position();
        p1.setLatitude(10);
        p1.setLongitude(19);

        Position p2 = new Position();
        p2.setLatitude(5);
        p2.setLongitude(25);

        assertFalse("Falhou para p1=" + p1.toString(), calculator.isInsideArea(p1, area));
        assertFalse("Falhou para p2=" + p2.toString(), calculator.isInsideArea(p2, area));
    }

    @Test
    public void testCantFindCandidatesUpsideArea() {

        area = Utils.getRetangle();

        Position p = new Position();
        p.setLatitude(20);
        p.setLongitude(50);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(0, candidatesToInterssect.size());
    }

    @Test
    public void testCantFindCandidatesDownsideArea() {

        area = Utils.getRetangle();

        Position p = new Position();
        p.setLatitude(20);
        p.setLongitude(0);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(0, candidatesToInterssect.size());
    }

    @Test
    public void testFindCandidatesInRetangle() {

        area = Utils.getRetangle();

        // está dentro do retangulo
        Position p = new Position();
        p.setLatitude(11);
        p.setLongitude(21);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(2, candidatesToInterssect.size());

    }

    @Test
    public void testCantFindCandidatesOutOfRetangle() {

        area = Utils.getRetangle();

        // está dentro do retangulo
        Position p = new Position();
        p.setLatitude(10);
        p.setLongitude(50);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(0, candidatesToInterssect.size());

    }

    @Test
    public void testFindCandidatesInTriangle() {

        area = Utils.getTriangle();

        // está dentro do triangulo
        Position p = new Position();
        p.setLatitude(45);
        p.setLongitude(25);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(2, candidatesToInterssect.size());
    }

    @Test
    public void testCantFindCandidatesOutOfTriangle() {

        area = Utils.getTriangle();

        // está fora do triangulo
        Position p = new Position();
        p.setLatitude(70);
        p.setLongitude(20);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(0, candidatesToInterssect.size());
    }

    @Test
    public void testCanFindCandidatesInDisform_2() {
        area = Utils.getDisform();

        Position p = new Position();
        p.setLatitude(11);
        p.setLongitude(11);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(2, candidatesToInterssect.size());
    }

    @Test
    public void testCanFindCandidatesInDisform_4() {
        area = Utils.getDisform();

        Position p = new Position();
        p.setLatitude(11);
        p.setLongitude(35);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(4, candidatesToInterssect.size());
    }

    @Test
    public void testCanFindCandidatesInDisformAtRight() {
        area = Utils.getDisform();

        Position p = new Position();
        p.setLatitude(70);
        p.setLongitude(21);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(4, candidatesToInterssect.size());
    }

    @Test
    public void testCantFindCandidatesOutOfDisform() {
        area = Utils.getDisform();

        Position p = new Position();
        p.setLatitude(10);
        p.setLongitude(9.99);

        final List<Pair<AreaPosition, AreaPosition>> candidatesToInterssect = calculator
                .findCandidatesToInterssect(p, area);

        assertEquals(0, candidatesToInterssect.size());
    }
}