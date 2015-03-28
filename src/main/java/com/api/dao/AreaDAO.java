package com.api.dao;

import com.api.model.Area;

/**
 * Created by hevilavio on 3/23/15.
 */
public interface AreaDAO {

    /**
     * Salva uma nova area, inativando a antiga
     * */
    public Integer createNew(Area area);

    public boolean update(Area area);

    public Area getActiveArea();

    public Area get(int id);

    public void finish();
}
