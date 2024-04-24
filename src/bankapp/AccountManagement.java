package bankapp;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class AccountManagement {

	private FileProcessor fileProcessor;
	private HashMap<String, BankAccount> accounts;
	private BankAccount currentAccount;

	public AccountManagement() {
		this.currentAccount = new BankAccount();
		this.accounts = new HashMap<>();
	}

	public AccountManagement(String pathToFile) {
		this.currentAccount = new BankAccount();
		try {
			this.fileProcessor = new FileProcessor(pathToFile);
			this.accounts = fileProcessor.readAccounts();
		} catch (FileNotFoundException e) {
			// didn't found file so use empty hash map
			this.accounts = new HashMap<>();
		}
		// default to a special account 'root' which has no password
		// it will start at 0 balance each time the program runs
		this.accounts.put("root", currentAccount);
	}

	public void insertAccount(String name) {
		if (accounts.containsKey(name)) {
			throw new IllegalArgumentException("This account already exists.");
		}
		BankAccount bankAccount = new BankAccount();
		accounts.put(name, bankAccount);
	}

	public void insertAccountWithPassword(String name, String password) {
		if (accounts.containsKey(name)) {
			throw new IllegalArgumentException("This account already exists.");
		}
		BankAccount bankAccount = new BankAccount(password);
		accounts.put(name, bankAccount);
	}

	public void switchAccount(String name) {
		if (!accounts.containsKey(name)) {
			throw new IllegalArgumentException("There is no account with that name.");
		}
		currentAccount = accounts.get(name);
	}

	public void switchAccountWithPassword(String name, String password) {
		if (!accounts.containsKey(name)) {
			throw new IllegalArgumentException("There is no account with that name.");
		}

		BankAccount targetAccount = accounts.get(name);
		if (!targetAccount.checkPassword(password)) {
			throw new IllegalArgumentException("The password does not match.");
		}

		currentAccount = targetAccount;
	}

	public void changeAccountPassword(String oldPassword, String newPassword) {
		currentAccount.setPassword(oldPassword, newPassword);
	}

	public BankAccount getCurrentAccount() {
		return currentAccount;
	}

	public double getCurrentBalance() {
		return currentAccount.getBalance();
	}

	// getAccount() is only used to get the receiver's account
	// when trying to transfer money, so we shouldn't check password here
	public BankAccount getAccount(String accountName) {
		return accounts.get(accountName);
	}

	public HashMap<String, BankAccount> getAllAccounts() {
		return accounts;
	}

	public FileProcessor getFileProcessor() {
		return this.fileProcessor;
	}
}
