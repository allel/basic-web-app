package bioresultats.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bioresultats.core.models.entities.Account;
import bioresultats.core.models.entities.Blog;
import bioresultats.core.services.IAccountService;
import bioresultats.core.services.exceptions.AccountDoesNotExistsException;
import bioresultats.core.services.exceptions.AccountExistsException;
import bioresultats.core.services.exceptions.BlogExistsException;
import bioresultats.rest.exceptions.BadRequestException;
import bioresultats.rest.exceptions.ConflictException;
import bioresultats.rest.resources.AccountResource;
import bioresultats.rest.resources.BlogResource;
import bioresultats.rest.resources.asm.AccountResourceAsm;
import bioresultats.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
	
	private IAccountService accountService;

	@Autowired
	public AccountController(IAccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(
			@RequestBody AccountResource sentAccount) {
		try {
			Account createdAccount = accountService.createAccount(sentAccount
					.toAccount());
			AccountResource res = new AccountResourceAsm()
					.toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers,
					HttpStatus.CREATED);
		} catch (AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(
			@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(
			@PathVariable Long accountId, @RequestBody BlogResource res) {
		try {
			Blog createdBlog = accountService.createBlog(accountId,
					res.toBlog());
			BlogResource createdBlogRes = new BlogResourceAsm()
					.toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogRes.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogResource>(createdBlogRes, headers,
					HttpStatus.CREATED);
		} catch (AccountDoesNotExistsException exception) {
			throw new BadRequestException(exception);
		} catch (BlogExistsException exception) {
			throw new ConflictException(exception);
		}
	}
}