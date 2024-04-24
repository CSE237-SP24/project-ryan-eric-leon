package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import bankapp.ProcessUserInput;

//Methodology to test output and input streams found here:
//https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
//https://www.baeldung.com/java-testing-system-out-println

class ProcessUserInputTests {

	@Test
	void testGetValidAmountWithCorrectAmount() {
		String input = "5.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		ProcessUserInput processUserInput = new ProcessUserInput();
		assertEquals(5.0, processUserInput.getValidAmount(10.0));
		processUserInput.close();
	}

	@Test
	void testGetValidAmountWithoutValidNumber() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String input = "hello\n5.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());

		System.setIn(in);
		ProcessUserInput processUserInput = new ProcessUserInput();
		processUserInput.getValidAmount(10.0);
		assertEquals("Invalid input! Please enter a valid number:", out.toString().trim());
		processUserInput.close();
	}

	@Test
	void testGetValidAmountWithNegativeNumber() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String input = "-10.0\n5.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());

		System.setIn(in);
		ProcessUserInput processUserInput = new ProcessUserInput();
		processUserInput.getValidAmount(10.0);
		assertEquals("Invalid value! Please enter a non-negative number:", out.toString().trim());
		processUserInput.close();
	}

	@Test
	void testGetValidAmountHigherThanLimit() {
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		String input = "10.0\n5.0";
		InputStream in = new ByteArrayInputStream(input.getBytes());

		System.setIn(in);
		ProcessUserInput processUserInput = new ProcessUserInput();
		processUserInput.getValidAmount(10.0);
		assertEquals("Invalid value! Please enter a number less than your balance and less than a million:",
				out.toString().trim());
		processUserInput.close();
	}

}
