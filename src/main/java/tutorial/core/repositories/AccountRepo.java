package tutorial.core.repositories;

import java.util.List;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;

public interface AccountRepo {

	public List<Account> findAllAccounts();

	public Account findAccount(Long id);

	public Account findAccountByName(String name);

	public Account createAccount(Account data);

}
