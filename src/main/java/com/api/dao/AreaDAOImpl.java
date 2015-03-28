package com.api.dao;

import com.api.model.Area;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hevilavio on 3/23/15.
 */
public class AreaDAOImpl implements AreaDAO {

    private final Session session;

    public AreaDAOImpl() {
        this.session = HibernateUtils.getSession();
        session.setCacheMode(CacheMode.IGNORE);
    }

    @Override
    public Integer createNew(Area area) {

        Area activeArea = getActiveArea();
        if(activeArea != null){
            activeArea.setActive(false);
            update(activeArea);
        }

        session.save(area);
        session.flush();
        return area.getId();
    }

    @Override
    public boolean update(Area area) {
        session.update(area);
        session.flush();
        return true;
    }

    @Override
    public Area getActiveArea() {
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Restrictions.eq("active", true));

        Object result = criteria.uniqueResult();
        if(result == null){
            return null;
        }

        return (Area) result;
    }

    @Override
    public Area get(int id) {
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Restrictions.eq("id", id));

        return (Area) criteria.uniqueResult();
    }

    @Override
    public void finish() {
        session.close();
    }
}
