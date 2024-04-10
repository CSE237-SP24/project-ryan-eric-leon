package bankapp;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class AccountManagement {

	private FileProcessor fileProcessor;
	private HashMap<String, BankAccount> accounts;
	private BankAccount currentAccount;

	public AccountManagement() {
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

	public void insertAccount(String name) {
		if (accounts.containsKey(name)) {
			throw new IllegalArgumentException("This account already exists.");
		}
		BankAccount bankAccount = new BankAccount();
		accounts.put(name, bankAccount);
	}

	public void switchAccount(String name) {
		if (!accounts.containsKey(name)) {
			throw new IllegalArgumentException("There is no account with that name.");
		}
		currentAccount = accounts.get(name);
	}

	public BankAccount getCurrentAccount() {
		return currentAccount;
	}

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
