package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.FileProcessor;

class FileProcessorTests {

	@Test
	void testReadEmptyAccounts() throws FileNotFoundException {
		FileProcessor fileProcessor = new FileProcessor("accounts_file/test_empty.txt");

		HashMap<String, BankAccount> accounts = fileProcessor.readAccounts();

		assertTrue(accounts.isEmpty());
	}

	@Test
	void testReadWrite() throws FileNotFoundException {
		FileProcessor fileProcessor = new FileProcessor("accounts_file/test_readwrite.txt");
		HashMap<String, BankAccount> accountsForWriting = new HashMap<>();
		BankAccount t1 = new BankAccount();
		BankAccount t2 = new BankAccount();

		accountsForWriting.put("t1", t1);
		accountsForWriting.put("t2", t2);
		fileProcessor.writeAccounts(accountsForWriting);
		HashMap<String, BankAccount> accounts = fileProcessor.readAccounts();

		// if readAcounts and writeAccounts work properly
		// readAccounts should contain exactly 2 key-value pairs
		assertEquals(2, accounts.size(), 0.01);
	}

	@Test
	void testReadWritePassword() throws FileNotFoundException {
		FileProcessor fileProcessor = new FileProcessor("accounts_file/test_readwrite_password.txt");
		HashMap<String, BankAccount> accountsForWriting = new HashMap<>();
		BankAccount t1 = new BankAccount("password_1");
		BankAccount t2 = new BankAccount("password_2");

		accountsForWriting.put("t1", t1);
		accountsForWriting.put("t2", t2);
		fileProcessor.writeAccounts(accountsForWriting);
		HashMap<String, BankAccount> accounts = fileProcessor.readAccounts();

		// if readAcounts and writeAccounts work properly
		// readAccounts should contain exactly 2 key-value pairs
		assertEquals(2, accounts.size(), 0.01);
	}

	@Test
	void testReadWriteMixed() throws FileNotFoundException {
		FileProcessor fileProcessor = new FileProcessor("accounts_file/test_readwrite_mixed.txt");
		HashMap<String, BankAccount> accountsForWriting = new HashMap<>();
		BankAccount t1 = new BankAccount("password_1");
		BankAccount t2 = new BankAccount();

		accountsForWriting.put("t1", t1);
		accountsForWriting.put("t2", t2);
		fileProcessor.writeAccounts(accountsForWriting);
		HashMap<String, BankAccount> accounts = fileProcessor.readAccounts();

		// if readAcounts and writeAccounts work properly
		// readAccounts should contain exactly 2 key-value pairs
		assertEquals(2, accounts.size(), 0.01);
	}

}
