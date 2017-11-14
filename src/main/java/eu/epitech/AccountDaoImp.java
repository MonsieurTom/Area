package eu.epitech;

import eu.epitech.Model.Entity.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImp implements AccountDao
{
	private JdbcTemplate jdbcTemplate;

	public AccountDaoImp(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Account account)
	{
		String sql = "INSERT INTO Account (token, moduleName, user_id) VALUES (?, ?, ?)";
		this.jdbcTemplate.update(sql, account.getToken(), account.getModuleName(), account.getUserId());
	}

	@Override
	public void update(Account account)
	{
		String sql = "UPDATE Account SET token=?, moduleName=?, user_id=? WHERE id=?";
		this.jdbcTemplate.update(sql, account.getToken(), account.getModuleName(), account.getUserId(), account.getId());
	}

	@Override
	public void delete(int accountId)
	{
		String sql = "DELETE FROM Account WHERE id=?";
		this.jdbcTemplate.update(sql, accountId);
	}

	@Override
	public Account getAccount(int accountId) {
		String sql = "SELECT * FROM Account WHERE id=" + accountId;
		return (this.jdbcTemplate.query(sql, new ResultSetExtractor<Account>()
		{
			@Nullable
			@Override
			public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException
			{
				if (resultSet.next())
				{
					Account account = new Account();
					account.setUserId(resultSet.getInt("user_id"));
					account.setModuleName(resultSet.getString("moduleName"));
					account.setToken(resultSet.getString("token"));
					account.setId(resultSet.getInt("id"));
					return (account);
				}
				return (null);
			}
		}));
	}

	@Override
	public String getToken(int user_id, String moduleName) {
		String sql = "SELECT * FROM Account WHERE user_id=" + user_id + " AND moduleName=" + moduleName;
		return (jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
			@Nullable
			@Override
			public String extractData(ResultSet resultSet) throws SQLException, DataAccessException
			{
				if (resultSet.next())
					return (resultSet.getString("token"));
				return null;
			}
		}));
	}

	@Override
	public List<Account> list()
	{
		String sql = "SELECT * FROM Account";
		List<Account> list = this.jdbcTemplate.query(sql, new RowMapper<Account>()
		{
			@Override
			public Account mapRow(ResultSet resultSet, int i) throws SQLException
			{
				Account account = new Account();
				account.setToken(resultSet.getString("token"));
				account.setModuleName(resultSet.getString("moduleName"));
				account.setUserId(resultSet.getInt("user_id"));
				account.setId(resultSet.getInt("id"));
				return (account);
			}
		});
		return (list);
	}
}
