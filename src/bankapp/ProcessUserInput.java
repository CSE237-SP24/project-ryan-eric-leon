package bankapp;

import java.util.Scanner;

public class ProcessUserInput {
	private Scanner scanner;

	public ProcessUserInput() {
		this.scanner = new Scanner(System.in);
	}

	public String getInput() {
		return scanner.nextLine();
	}

	public double getValidAmount(double limit) {
		while (true) {
			try {
				double amount = Double.parseDouble(scanner.nextLine());
				if (amount < 0) {
					System.out.println("Invalid value! Please enter a non-negative number:");
				} else if (amount >= limit) {
					System.out.println(
							"Invalid value! Please enter a number less than your balance and less than a million:");
				} else {
					return amount;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input! Please enter a valid number:");
			}
		}
	}

	public void close() {
		scanner.close();
	}
}
