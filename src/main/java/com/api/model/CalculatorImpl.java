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
        logger.info("M=IIA, size=" + candidatesToInterssect.size());

        // TODO Melhorar este algoritmo
        int realInsersections = 0;
        for (Pair<Position, Position> candidate : candidatesToInterssect) {
            Position a = candidate.getA();
            Position b = candidate.getB();

            logger.info("M=IIA, a.X=" + a.getPosX() + ", X=" + position.getPosX() + ", b.X=" + b.getPosX());
            if (position.getPosX() < a.getPosX() || position.getPosX() < b.getPosX()) {
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

        double posY = position.getPosY();

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
            if (prev.getPosY() < posY && posY < current.getPosY() || // "subindo"
                    prev.getPosY() > posY && posY > current.getPosY()) { // "descendo"

                logger.info("M=FCTI, prev.Y=" + prev.getPosY() //
                        + ", posY=" + posY //
                        + ", current.Y=" + current.getPosY());

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

