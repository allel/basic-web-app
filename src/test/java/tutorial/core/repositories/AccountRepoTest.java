package tutorial.core.repositories;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tutorial.core.models.entities.Account;
import tutorial.core.repositories.jpa.JpaAccountRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {
	
	@Autowired
	private JpaAccountRepo repo;
	
	private Account account;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setup() {
		account = new Account();
		account.setName("Test");
		account.setPassword("pwd");
		
		repo.createAccount(account);
	}
	
	@Test
	@Transactional
	public void test() {
		assertNotNull(repo.findAccount(account.getId()));
	}

}
