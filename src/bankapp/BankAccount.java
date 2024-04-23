package bankapp;

import java.util.List;

public class BankAccount {

	private double balance;
	private Transactions transactions;
	private String password;

	// Constructors - not tested
	public BankAccount() {
		this.balance = 0;
		this.transactions = new Transactions();
		// a no-password account essentially has an empty string as password
		this.password = "";
	}

	// constructor for BankAccount with password
	public BankAccount(String password) {
		this.balance = 0;
		this.transactions = new Transactions();
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean checkPassword(String possiblePassword) {
		return this.password.equals(possiblePassword);
	}

	// public method doing some work - lots of tests
	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive.");
		}

		this.balance += amount;
		this.transactions.addTransaction("Deposit", amount);
	}

	// getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}

	public int getTransactionSize() {
		return this.transactions.getSize();
	}

	public List<String> getTransactionList() {
		return this.transactions.getTransactionList();
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive.");
		} else if (this.balance - amount < 0) {
			throw new IllegalArgumentException("Amount must be smaller or equal to balance.");
		}

		this.balance -= amount;
		this.transactions.addTransaction("Withdraw", amount);

	}

	public void transfer(double amount, BankAccount other) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive.");
		}

		this.withdraw(amount);
		other.deposit(amount);

	}

	public void getTransactionHistory() {
		this.transactions.printTransactions();
	}
}
