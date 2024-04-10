package bankapp;

public class ProcessTransaction {
    private AccountManagement accountManagement;

    public ProcessTransaction(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    public void processDeposit(double amount) {
        BankAccount currentAccount = accountManagement.getCurrentAccount();
        currentAccount.deposit(amount);
    }

    public void processWithdraw(double amount) {
        BankAccount currentAccount = accountManagement.getCurrentAccount();
        if (currentAccount.getBalance() >= amount) {
            currentAccount.withdraw(amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds for withdrawal.");
        }
    }

    public void processTransfer(double amount, String receiverAccountString) {
        BankAccount senderAccount = accountManagement.getCurrentAccount();
        BankAccount receiverAccount = accountManagement.getAccount(receiverAccountString);

        if (receiverAccount == null) {
            throw new IllegalArgumentException("Receiver account not found.");
        }

        if (senderAccount.getBalance() >= amount) {
            senderAccount.withdraw(amount);
            receiverAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
    }
}
