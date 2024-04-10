package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.AccountManagement;
import bankapp.BankAccount;
import bankapp.Menu;
import bankapp.ProcessTransaction;

class MenuTests {

    private Menu menu;
    private AccountManagement accountManagement;
    private ProcessTransaction processTransaction;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        accountManagement = new AccountManagement();
        processTransaction = new ProcessTransaction(accountManagement);
    }

    @Test
    void testUserDeposit() {
        double depositAmount = 50;
        processTransaction.processDeposit(depositAmount);

        double actualBalance = accountManagement.getCurrentAccount().getBalance();
        assertEquals(depositAmount, actualBalance, 0.01);
    }

    @Test
    void testUserWithdraw() {
        double initialDeposit = 50;
        processTransaction.processDeposit(initialDeposit);

        double withdrawAmount = 30;
        processTransaction.processWithdraw(withdrawAmount);

        double expectedBalance = initialDeposit - withdrawAmount;
        double actualBalance = accountManagement.getCurrentAccount().getBalance();
        assertEquals(expectedBalance, actualBalance, 0.01);
    }

    @Test
    void testUserTransfer() {
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

 
}