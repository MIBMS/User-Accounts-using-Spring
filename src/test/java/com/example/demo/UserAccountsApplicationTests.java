package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration("context.xml")
public class UserAccountsApplicationTests {
	
	@Autowired
	private Teller teller;
	@Autowired
	private User user;
	@Autowired
	private Account account;
	
	@Test
	public void objectsShouldNotBeNull() {
		assertNotNull(teller);
		assertNotNull(user);
		assertNotNull(account);
	}

}
