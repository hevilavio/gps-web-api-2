package com.api.dao;

import com.api.model.Area;
import org.hibernate.Session;

/**
 * Created by hevilavio on 3/23/15.
 */
public class AreaDAOImpl implements AreaDAO {

    private final Session session;

    public AreaDAOImpl() {
        this.session = HibernateUtils.getSession();
    }

    @Override
    public Integer save(Area area) {
        session.save(area);
        return area.getId();
    }

    @Override
    public boolean update(Area area) {
        return false;
    }

    @Override
    public Area getActiveArea() {
        return null;
    }
}
