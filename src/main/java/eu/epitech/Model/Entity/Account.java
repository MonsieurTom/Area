package eu.epitech.Model.Entity;

public class Account implements IAccount
{
	private String		token;
	private String		moduleName;
	private int			userId;
	private int			id;

	public Account()
	{ }

	public Account(String token, String moduleName, int userId, int id)
	{
		this.token = token;
		this.moduleName = moduleName;
		this.id = id;
		this.userId = userId;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setUserId(int userId)
	{
		this.userId = id;
	}

	@Override
	public String getToken()
	{ return (this.token); }

	@Override
	public String getModuleName() {
		return moduleName;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getUserId() {
		return userId;
	}
}
