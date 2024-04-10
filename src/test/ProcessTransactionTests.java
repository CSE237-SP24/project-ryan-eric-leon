package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.AccountManagement;
import bankapp.ProcessTransaction;

class ProcessTransactionTests {

	private AccountManagement accountManagement;
	private ProcessTransaction processTransaction;

	@BeforeEach
	void setUp() {
		accountManagement = new AccountManagement();
		processTransaction = new ProcessTransaction(accountManagement);
	}

	@Test
	void testProcessDeposit() {
		double depositAmount = 50;
		processTransaction.processDeposit(depositAmount);

		double actualBalance = accountManagement.getCurrentAccount().getBalance();
		assertEquals(depositAmount, actualBalance, 0.01);
	}

	@Test
	void testProcessWithdraw() {
		double initialDeposit = 50;
		processTransaction.processDeposit(initialDeposit);

		double withdrawAmount = 30;
		processTransaction.processWithdraw(withdrawAmount);

		double expectedBalance = initialDeposit - withdrawAmount;
		double actualBalance = accountManagement.getCurrentAccount().getBalance();
		assertEquals(expectedBalance, actualBalance, 0.01);
	}

	@Test
	void testProcessWithdrawInsufficientFund() {
		double withdrawAmount = 30;

		try {
			processTransaction.processWithdraw(withdrawAmount);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testProcessTransfer() {
		double initialDeposit = 50;
		processTransaction.processDeposit(initialDeposit);
		String receiverAccountName = "Bob";
		accountManagement.insertAccount(receiverAccountName);
		double transferAmount = 30;
		processTransaction.processTransfer(transferAmount, receiverAccountName);

		double senderExpectedBalance = initialDeposit - transferAmount;
		double actualSenderBalance = accountManagement.getCurrentAccount().getBalance();
		assertEquals(senderExpectedBalance, actualSenderBalance, 0.01);

		double receiverExpectedBalance = transferAmount;
		double actualReceiverBalance = accountManagement.getAccount(receiverAccountName).getBalance();
		assertEquals(receiverExpectedBalance, actualReceiverBalance, 0.01);
	}

	@Test
	void testProcessTransferInsufficientFund() {
		String receiverAccountName = "Bob";
		accountManagement.insertAccount(receiverAccountName);
		double transferAmount = 30;

		try {
			processTransaction.processTransfer(transferAmount, receiverAccountName);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testProcessTransferUnknownReceiver() {
		double initialDeposit = 50;
		processTransaction.processDeposit(initialDeposit);
		String receiverAccountName = "Bob";
		double transferAmount = 30;

		try {
			processTransaction.processTransfer(transferAmount, receiverAccountName);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

}