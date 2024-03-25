package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		//user has provided value input of 50
		m.processingUserSelection(50);
		
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
	}
	
	@Test
	void testSwitchAccount() {
		Menu m = new Menu();
		BankAccount account1 = m.getAccount();
		account1.deposit(50);
		m.insertAccount("Bob", 30);
		assertEquals(50, m.getAccount().getBalance(), 0.01);
		m.switchAccounts("Bob");
		assertEquals(30, m.getAccount().getBalance(), 0.01);
		
	}

}
