package bankapp;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Menu {
	private AccountManagement accountManagement;
	private ProcessUserInput processUserInput;
	private ProcessUserDisplay processUserDisplay;
	private ProcessTransaction processTransaction;

	public Menu() {
		this.processUserInput = new ProcessUserInput();
		this.processUserDisplay = new ProcessUserDisplay();
		this.accountManagement = new AccountManagement("accounts_file/accounts.txt");
		this.processTransaction = new ProcessTransaction(accountManagement);
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.run();
		// try to save the accounts info after user quits
		menu.saveAccounts();
	}

	public void saveAccounts() {
		try {
			FileProcessor fileProcessor = accountManagement.getFileProcessor();
			HashMap<String, BankAccount> accounts = accountManagement.getAllAccounts();
			fileProcessor.writeAccounts(accounts);
			System.out.println("Finished saving accounts info.");
		} catch (FileNotFoundException e) {
			System.err.println("Didn't found file to write to.");
		}
	}

	public void run() {
		processUserDisplay.welcomeMessage();
		processUserDisplay.showOptions();
		processUserDisplay.optionsDescriptions();

		String command = processUserInput.getInput();
		while (!command.equalsIgnoreCase("quit")) {
			processCommand(command);
			System.out.println();
			System.out.println("Please let me know what do you want to do.");
			command = processUserInput.getInput();
		}

		processUserDisplay.ByeMessage();
		processUserInput.close();
	}

	private void processCommand(String command) {
		switch (command.toLowerCase()) {
		case "options":
			processUserDisplay.showOptions();
			break;
		case "accounts":
			displayAccounts();
			break;
		case "balance":
			processUserDisplay.showInfo("Current balance: " + accountManagement.getCurrentBalance());
			break;
		case "deposit":
			tryDeposit();
			break;
		case "withdraw":
			tryWithdraw();
			break;
		case "transfer":
			tryTransfer();
			break;
		case "insert":
			tryInsert();
			break;
		case "switch":
			trySwitch();
			break;
		case "change_password":
			tryChangePassword();
			break;
		case "history":
			tryDisplayHistory();
			break;
		default:
			processUserDisplay.showError("Invalid command. Please try again.");
			break;
		}
	}

	private void tryDeposit() {
		processUserDisplay.showInfo("Please enter the amount to deposit:");
		double limit = 1000000.0; // single transaction limit < a million
		double amount = processUserInput.getValidAmount(limit);

		try {
			processTransaction.processDeposit(amount);
			processUserDisplay.showInfo("Deposit successful. New balance: " + accountManagement.getCurrentBalance());
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error during deposit: " + e.getMessage());
		}
	}

	private void tryWithdraw() {
		processUserDisplay.showInfo("Please enter the amount to withdraw:");
		double limit = accountManagement.getCurrentBalance();
		double amount = processUserInput.getValidAmount(limit);

		try {
			processTransaction.processWithdraw(amount);
			processUserDisplay.showInfo("Withdrawal successful. New balance: " + accountManagement.getCurrentBalance());
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error during withdrawal: " + e.getMessage());
		}
	}

	private void tryTransfer() {
		processUserDisplay.showInfo("Please enter the amount to transfer:");
		double limit = accountManagement.getCurrentBalance();
		double amount = processUserInput.getValidAmount(limit);

		processUserDisplay.showInfo("Please enter the recipient's account name:");
		String recipient = processUserInput.getInput();

		try {
			processTransaction.processTransfer(amount, recipient);
			processUserDisplay.showInfo("Transfer successful. New balance: " + accountManagement.getCurrentBalance());
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error during transfer: " + e.getMessage());
		}
	}

	private void displayAccounts() {
		processUserDisplay.showInfo("Existing accounts include: " + accountManagement.getAllAccounts().keySet());
	}

	private void tryInsert() {
		processUserDisplay.showInfo("Please enter a new account name:");
		String accountName = processUserInput.getInput();
		processUserDisplay.showInfo("Enter a password for the account (press enter for no password):");
		String password = processUserInput.getInput();

		try {
			if (password.isEmpty()) {
				accountManagement.insertAccount(accountName);
				processUserDisplay.showInfo("Account created without password.");
			} else {
				accountManagement.insertAccountWithPassword(accountName, password);
				processUserDisplay.showInfo("Account created with password.");
			}
			displayAccounts();
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error creating account: " + e.getMessage());
		}
	}

	private void trySwitch() {
		processUserDisplay.showInfo("Please enter the account name to switch to:");
		String accountName = processUserInput.getInput();
		processUserDisplay.showInfo("Enter the account password (press enter for no password):");
		String password = processUserInput.getInput();

		try {
			accountManagement.switchAccountWithPassword(accountName, password);
			processUserDisplay.showInfo("Switched to account: " + accountName);
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error switching accounts: " + e.getMessage());
		}
	}

	private void tryChangePassword() {
		processUserDisplay.showInfo("Please enter old account password (press enter for no password):");
		String oldPassword = processUserInput.getInput();
		processUserDisplay.showInfo("Please enter new account password (press enter for no password):");
		String newPassword = processUserInput.getInput();

		try {
			accountManagement.changeAccountPassword(oldPassword, newPassword);
			processUserDisplay.showInfo("Account password updated.");
		} catch (IllegalArgumentException e) {
			processUserDisplay.showError("Error changing password: " + e.getMessage());
		}
	}

	private void tryDisplayHistory() {
		accountManagement.getCurrentAccount().printTransactionHistory();
	}
}
