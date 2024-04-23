package bankapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor {

	// reading: scanner
	private File accountsFile;
	private Scanner in;
	private PrintWriter out;

	// constructor
	public FileProcessor(String filePath) throws FileNotFoundException {
		this.accountsFile = new File(filePath);
		ensureFileExistence();
		this.in = new Scanner(this.accountsFile);
	}

	// creates the file if it doesn't
	private void ensureFileExistence() {
		if (!this.accountsFile.exists()) {
			try {
				File parentDir = this.accountsFile.getParentFile();
				if (parentDir != null) {
					// also make the parent directory if necessary
					parentDir.mkdirs();
				}
				this.accountsFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Error while generating file: " + e.getLocalizedMessage());
			}
		}
	}

	public HashMap<String, BankAccount> readAccounts() {
		HashMap<String, BankAccount> accounts = new HashMap<>();
		while (this.in.hasNextLine()) {
			String[] accountDetails = this.in.nextLine().split(",");
			if (accountDetails.length >= 2) {
				String accountName = accountDetails[0];
				double balance = Double.parseDouble(accountDetails[1]);
				// ensure no password accounts get initialized correctly
				String password = accountDetails.length > 2 ? accountDetails[2] : "";
				BankAccount account = new BankAccount(password);
				account.deposit(balance);
				accounts.put(accountName, account);
			}
		}
		return accounts;
	}

	public void writeAccounts(HashMap<String, BankAccount> accounts) throws FileNotFoundException {
		this.out = new PrintWriter(this.accountsFile);
		for (String accountName : accounts.keySet()) {
			BankAccount account = accounts.get(accountName);
			out.println(accountName + "," + account.getBalance() + "," + account.getPassword());
		}
		out.close();
	}

}
