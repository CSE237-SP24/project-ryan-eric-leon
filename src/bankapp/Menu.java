package bankapp;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
	private Scanner in;
	private AccountManagement accountManagement;
	private ProcessTransaction processTransaction;
	private UserInputValidity userInputValidity;

	public Menu() {
		this.in = new Scanner(System.in);
		this.accountManagement = new AccountManagement("accounts_file/accounts.txt");
		this.processTransaction = new ProcessTransaction(accountManagement);
		// set the single transaction limit to be less than a million dollars
		this.userInputValidity = new UserInputValidity(in, 1000000.0);
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.start();

		menu.in.close();
		// try to save the accounts info after user quits
		try {
			AccountManagement accountManagement = menu.getAccountManagement();
			FileProcessor fileProcessor = accountManagement.getFileProcessor();
			HashMap<String, BankAccount> accounts = accountManagement.getAllAccounts();
			fileProcessor.writeAccounts(accounts);
			System.out.println("Finished saving accounts info.");
		} catch (FileNotFoundException e) {
			System.err.println("Didn't found file to write to.");
		}
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
			case "balance":
				showBalance();
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

	private void showBalance() {
		System.out.println("Current balance: " + accountManagement.getCurrentAccount().getBalance());
	}

	private void processTransaction(String type) {
		try {
			System.out.println("Please enter the amount of money you wish to " + type + ": ");
			double amount = userInputValidity.getValidUserInput();
			if (type.equalsIgnoreCase("deposit")) {
				processTransaction.processDeposit(amount);
			} else if (type.equalsIgnoreCase("withdraw")) {
				processTransaction.processWithdraw(amount);
			}
			System.out.println("Transaction complete. Your current balance is: "
					+ accountManagement.getCurrentAccount().getBalance());
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid value! " + e.getLocalizedMessage() + " Transaction failed.");
		}
	}

	private void processTransfer() {
		try {
			System.out.println("Please enter the amount of money you wish to transfer: ");
			double amount = userInputValidity.getValidUserInput();
			System.out.println("Please enter the account you wish to transfer money to: ");
			String receiverAccountString = in.next();
			processTransaction.processTransfer(amount, receiverAccountString);
			System.out.println("Transfer complete. Your current balance is: "
					+ accountManagement.getCurrentAccount().getBalance());
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid value! " + e.getLocalizedMessage() + " Transfer failed.");
		}
	}

	private void processInsert() {
		try {
			System.out.println("Please enter the name of the account you wish to create: ");
			String newAccountName = in.next();
			accountManagement.insertAccount(newAccountName);
			System.out.println("Account created successfully.");
		} catch (IllegalArgumentException e) {
			System.err.println(e.getLocalizedMessage() + " Insert new account failed.");
		}
	}

	private void processSwitch() {
		try {
			System.out.println("Please enter the name of the account you wish to switch to: ");
			String accountName = in.next();
			accountManagement.switchAccount(accountName);
			System.out.println("Switched to account: " + accountName);
			showBalance();
		} catch (IllegalArgumentException e) {
			System.err.println(e.getLocalizedMessage() + " Switch account failed.");
		}
	}

	private void processHistory() {
		accountManagement.getCurrentAccount().getTransactionHistory();
	}

	public AccountManagement getAccountManagement() {
		return accountManagement;
	}
}
