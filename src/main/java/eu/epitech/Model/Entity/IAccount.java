package eu.epitech.Model.Entity;

public interface IAccount
{
	void setId(int id);
	void setUserId(int user_id);
	void setToken(String token);
	void setModuleName(String token);
	String getToken();
	String getModuleName();
	int getId();
	int getUserId();
}
