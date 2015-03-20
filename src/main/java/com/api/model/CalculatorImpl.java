package com.api.model;

import com.api.controller.GPSController;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hevilavio on 3/17/15.
 */

public class CalculatorImpl implements Calculator {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GPSController.class);

    @Override
    public boolean isInsideArea(Position position, Area area) {

        final List<Pair<Position, Position>> candidatesToInterssect = findCandidatesToInterssect(position, area);
        logger.info("M=isInsideArea, size=" + candidatesToInterssect.size());

        // TODO Melhorar este algoritmo
        int realInsersections = 0;
        for (Pair<Position, Position> candidate : candidatesToInterssect) {
            Position a = candidate.getA();
            Position b = candidate.getB();

            logger.info("M=isInsideArea, a.getLatitude=" + a.getLatitude() + ", latitude=" + position.getLatitude()
                    + ", b.getLatitude=" + b.getLatitude());

            if (position.getLatitude() < a.getLatitude() || position.getLatitude() < b.getLatitude()) {
                realInsersections++;
            }
        }
        // Quando o numero de intersecções é impar, significa que o ponto
        // está dentro da área (salvo casos especiais).
        return realInsersections > 0 && realInsersections % 2 != 0;
    }

    @Override
    public List<Pair<Position, Position>> findCandidatesToInterssect(Position position, Area area) {

        final List<Position> positionList = area.getPositionList();

        double longitude = position.getLongitude();

        List<Pair<Position, Position>> pairs = new ArrayList<>();
        final Iterator<Position> iterator = positionList.iterator();

        Position prev = null;
        while (iterator.hasNext()) {
            if (prev == null) {
                prev = iterator.next();
                continue;
            }
            Position current = iterator.next();

            // Verifica se os pontos estão em lados distintos da reta R
            if (prev.getLongitude() < longitude && longitude < current.getLongitude() || // "subindo"
                    prev.getLongitude() > longitude && longitude > current.getLongitude()) { // "descendo"

                logger.info("M=findCandidatesToInterssect, prev.getLongitude=" + prev.getLongitude() //
                        + ", longitude=" + longitude //
                        + ", current.getLongitude=" + current.getLongitude());

                pairs.add(new Pair<Position, Position>(prev, current));

                if (iterator.hasNext()) {
                    prev = iterator.next();
                }
            }
        }

        return pairs;

    }

    @Override
    public int countRealInterssect(Position position, Area area) {
        return 0;
    }
}

