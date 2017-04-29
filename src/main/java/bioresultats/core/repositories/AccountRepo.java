package bioresultats.core.repositories;

import java.util.List;

import bioresultats.core.models.entities.Account;

public interface AccountRepo {

	 List<Account> findAllAccounts();

	 Account findAccount(Long id);

	 Account findAccountByName(String name);

	 Account createAccount(Account data);

}
