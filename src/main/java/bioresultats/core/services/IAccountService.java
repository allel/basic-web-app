package bioresultats.core.services;

import bioresultats.core.models.entities.Account;
import bioresultats.core.models.entities.Blog;
import bioresultats.core.services.util.AccountList;
import bioresultats.core.services.util.BlogList;

public interface IAccountService {

	public Account findAccount(Long id);

	public Account createAccount(Account data);

	public Blog createBlog(Long accountId, Blog data);

	public BlogList findBlogsByAccount(Long accountId);

	public AccountList findAllAccounts();

	public Account findByAccountName(String name);
}
