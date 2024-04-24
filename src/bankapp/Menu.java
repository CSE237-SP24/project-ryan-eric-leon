package bankapp;

public class Menu {
    private ProcessUserInput processUserInput;
    private ProcessUserDisplay processUserDisplay;
    private AccountManagement accountManagement;

    public Menu() {
        this.processUserInput = new ProcessUserInput();
        this.processUserDisplay = new ProcessUserDisplay();
        this.accountManagement = new AccountManagement("accounts_file/accounts.txt");
    }

    public void start() {
        processUserDisplay.WelcomeMessage();
        processUserDisplay.showOptions();
        processUserDisplay.optionsDescriptions();

        String command;
        while (!(command = processUserInput.getInput()).equalsIgnoreCase("quit")) {
            processCommand(command);
            processUserDisplay.showOptions();
        }

        processUserDisplay.ByeMessage();
        processUserInput.close();
    }

    private void processCommand(String command) {
        switch (command.toLowerCase()) {
            case "balance":
                processUserDisplay.showInfo("Current balance: " + accountManagement.getCurrentAccount().getBalance());
                break;
            case "deposit":
                processDeposit();
                break;
            case "withdraw":
                processWithdraw();
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
                processUserDisplay.showError("Invalid command. Please try again.");
                break;
        }
    }

    private void processDeposit() {
        processUserDisplay.showInfo("Please enter the amount to deposit:");
        double amount = processUserInput.getValidAmount(1000000.0);  // Assuming a limit is set
        try {
            accountManagement.getCurrentAccount().deposit(amount);
            processUserDisplay.showInfo("Deposit successful. New balance: " + accountManagement.getCurrentAccount().getBalance());
        } catch (IllegalArgumentException e) {
            processUserDisplay.showError("Error during deposit: " + e.getMessage());
        }
    }

    private void processWithdraw() {
        processUserDisplay.showInfo("Please enter the amount to withdraw:");
        double amount = processUserInput.getValidAmount(accountManagement.getCurrentAccount().getBalance());
        try {
            accountManagement.getCurrentAccount().withdraw(amount);
            processUserDisplay.showInfo("Withdrawal successful. New balance: " + accountManagement.getCurrentAccount().getBalance());
        } catch (IllegalArgumentException e) {
            processUserDisplay.showError("Error during withdrawal: " + e.getMessage());
        }
    }

    private void processTransfer() {
        processUserDisplay.showInfo("Please enter the amount to transfer:");
        double amount = processUserInput.getValidAmount(accountManagement.getCurrentAccount().getBalance());
        processUserDisplay.showInfo("Please enter the recipient's account name:");
        String recipient = processUserInput.getInput();
        try {
            BankAccount recipientAccount = accountManagement.getAccount(recipient);
            if (recipientAccount == null) {
                throw new IllegalArgumentException("Recipient account not found.");
            }
            accountManagement.getCurrentAccount().withdraw(amount);
            recipientAccount.deposit(amount);
            processUserDisplay.showInfo("Transfer successful. New balance: " + accountManagement.getCurrentAccount().getBalance());
        } catch (IllegalArgumentException e) {
            processUserDisplay.showError("Error during transfer: " + e.getMessage());
        }
    }

    private void processInsert() {
        processUserDisplay.showInfo("Please enter a new account name:");
        String accountName = processUserInput.getInput();
        processUserDisplay.showInfo("Enter a password for the account (leave blank for no password):");
        String password = processUserInput.getInput();
        try {
            if (password.isEmpty()) {
                accountManagement.insertAccount(accountName);
            } else {
                accountManagement.insertAccountWithPassword(accountName, password);
            }
            processUserDisplay.showInfo("Account created successfully.");
        } catch (IllegalArgumentException e) {
            processUserDisplay.showError("Error creating account: " + e.getMessage());
        }
    }

    private void processSwitch() {
        processUserDisplay.showInfo("Please enter the account name to switch to:");
        String accountName = processUserInput.getInput();
        processUserDisplay.showInfo("Enter the account password (leave blank for no password):");
        String password = processUserInput.getInput();
        try {
            accountManagement.switchAccountWithPassword(accountName, password);
            processUserDisplay.showInfo("Switched to account: " + accountName);
        } catch (IllegalArgumentException e) {
            processUserDisplay.showError("Error switching accounts: " + e.getMessage());
        }
    }

    private void processHistory() {
        accountManagement.getCurrentAccount().getTransactionHistory();
    }
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}
