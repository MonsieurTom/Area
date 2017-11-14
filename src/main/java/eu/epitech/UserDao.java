package eu.epitech;

import eu.epitech.Model.Entity.User;

import java.util.List;

public interface UserDao
{
    void save(User user);
    void update(User user);
    void delete(int userId);
    User getUser(int userId);
    User getUser(String mail);

    List<User> list();
}
