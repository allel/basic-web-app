package tutorial.core.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.repositories.AccountRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.IAccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistsException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BlogRepo blogRepo;

	@Override
	public Account findAccount(Long id) {
		return accountRepo.findAccount(id);
	}

	@Override
	public Account createAccount(Account data) {
		Account account = accountRepo.findAccountByName(data.getName());
		if (account != null) {
			throw new AccountExistsException();
		}
		return accountRepo.createAccount(data);
	}

	@Override
	public Blog createBlog(Long accountId, Blog data) {
		Blog blogSameTitle = blogRepo.findBlogByTitle(data.getTitle());
		if (blogSameTitle != null) {
			throw new BlogExistsException();
		}
		Account account = accountRepo.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistsException();
		}
		Blog createdBlog = blogRepo.createBlog(data);
		createdBlog.setOwner(account);
		return createdBlog;
	}

	@Override
	public BlogList findBlogsByAccount(Long accountId) {
		Account account = accountRepo.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistsException();
		}
		return new BlogList(blogRepo.findBlogsByAccount(accountId));
	}

	@Override
	public AccountList findAllAccounts() {
		return new AccountList(accountRepo.findAllAccounts());
	}

	@Override
	public Account findByAccountName(String name) {
		return accountRepo.findAccountByName(name);
	}
}
