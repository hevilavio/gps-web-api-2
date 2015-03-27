package com.api.dao;

import com.api.model.Area;

/**
 * Created by hevilavio on 3/23/15.
 */
public interface AreaDAO {

    public Integer save(Area area);

    public boolean update(Area area);

    public Area getActiveArea();

}
