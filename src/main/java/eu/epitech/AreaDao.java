package eu.epitech;

import eu.epitech.Model.Entity.Area;
import eu.epitech.Model.Entity.User;

import java.util.List;

public interface AreaDao
{
    void save(Area area);
    void update(Area area);
    void delete(int areaId);
    Area getArea(int areaId);
    List<Area> getAreasByUser(int userId);

    List<Area>  list();
}
