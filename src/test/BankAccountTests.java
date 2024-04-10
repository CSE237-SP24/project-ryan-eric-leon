package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void testEmptyTransaction() {
		BankAccount testAccount = new BankAccount();
		
		assertEquals(0, testAccount.getTransactionSize(), 0.01);
	}
	
	@Test 
	void testProperTransactionList(){
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