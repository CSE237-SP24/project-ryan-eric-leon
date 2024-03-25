package bankapp;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {

	private Scanner in;
	private HashMap<String, BankAccount> accounts;
	private BankAccount currentAccount;
	
	//not tested
	public static void main(String[] args) {
		System.out.println("Please let me know what do you want to do. You can enter 'details' for more detailed commands to use. ");
		Scanner in = new Scanner(System.in);
		String userInput = in.next();
		Menu mainMenu = new Menu();
		while(!userInput.equals("quit"))
		{
			if(userInput.equals("details"))
			{
				mainMenu.displayingOptions();
			}
			else if(userInput.equals("deposit"))
			{
				System.out.println("Please enter the amount of money you wish to deposit:");
				double amount = mainMenu.getValidUserInput();
				mainMenu.processingUserSelectionDeposit(amount);
			}
			else if(userInput.equals("withdraw"))
			{
				System.out.println("Please enter the amount of money you wish to withdraw:");
				double amount = mainMenu.getValidUserInput();
				mainMenu.processingUserSelectionWithdraw(amount);
			}
			else if(userInput.equals("transfer"))
			{
				System.out.println("Please enter the amount of money you wish to transfer:");
				double amount = mainMenu.getValidUserInput();
				System.out.println("Please enter the account you wish to receive money:");
				String receiverAccountString = in.next();
				BankAccount receiverAccount = mainMenu.accounts.get(receiverAccountString); 
				if (receiverAccount != null) 
				{
					mainMenu.getAccount().transfer(amount, receiverAccount);
					System.out.println("You successfully transferred" + amount + " to" + receiverAccount);
					System.out.println("Your new balance is: " + mainMenu.getAccount().getBalance());
				}
				else 
			    {
			        System.out.println("Account not found.");
			    }
			}
			else if(userInput.equals("switch"))
			{
				System.out.println("Please enter the name of the account you wish to switch to:");
			    String accountName = in.next(); 
			    BankAccount accountToSwitch = mainMenu.accounts.get(accountName); 
			    if (accountToSwitch != null) 
			    {
			        mainMenu.currentAccount = accountToSwitch; 
			        System.out.println("Successfully switched to: " + accountName);
			    } 
			    else 
			    {
			        System.out.println("Account not found.");
			    }
			}
			userInput = in.next();
		}
		in.close();
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
		System.out.println("If you wish to deposit money into your account, you can enter 'deposit' and follow the guidelines afterwards.");
		System.out.println("If you wish to withdraw money into your account, you can enter 'withdraw' and follow the guidelines afterwards.");
		System.out.println("If you wish to transfer money between accounts, you can enter 'transfer' and follow the guidelines afterwards.");
		System.out.println("If you wish to switch between your accounts, you can enter 'switch' and follow the guidelines afterwards.");
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
	public void processingUserSelectionDeposit(double amount) {
		currentAccount.deposit(amount);
		System.out.println("Your balance is now: " + currentAccount.getBalance());
	}
	
	public void processingUserSelectionWithdraw(double amount) {
		currentAccount.withdraw(amount);
		System.out.println("Your balance is now: " + currentAccount.getBalance());
	}
	
	public BankAccount getAccount() {
		return currentAccount;
	}
}
