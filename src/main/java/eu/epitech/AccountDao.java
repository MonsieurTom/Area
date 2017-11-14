package eu.epitech;

import eu.epitech.Model.Entity.Account;

import java.util.List;

public interface AccountDao
{
	void save(Account account);
	void update(Account account);
	void delete(int accountId);
	Account getAccount(int accountId);
	String getToken(int user_id, String moduleName);

	List<Account> list();
}
