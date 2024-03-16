package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
	
	public void withdraw(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		else if(this.balance - amount < 0) {
			throw new IllegalArgumentException("Amount must be smaller or equal to balance");
		}
		this.balance -= amount;
		
	}
	
	public void transfer(double amount, BankAccount other) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		
		this.withdraw(amount);
		other.deposit(amount);
		
	}
}
