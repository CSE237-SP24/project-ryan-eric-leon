package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.Transactions;

class TransactionsTest {
	// void addTransaction(String typeTransaction, double amount)

	@Test
	void testSizeTransaction() {
		Transactions testTransaction = new Transactions();

		assertEquals(0, testTransaction.getSize(), 0);

		testTransaction.addTransaction("Deposit", 10);

		assertEquals(1, testTransaction.getSize(), 0.01);
	}

	@Test
	void testDepositListTransaction() {
		Transactions testTransaction = new Transactions();

		testTransaction.addTransaction("Deposit", 10);

		assertEquals("Deposit: 10.0", testTransaction.getTransactionList().get(0));
	}

	@Test
	void testWithdrawalListTransaction() {
		Transactions testTransaction = new Transactions();

		testTransaction.addTransaction("Withdrawal", 10);

		assertEquals("Withdrawal: 10.0", testTransaction.getTransactionList().get(0));
	}

	@Test
	void testPrintTransaction() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		Transactions testTransaction = new Transactions();

		testTransaction.addTransaction("Deposit", 10);

		testTransaction.printTransactions();
		assertEquals("Transactions: \nDeposit: 10.0", out.toString().replace("\r\n", "\n").trim());
	}
}
