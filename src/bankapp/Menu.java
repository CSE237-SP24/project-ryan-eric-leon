package bankapp;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private HashMap<String, BankAccount> accounts;
	private BankAccount currentAccount;
	
	//not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
		double amount = mainMenu.getValidUserInput();
		mainMenu.processingUserSelection(amount);
	}
	
	//Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		this.currentAccount = new BankAccount();
		this.accounts = new HashMap<>();
		this.accounts.put("root", currentAccount);
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("How much money do you want to deposit?");
	}
	
	public void switchAccounts(String name) {
		if (!(this.accounts.containsKey(name))) {
			System.out.println("There is no account with that name.");
			return;
		}

		this.currentAccount = this.accounts.get(name);;
	}
	
	public void insertAccount(String name, double balance) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(balance);
		this.accounts.put(name, bankAccount);
		
	}
	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	public double getValidUserInput() {
		double amount = in.nextDouble();
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}
	
	//Does work - needs tests
	public void processingUserSelection(double amount) {
		currentAccount.deposit(amount);
		System.out.println("Your balance is now: " + currentAccount.getBalance());
	}
	
	public BankAccount getAccount() {
		return currentAccount;
	}
}
