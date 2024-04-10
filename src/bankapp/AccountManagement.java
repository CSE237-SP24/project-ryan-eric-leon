package bankapp;

import java.util.HashMap;

public class AccountManagement{

	private HashMap<String, BankAccount> accounts;
    private BankAccount currentAccount;

    public AccountManagement() {
    	this.accounts = new HashMap<>();
        this.currentAccount = new BankAccount();
        this.accounts.put("root", currentAccount);//root as default account
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
}
    

