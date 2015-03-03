package com.api.dao;

import com.api.model.Position;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by hevilavio on 2/9/15.
 */
public interface PositionDAO {

    public Integer insert(Position position);

    public Position getLast(Integer gpsId);

    public List<Position> getAllPositions(Integer gpsId);

}
