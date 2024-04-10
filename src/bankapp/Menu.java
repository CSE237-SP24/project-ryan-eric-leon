package bankapp;

import java.util.Scanner;

public class Menu {
    private Scanner in;
    private AccountManagement accountManagement;
    private ProcessTransaction processTransaction;
    private UserInputValidity userInputValidity;

    public Menu() {
        this.in = new Scanner(System.in);
        this.accountManagement = new AccountManagement();
        this.processTransaction = new ProcessTransaction(accountManagement);
        this.userInputValidity = new UserInputValidity(in);
    }
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }

    public void start() {
        System.out.println(
                "Please let me know what do you want to do. You can enter 'details' for more detailed commands to use or 'quit' to exit.");
        String userInput = in.next();
        inputProcessLoop(userInput);
    }
    
    private void inputProcessLoop(String userInput) {
        while (!userInput.equalsIgnoreCase("quit")) {
            switch (userInput.toLowerCase()) {
                case "details":
                    displayingOptions();
                    break;
                case "deposit":
                    processTransaction("deposit");
                    break;
                case "withdraw":
                    processTransaction("withdraw");
                    break;
                case "transfer":
                    processTransfer();
                    break;
                case "insert":
                    processInsert();
                    break;
                case "switch":
                    processSwitch();
                    break;
                case "history":
                	processHistory();
                	break;
                default:
                    System.err.println("Invalid command. Please try again.");
                    break;
            }
            System.out.println();
            System.out.println("Please let me know what do you want to do.");
            userInput = in.next();
        }
        System.out.println("Bye.");
    }
    
    private void displayingOptions() {
        System.out.println("If you wish to deposit money into your account, you can enter 'deposit'.");
        System.out.println("If you wish to withdraw money from your account, you can enter 'withdraw'.");
        System.out.println("If you wish to transfer money between accounts, you can enter 'transfer'.");
        System.out.println("If you wish to make a new account, you can enter 'insert'.");
        System.out.println("If you wish to switch between your accounts, you can enter 'switch'.");
        System.out.println("If you wish to get transaction history, you can enter 'history'.");
        System.out.println("Existing accounts include: " + accountManagement.getAllAccounts().keySet());
    }

    private void processTransaction(String type) {
        System.out.println("Please enter the amount of money you wish to " + type + ": ");
        double amount = userInputValidity.getValidUserInput();
        if (type.equalsIgnoreCase("deposit")) {
            processTransaction.processDeposit(amount);
        } else if (type.equalsIgnoreCase("withdraw")) {
            processTransaction.processWithdraw(amount);
        }
        System.out.println("Transaction complete. Your current balance is: " + accountManagement.getCurrentAccount().getBalance());
    }

    private void processTransfer() {
        System.out.println("Please enter the amount of money you wish to transfer: ");
        double amount = userInputValidity.getValidUserInput();
        System.out.println("Please enter the account you wish to transfer money to: ");
        String receiverAccountString = in.next();
        processTransaction.processTransfer(amount, receiverAccountString);
        System.out.println("Transfer complete. Your current balance is: " + accountManagement.getCurrentAccount().getBalance());
    }

    private void processInsert() {
        System.out.println("Please enter the name of the account you wish to create: ");
        String newAccountName = in.next();
        accountManagement.insertAccount(newAccountName);
        System.out.println("Account created successfully.");
    }

    private void processSwitch() {
        System.out.println("Please enter the name of the account you wish to switch to: ");
        String accountName = in.next();
        accountManagement.switchAccount(accountName);
        System.out.println("Switched to account: " + accountName);
    }
    
    private void processHistory() {
    	accountManagement.getCurrentAccount().getTransactionHistory();
    }
}
