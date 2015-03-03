package com.api.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.api.model.Position;

/**
 * Created by hevilavio on 2/10/15.
 */
public class PositionDAOImpl implements PositionDAO {

    private final Session session;
    private final Transaction transaction;

    public PositionDAOImpl() {
        this.session = HibernateUtils.getSession();
        this.transaction = session.getTransaction();
    }

    @Override
    public Integer insert(Position position) {
        position.setDate(Calendar.getInstance());
        session.save(position);
        return position.getId();
    }

    @Override
    public Position getLast(Integer gpsId) {

        List<Position> list = getAllPositions(gpsId);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    @Override
    public List<Position> getAllPositions(Integer gpsId) {
        Criteria criteria = session.createCriteria(Position.class);
        criteria.add(Restrictions.eq("gpsId", gpsId));

        return criteria.list();
    }
}
