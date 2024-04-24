package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		// 1. Setup Objects

		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		testAccount.deposit(25);

		// 3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);
	}

	@Test
	void testNegativeDeposit() {
		// 1. Setup Objects
		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testSimpleWithdraw() {
		// 1. Setup Objects

		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		testAccount.deposit(25);
		testAccount.withdraw(25);
		// 3. Use assertions to verify results
		assertEquals(0.0, testAccount.getBalance(), 0.01);
	}

	@Test
	void testNegativeWithdraw() {
		// 1. Setup Objects
		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		try {
			testAccount.withdraw(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testAmountLargerThanBalance() {
		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		try {
			testAccount.withdraw(25);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here because 25 is larger than the default balance of 0
			assertTrue(true);
		}
	}

	@Test
	void testSimpleTransfer() {
		BankAccount testAccount1 = new BankAccount();
		BankAccount testAccount2 = new BankAccount();

		testAccount1.deposit(10);
		testAccount1.transfer(10, testAccount2);

		assertEquals(0.0, testAccount1.getBalance(), 0.01);
		assertEquals(10.0, testAccount2.getBalance(), 0.01);

	}

	@Test
	void testNegativeTransfer() {
		BankAccount testAccount1 = new BankAccount();
		BankAccount testAccount2 = new BankAccount();

		testAccount1.deposit(25);
		testAccount1.transfer(25, testAccount2);

		try {
			testAccount1.transfer(-25, testAccount2);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testCorrectPassword() {
		String password = "test-password";

		BankAccount testAccount = new BankAccount(password);

		assertTrue(testAccount.checkPassword("test-password"));
	}

	@Test
	void testWrongPassword() {
		String password = "test-password";

		BankAccount testAccount = new BankAccount(password);

		assertFalse(testAccount.checkPassword("wrong-password"));
	}

	@Test
	// a no-password account essentially has an empty string as password
	void testNoPassword() {
		String emptyPassword = "";

		BankAccount testAccountA = new BankAccount();
		BankAccount testAccountB = new BankAccount(emptyPassword);
		BankAccount testAccountC = new BankAccount("test-password");

		assertTrue(testAccountA.checkPassword(emptyPassword));
		assertTrue(testAccountA.getPassword().equals(testAccountB.getPassword()));
		assertFalse(testAccountA.getPassword().equals(testAccountC.getPassword()));
	}

	@Test
	// adding a password to an account is essentially setting a new password to
	// replace the empty string password
	void testAddPassword() {
		String newPassword = "new-password";

		BankAccount testAccount = new BankAccount();
		testAccount.setPassword("", newPassword);

		assertTrue(testAccount.checkPassword("new-password"));
	}

	@Test
	// removing a password from an account is essentially setting a new empty string
	// password to replace the original string password
	void testRemovePassword() {
		String oldPassword = "old-password";

		BankAccount testAccount = new BankAccount(oldPassword);
		testAccount.setPassword(oldPassword, "");

		assertTrue(testAccount.getPassword().isEmpty());
	}

	@Test
	void testChangePasswordCorrectOldPassword() {
		String oldPassword = "old-password";
		String newPassword = "new-password";

		BankAccount testAccount = new BankAccount(oldPassword);
		testAccount.setPassword(oldPassword, newPassword);

		assertTrue(testAccount.checkPassword("new-password"));
	}

	@Test
	void testChangePasswordWrongOldPassword() {
		String oldPassword = "old-password";
		String newPassword = "new-password";

		BankAccount testAccount = new BankAccount(oldPassword);

		try {
			testAccount.setPassword("wrong-password", newPassword);
			fail();
		} catch (IllegalArgumentException e) {
			// we expect to end up here, the old password doesn't match
			assertTrue(true);
		}
	}

	@Test
	void testEmptyTransaction() {
		BankAccount testAccount = new BankAccount();

		assertEquals(0, testAccount.getTransactionSize(), 0.01);
	}

	@Test
	void testProperTransactionList() {
		BankAccount testAccount = new BankAccount();
		BankAccount testAccount2 = new BankAccount();
		testAccount.deposit(25);
		testAccount.withdraw(15);
		testAccount.transfer(10, testAccount2);

		assertEquals("Deposit: 25.0", testAccount.getTransactionList().get(0));
		assertEquals("Withdraw: 15.0", testAccount.getTransactionList().get(1));
		assertEquals("Deposit: 10.0", testAccount2.getTransactionList().get(0));
		assertEquals("Withdraw: 10.0", testAccount.getTransactionList().get(2));
	}
}
