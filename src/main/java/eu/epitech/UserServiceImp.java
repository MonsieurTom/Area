package eu.epitech;

import eu.epitech.Model.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService
{
    private UserDao userDao;

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }


    @Transactional
    public void delete(int userId) { userDao.delete(userId); }

    @Transactional
    public void update(User user) { userDao.update(user);}

    @Transactional
    public User getUser(int userId) { return (userDao.getUser(userId)); }

    @Transactional(readOnly = true)
    public List<User> list() {
        return userDao.list();
    }
}
