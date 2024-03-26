package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		// assume user has provided value input of 50
		m.processDeposit(50);

		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
	}

	@Test
	void testUserWithdraw() {
		Menu m = new Menu();
		BankAccount account = m.getAccount();
		account.deposit(50);

		// assume user has provided value input of 30
		m.processWithdraw(30);

		assertEquals(20, account.getBalance(), 0.01);
	}

	@Test
	void testUserTransfer() {
		Menu m = new Menu();
		m.getAccount().deposit(50);
		m.insertAccount("Bob");

		m.processTransfer(30, "Bob");

		// original account should now have 20
		assertEquals(20, m.getAccount().getBalance(), 0.01);
		// Bob should now have 30
		m.switchAccount("Bob");
		assertEquals(30, m.getAccount().getBalance(), 0.01);
	}

	@Test
	void testInsertAccount() {
		Menu m = new Menu();

		BankAccount account = m.getAccount();
		account.deposit(50);
		m.processInsert("Bob");

		// we should be able to insert a new account without affecting current account
		assertEquals(50, m.getAccount().getBalance(), 0.01);
	}

	@Test
	void testSwitchAccount() {
		Menu m = new Menu();
		m.insertAccount("Bob");
		m.insertAccount("Alice");

		// try switch to Bob and deposit money into it
		m.processSwitch("Bob");
		m.processDeposit(50);
		// try switch to Alice and deposit money into it
		m.processSwitch("Alice");
		m.processDeposit(25);

		assertEquals(25, m.getAccount().getBalance(), 0.01);
		// switching back to Bob to see if its balance is unaffected
		m.processSwitch("Bob");
		assertEquals(50, m.getAccount().getBalance(), 0.01);
	}
}
