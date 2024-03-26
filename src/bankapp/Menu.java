package bankapp;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private HashMap<String, BankAccount> accounts;
	private BankAccount currentAccount;
	private FileProcessor fileProcessor;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();

		System.out.println(
				"Please let me know what do you want to do. You can enter 'details' for more detailed commands to use or 'quit' to exit.");
		String userInput = mainMenu.in.next();
		mainMenu.inputProcessLoop(userInput);
		mainMenu.in.close();

		try {
			mainMenu.getFileProcessor().writeAccounts(mainMenu.accounts);
		} catch (FileNotFoundException e) {
			System.err.println("Didn't found file to write to.");
		}
	}

	// Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		this.currentAccount = new BankAccount();
		try {
			this.fileProcessor = new FileProcessor("accounts_file/accounts.txt");
			this.accounts = getFileProcessor().readAccounts();
		} catch (FileNotFoundException e) {
			// didn't found file so use empty hash map
			this.accounts = new HashMap<>();
		}
		// default to a special account 'root'
		// it will start at 0 balance each time the program runs
		this.accounts.put("root", currentAccount);
	}

	private void inputProcessLoop(String userInput) {
		while (!userInput.equals("quit")) {
			switch (userInput) {
			case "details":
				this.displayingOptions();
				break;
			case "deposit":
				System.out.println("Please enter the amount of money you wish to deposit: ");
				double depositAmount = this.getValidUserInput();
				this.processDeposit(depositAmount);
				break;
			case "withdraw":
				System.out.println("Please enter the amount of money you wish to withdraw: ");
				double withdrawAmount = this.getValidUserInput();
				this.processWithdraw(withdrawAmount);
				break;
			case "transfer":
				System.out.println("Please enter the amount of money you wish to transfer: ");
				double transferAmount = this.getValidUserInput();
				System.out.println("Please enter the account you wish to transfer money to: ");
				String receiverAccountString = this.in.next();
				processTransfer(transferAmount, receiverAccountString);
				break;
			case "insert":
				System.out.println("Please enter the name of the account you wish to make: ");
				String newAccountName = this.in.next();
				processInsert(newAccountName);
				break;
			case "switch":
				System.out.println(
						"Please enter the name of the account you wish to switch to (default account is 'root'): ");
				String accountName = this.in.next();
				processSwitch(accountName);
				break;
			default:
				System.err.println("Invalid command. Please try again.");
				break;
			}
			System.out.println(); // extra new line for better visualization
			System.out.println("Please let me know what do you want to do.");
			userInput = this.in.next();
		}
		System.out.println("Bye.");
	}

	// Code that just displays stuff - no tests needed
	private void displayingOptions() {
		System.out.println(
				"If you wish to deposit money into your account, you can enter 'deposit' and follow the guidelines afterwards.");
		System.out.println(
				"If you wish to withdraw money into your account, you can enter 'withdraw' and follow the guidelines afterwards.");
		System.out.println(
				"If you wish to transfer money between accounts, you can enter 'transfer' and follow the guidelines afterwards.");
		System.out.println(
				"If you wish to make a new account, you can enter 'insert' and follow the guidelines afterwards.");
		System.out.println(
				"If you wish to switch between your accounts, you can enter 'switch' and follow the guidelines afterwards.");
		System.out.println("Existing accounts include: " + this.accounts.keySet());
	}

	public void insertAccount(String name) {
		if (this.accounts.containsKey(name)) {
			throw new IllegalArgumentException("This account already exists.");
		}
		BankAccount bankAccount = new BankAccount();
		this.accounts.put(name, bankAccount);
	}

	public void switchAccount(String name) {
		if (!(this.accounts.containsKey(name))) {
			throw new IllegalArgumentException("There is no account with that name.");
		}
		this.currentAccount = this.accounts.get(name);
	}

	// ensures user input
	private double getValidUserInput() {
		double amount = 0; // has to initialize it to a value to avoid error message at return
		boolean invalid = true; // assume invalid at beginning to enter the loop

		while (invalid) {
			try {
				amount = this.in.nextDouble();
				if (amount < 0) {
					System.err.println("Invalid value! Please enter a non-negative number.");
				} else {
					invalid = false;
				}
			} catch (InputMismatchException e) {
				System.err.println("Invalid value! Please enter a non-negative number.");
				this.in.next();
			}
		}

		return amount;
	}

	public void processDeposit(double amount) {
		try {
			this.currentAccount.deposit(amount);
			System.out.println("Your balance is now: " + this.currentAccount.getBalance());
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid value! " + e.getLocalizedMessage() + " Deposit failed.");
		}
	}

	public void processWithdraw(double amount) {
		try {
			this.currentAccount.withdraw(amount);
			System.out.println("Your balance is now: " + this.currentAccount.getBalance());
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid value! " + e.getLocalizedMessage() + " Withdraw failed.");
		}
	}

	public void processTransfer(double amount, String receiverAccountString) {
		try {
			BankAccount receiverAccount = this.accounts.get(receiverAccountString);
			if (receiverAccount == null) {
				throw new IllegalArgumentException("Receiver account not found.");
			}
			this.getAccount().transfer(amount, receiverAccount);
			System.out.println("You successfully transferred " + amount + " to " + receiverAccountString);
			System.out.println("Your balance is now: " + this.getAccount().getBalance());
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid value! " + e.getLocalizedMessage() + " Transfer failed.");
		}
	}

	public void processInsert(String accountName) {
		try {
			insertAccount(accountName);
			System.out.println("Successfully created new account: " + accountName);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage() + " Insert new account failed.");
		}
	}

	public void processSwitch(String accountName) {
		try {
			switchAccount(accountName);
			System.out.println("Switched to: " + accountName);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getLocalizedMessage() + " Switch account failed.");
		}
	}

	public BankAccount getAccount() {
		return this.currentAccount;
	}

	public HashMap<String, BankAccount> getAllAccounts() {
		return this.accounts;
	}

	public FileProcessor getFileProcessor() {
		return this.fileProcessor;
	}

}
