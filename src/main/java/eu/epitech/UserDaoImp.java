package eu.epitech;

import eu.epitech.Model.Entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImp implements UserDao
{

    private JdbcTemplate jdbcTemplate;

    public UserDaoImp(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user)
    {
        String sql= "INSERT INTO User (mail, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    @Override
    public void update(User user)
    {
        String sql = "UPDATE User SET mail=?, password=?, banned=? WHERE user_id=?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getBanned(), user.getId());
    }

    @Override
    public void delete(int userId)
    {
        String sql = "DELETE FROM User WHERE user_id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User getUser(int userId)
    {
        String sql = "SELECT * FROM User WHERE user_id=" + userId;
        return (jdbcTemplate.query(sql, new ResultSetExtractor<User>()
        {
            @Nullable
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException
            {
                if (resultSet.next())
                {
                    User user = new User();

                    user.setId(resultSet.getInt("user_id"));
                    user.setEmail(resultSet.getString("mail"));
                    user.setPassword(resultSet.getString("password"));
                    user.setBanned(resultSet.getBoolean("banned"));
                    return (user);
                }
                return (null);
            }
        }));
    }

    @Override
    public User getUser(String mail)
    {
        String sql = "SELECT * FROM User WHERE mail='" + mail + "'";
        return (jdbcTemplate.query(sql, new ResultSetExtractor<User>()
        {
            @Nullable
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException
            {
                if (resultSet.next())
                {
                    User user = new User();

                    user.setId(resultSet.getInt("user_id"));
                    user.setEmail(resultSet.getString("mail"));
                    user.setPassword(resultSet.getString("password"));
                    user.setBanned(resultSet.getBoolean("banned"));
                    return (user);
                }
                return (null);
            }
        }));
    }

    @Override
    public List<User>   list()
    {
        String sql = "SELECT * FROM User";
        List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>()
        {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException
            {
                User newUser = new User();

                newUser.setId(resultSet.getInt("user_id"));
                newUser.setEmail(resultSet.getString("mail"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setBanned(resultSet.getBoolean("banned"));
                return (newUser);
            }
        });
        return (listUser);
    }
}
