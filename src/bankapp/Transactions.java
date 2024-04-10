package bankapp;

import java.util.LinkedList;
import java.util.List;

public class Transactions {
	List<String> transactionHistory;
	
	public Transactions() {
		this.transactionHistory = new LinkedList<>();
	}
	
	
	public void addTransaction(String typeTransaction, double amount) {
		this.transactionHistory.add(typeTransaction + ": " + amount);
		
	}
	
	public void printTransactions() {
		if (this.transactionHistory.isEmpty()) {
			System.out.println("No transaction history");
			return;
		}
		System.out.println("Transactions: ");
		for (String transaction : this.transactionHistory) {
			System.out.println(transaction);
		}
	}
	
	public void remove() {
		int removeIndex = this.transactionHistory.size() - 1;
		this.transactionHistory.remove(removeIndex);
		this.transactionHistory.remove(removeIndex - 1);
	}
	
	public int getSize() {
		return this.transactionHistory.size();
	}
	
	public List<String> getTransactionList(){
		return this.transactionHistory;
	}
}
