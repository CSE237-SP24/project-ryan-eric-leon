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

	// Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		this.currentAccount = new BankAccount();
		try {
			this.fileProcessor = new FileProcessor("accounts_file/accounts.txt");
			this.accounts = fileProcessor.readAccounts();
		} catch (FileNotFoundException e) {
			// didn't found file so use empty hash map
			this.accounts = new HashMap<>();
		}
		// default to a special account 'root'
		// it will start at 0 balance each time the program runs
		this.accounts.put("root", currentAccount);
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

	public void saveAccounts() {
		try {
			fileProcessor.writeAccounts(accounts);
			System.out.println("Finished saving accounts info.");
		} catch (FileNotFoundException e) {
			System.err.println("Didn't found file to write to.");
		}
	}
	
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
