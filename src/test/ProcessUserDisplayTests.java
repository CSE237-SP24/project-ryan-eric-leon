package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.ProcessUserDisplay;

//Methodology to test output and input streams found here:
//https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
//https://www.baeldung.com/java-testing-system-out-println

public class ProcessUserDisplayTests {

	private OutputStream out;
	private ProcessUserDisplay processUserDisplay;

	@BeforeEach
	void setUp() {
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		processUserDisplay = new ProcessUserDisplay();
	}

	@Test
	void testShowOptions() {
		processUserDisplay.showOptions();

		assertEquals(
				"COMMANDS: options, accounts, balance, deposit, withdraw, transfer, insert, switch, change_password, history, quit",
				out.toString().trim());
	}

	@Test
	void testOptionsDescriptions() {
		processUserDisplay.optionsDescriptions();

		String testOutput = "options: check the command options again.\n"
				+ "accounts: check all the accounts in the bank app.\n"
				+ "balance: check the balance of your account.\n"
				+ "deposit, withdraw: deposit/withdraw money to/from your account.\n"
				+ "transfer: transfer money from your account to another account.\n" + "insert: create a new account.\n"
				+ "switch: switch between your accounts.\n" + "change_password: change the password of your account.\n"
				+ "history: display the transaction history of your account.\n" + "quit: terminate the bank app.";

		assertEquals(testOutput, out.toString().replace("\r\n", "\n").trim());
	}

	@Test
	void testByeMessage() {
		processUserDisplay.ByeMessage();

		assertEquals("Thanks for using our Bank App. Have a good day and bye!", out.toString().trim());
	}
}
