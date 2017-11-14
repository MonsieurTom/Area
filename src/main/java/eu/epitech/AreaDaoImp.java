package eu.epitech;

import eu.epitech.Model.Entity.Area;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nullable;
import javax.sql.DataSource;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

public class AreaDaoImp implements  AreaDao
{
	private JdbcTemplate jdbcTemplate;

	public AreaDaoImp(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	 public void save(Area area)
	{
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			new ObjectOutputStream(out).writeObject(area.getActionArgs());
			String actionArgs = new String(Hex.encodeHex(out.toByteArray()));
			out.reset();
			new ObjectOutputStream(out).writeObject(area.getReactionArgs());
			String reactionArgs = new String(Hex.encodeHex(out.toByteArray()));

			String sql = "INSERT INTO Area (actionName, actionArgs, reactionName, reactionArgs, tmpData, user_id) VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, area.getActionName(), actionArgs, area.getReactionName(),
					reactionArgs, area.getTmpData(), area.getUserId());
		}
		catch (Exception e)
		{ System.out.println("error catch saveArea"); }
	}

	@Override
	public void update(Area area)
	{
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			new ObjectOutputStream(out).writeObject(area.getActionArgs());
			String actionArgs = new String(Hex.encodeHex(out.toByteArray()));
			out.reset();
			new ObjectOutputStream(out).writeObject(area.getReactionArgs());
			String reactionArgs = new String(Hex.encodeHex(out.toByteArray()));

			String sql = "UPDATE  Area SET actionName=?, actionArgs=?, reactionName=?, reactionArgs=?, tmpData=?, user_id=? WHERE area_id=?";
			jdbcTemplate.update(sql, area.getActionName(), actionArgs, area.getReactionName(),
					reactionArgs, area.getTmpData(), area.getUserId(), area.getId());
		}
		catch (Exception e)
		{ System.out.println("error catch updateArea"); }
	}

	@Override
	public void delete(int areaId)
	{
		String sql = "DELETE FROM Area WHERE area_id=?";
		jdbcTemplate.update(sql, areaId);
	}

	@Override
	public Area	getArea(int areaId)
	{
		String sql = "SELECT * FROM Area WHERE area_id=" + areaId;
		return (jdbcTemplate.query(sql, new ResultSetExtractor<Area>() {
			@Nullable
			@Override
			public Area extractData(ResultSet resultSet) throws SQLException, DataAccessException
			{
				if (resultSet.next())
				{
					try {
						ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("actionArgs").toCharArray()));
						String[] actionArgs = (String[]) new ObjectInputStream(in).readObject();
						in.reset();
						in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("reactionArgs").toCharArray()));
						String[] reactionArgs = (String[]) new ObjectInputStream(in).readObject();
						Area area = new Area();
						area.setActionName(resultSet.getString("actionName"));
						area.setActionArgs(actionArgs);
						area.setReactionName(resultSet.getString("reactionName"));
						area.setReactionArgs(reactionArgs);
						area.setTmpData(resultSet.getString("tmpData"));
						area.setId(resultSet.getInt("area_id"));
						area.setUserId(resultSet.getInt("user_id"));
						return (area);
					}
					catch (Exception e)
					{ System.out.println("error catch getArea"); }
				}
				return (null);
			}
		}));
	}

	public List<Area> getAreasByUser(int userId)
	{
		String sql = "Select * FROM Area WHERE user_id=" + userId;
		List<Area> listArea = jdbcTemplate.query(sql, new RowMapper<Area>()
		{
			@Override
			public Area mapRow(ResultSet resultSet, int i) throws SQLException
			{
				try {
					ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("actionArgs").toCharArray()));
					String[] actionArgs = (String[]) new ObjectInputStream(in).readObject();
					in.reset();
					in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("reactionArgs").toCharArray()));
					String[] reactionArgs = (String[]) new ObjectInputStream(in).readObject();

					Area area = new Area();
					area.setActionName(resultSet.getString("actionName"));
					area.setActionArgs(actionArgs);
					area.setReactionName(resultSet.getString("reactionName"));
					area.setReactionArgs(reactionArgs);
					area.setTmpData(resultSet.getString("tmpData"));
					area.setId(resultSet.getInt("area_id"));
					area.setUserId(resultSet.getInt("user_id"));
					return (area);
				}
				catch (Exception e)
				{ System.out.println("error catch getArea"); }
				return (null);
			}
		});
		return (listArea);
	}

	public List<Area> list()
	{
		String sql = "Select * FROM Area";
		List<Area> listArea = jdbcTemplate.query(sql, new RowMapper<Area>()
		{
			@Override
			public Area mapRow(ResultSet resultSet, int i) throws SQLException
			{
				try {
					ByteArrayInputStream in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("actionArgs").toCharArray()));
					String[] actionArgs = (String[]) new ObjectInputStream(in).readObject();
					in.reset();
					in = new ByteArrayInputStream(Hex.decodeHex(resultSet.getString("reactionArgs").toCharArray()));
					String[] reactionArgs = (String[]) new ObjectInputStream(in).readObject();

					Area area = new Area();
					area.setActionName(resultSet.getString("actionName"));
					area.setActionArgs(actionArgs);
					area.setReactionName(resultSet.getString("reactionName"));
					area.setReactionArgs(reactionArgs);
					area.setTmpData(resultSet.getString("tmpData"));
					area.setId(resultSet.getInt("area_id"));
					area.setUserId(resultSet.getInt("user_id"));
					return (area);
				}
				catch (Exception e)
				{ System.out.println("error catch getArea"); }
				return (null);
			}
		});
		return (listArea);
	}
}
