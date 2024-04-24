package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.ProcessUserDisplay;

//Methodology to test output and input streams found here:
//https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
//https://www.baeldung.com/java-testing-system-out-println


public class ProcessUserDisplayTests {
	
	@Test
	void testShowOptions() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		ProcessUserDisplay.showOptions();
		
		assertEquals("Commands: balance, deposit, withdraw, transfer, insert, switch, history, quit", out.toString().trim());
	}
	
	@Test
	void testOptionsDescriptions() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		ProcessUserDisplay.optionsDescriptions();
		
		String testOutput = "balance: checking the balance of your account."
				+ "\ndeposit, withdraw: deposit/withdraw money to/from your account."
				+ "\ntransfer: transfer money from your account to another account."
				+ "\ninsert: creating a new account."
				+ "\nswitch: switching between your accounts."
				+ "\nhistory: displaying the transaction history of your account."
				+ "\nquit: terminating the commands.";
		
		assertEquals(testOutput, out.toString().trim());
	}
	
	@Test
	void testByeMessage() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		ProcessUserDisplay.ByeMessage();
		
		assertEquals("Thanks for using our App. Have a good day and bye!", out.toString().trim());
	}
}
