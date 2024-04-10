package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bankapp.Transactions;

class TransactionsTest {
	// void addTransaction(String typeTransaction, double amount)

	@Test
	void testSizeTransaction() {
		Transactions testTransaction = new Transactions();

		testTransaction.addTransaction("Deposit", 10);

		assertEquals(1, testTransaction.getSize(), 0.01);
	}

	@Test
	void testListTransaction() {
		Transactions testTransaction = new Transactions();

		testTransaction.addTransaction("Deposit", 10);

		assertEquals("Deposit: 10.0", testTransaction.getTransactionList().get(0));
	}
}
